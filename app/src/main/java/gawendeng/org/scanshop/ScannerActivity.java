package gawendeng.org.scanshop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import gawendeng.org.scanshop.request.CartRequest;
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

  private BarcodeCallback callback = new BarcodeCallback() {
    @Override
    public void barcodeResult(BarcodeResult result) {
      if (result.getText() == null) {
        return;
      }

      barcodeView.setStatusText(result.getText());
      beepManager.playBeepSoundAndVibrate();

      addToCart(result.getText());
    }

    @Override
    public void possibleResultPoints(List<ResultPoint> resultPoints) {
    }
  };

  public void addToCart(String itemSku) {
    mApiService.addToCart(new CartRequest(itemSku)).enqueue(new Callback<DataResponse>() {
      @Override
      public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
        findViewById(R.id.loadingPanelCart).setVisibility(View.VISIBLE);
        if (response.isSuccessful() && response.body().getCode() == 200) {
          List<Item> itemList = convertCartItemToItem(response.body().getCartResponse().getItems());
          itemAdapter.setItemList(itemList);
        } else {
          getOutOfStockDialog();
        }
        findViewById(R.id.loadingPanelCart).setVisibility(View.GONE);
      }

      @Override
      public void onFailure(Call<DataResponse> call, Throwable t) {

      }
    });
  }

  private void getOutOfStockDialog() {
    AlertDialog.Builder dialog = new AlertDialog.Builder(ScannerActivity.this);
    dialog.setTitle("Out of Stock!");
    dialog.setMessage("Maaf, stok barang habis!");
    dialog.setPositiveButton("Belanja Lagi", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    dialog.show();
  }

  public void getCart() {
    mApiService.getCart().enqueue(new Callback<DataResponse>() {
      @Override
      public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
        findViewById(R.id.loadingPanelCart).setVisibility(View.VISIBLE);
        if (response.isSuccessful()) {
          List<Item> itemList = convertCartItemToItem(response.body().getCartResponse().getItems());
          itemAdapter.setItemList(itemList);
        }
        findViewById(R.id.loadingPanelCart).setVisibility(View.GONE);
      }

      @Override
      public void onFailure(Call<DataResponse> call, Throwable t) {

      }
    });
  }

  public void updateCart(String id) {
    mApiService.updateCart(new CartRequest(id, 0)).enqueue(new Callback<DataResponse>() {
      @Override
      public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
        findViewById(R.id.loadingPanelCart).setVisibility(View.VISIBLE);
        if (response.isSuccessful()) {
          List<Item> itemList = convertCartItemToItem(response.body().getCartResponse().getItems());
          itemAdapter.setItemList(itemList);
        }
        findViewById(R.id.loadingPanelCart).setVisibility(View.GONE);
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
    item.setId(cartItem.getItemSku());
    item.setName(cartItem.getName());
    item.setPrice(formatPrice(cartItem.getPrice().getOffered()));
    item.setPriceStrikethrough(formatPrice(cartItem.getPrice().getListed()));
    item.setQuantity(cartItem.getQuantity() + "");
    item.setImageUrl(cartItem.getThumbnailUrl());
    return item;
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
    barcodeView.decodeSingle(callback);
    findViewById(R.id.btn_continue_shopping).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        barcodeView.decodeSingle(callback);
      }
    });

    beepManager = new BeepManager(this);

    itemAdapter = new ItemAdapter(itemList, new ItemAdapter.Listener() {
      @Override
      public void onDelete(String id) {
        updateCart(id);
      }
    });

    recyclerView = findViewById(R.id.view_cart);
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

  public void triggerScan(View view) {
    barcodeView.decodeSingle(callback);
  }

}
