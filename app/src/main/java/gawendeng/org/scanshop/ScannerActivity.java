package gawendeng.org.scanshop;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import gawendeng.org.scanshop.request.AddToCartRequest;
import gawendeng.org.scanshop.response.CartItem;
import gawendeng.org.scanshop.response.DataResponse;
import gawendeng.org.scanshop.rest.ApiService;
import gawendeng.org.scanshop.rest.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScannerActivity extends Activity {
  private static final String TAG = ScannerActivity.class.getSimpleName();

  private ApiService mApiService;
  private DecoratedBarcodeView barcodeView;
  private BeepManager beepManager;
  private List<Item> itemList = new ArrayList<>();
  private RecyclerView recyclerView;
  private ItemAdapter itemAdapter;
  private String lastScannedText;
  private long lastScannedTime;

  private BarcodeCallback callback = new BarcodeCallback() {
    @Override
    public void barcodeResult(BarcodeResult result) {
      if (result.getText() == null && isDuplicate(result)) {
        return;
      }

      lastScannedText = result.getText();
      lastScannedTime = result.getTimestamp();
      barcodeView.setStatusText(result.getText());
      beepManager.playBeepSoundAndVibrate();

      addToCart(result.getText());
    }

    @Override
    public void possibleResultPoints(List<ResultPoint> resultPoints) {
    }
  };

  private boolean isDuplicate(BarcodeResult result) {
    return result.getText().equals(lastScannedText) && result.getTimestamp() - lastScannedTime > 60000;
  }

  public void addToCart(String itemSku) {
    mApiService.addToCart(new AddToCartRequest(itemSku)).enqueue(new Callback<DataResponse>() {
      @Override
      public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
        if (response.isSuccessful()) {
          List<Item> itemList = convertCartItemToItem(response.body().getCartResponse().getItems());
          itemAdapter.setItemList(itemList);
        }
      }

      @Override
      public void onFailure(Call<DataResponse> call, Throwable t) {

      }
    });
  }

  public void getCart() {
    mApiService.getCart().enqueue(new Callback<DataResponse>() {
      @Override
      public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        if (response.isSuccessful()) {
          List<Item> itemList = convertCartItemToItem(response.body().getCartResponse().getItems());
          itemAdapter.setItemList(itemList);
        }
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
      }

      @Override
      public void onFailure(Call<DataResponse> call, Throwable t) {

      }
    });
  }

  private List<Item> convertCartItemToItem(List<CartItem> cartItems) {
    List<Item> itemList = new ArrayList<>();
    for (CartItem cartItem : cartItems) {
      itemList.add(convertCartItemToItem(cartItem));
    }
    return itemList;
  }

  private Item convertCartItemToItem(CartItem cartItem) {
    Item item = new Item();
    item.setName(cartItem.getName());
    item.setPrice(formatPrice(cartItem.getPrice().getOffered()));
    item.setPriceStrikethrough(formatPrice(cartItem.getPrice().getListed()));
    item.setQuantity(cartItem.getQuantity() + "");
    item.setImageUrl(cartItem.getThumbnailUrl());
    item.setImageBitmap(getItemImageBitmap(cartItem.getThumbnailUrl()));
    return item;
  }

  private Bitmap getItemImageBitmap(String imageUrl) {
    try {
      URL url = new URL(imageUrl);
      return BitmapFactory.decodeStream(url.openConnection().getInputStream());
    } catch (Exception e) {
      return null;
    }
  }

  private String formatPrice(float price) {
    return "Rp " + price;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_scanner);

    barcodeView = findViewById(R.id.barcode_scanner);
    Collection<BarcodeFormat> formats = Arrays.asList(BarcodeFormat.QR_CODE, BarcodeFormat.CODE_39);
    barcodeView.getBarcodeView().setDecoderFactory(new DefaultDecoderFactory(formats));
    barcodeView.decodeContinuous(callback);

    beepManager = new BeepManager(this);

    recyclerView = findViewById(R.id.view_cart);

    itemAdapter = new ItemAdapter(itemList);
    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerView.setLayoutManager(mLayoutManager);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    recyclerView.setAdapter(itemAdapter);

    mApiService = ApiUtils.getApiService();

    getCart();
  }

  @Override
  protected void onResume() {
    super.onResume();

    barcodeView.resume();
  }

  @Override
  protected void onPause() {
    super.onPause();

    barcodeView.pause();
  }

  public void pause(View view) {
    barcodeView.pause();
  }

  public void resume(View view) {
    barcodeView.resume();
  }

  public void triggerScan(View view) {
    barcodeView.decodeSingle(callback);
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
  }
}
