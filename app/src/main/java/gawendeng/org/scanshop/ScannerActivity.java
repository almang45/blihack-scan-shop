package gawendeng.org.scanshop;

import android.app.Activity;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ScannerActivity extends Activity {
  private static final String TAG = ScannerActivity.class.getSimpleName();

  private DecoratedBarcodeView barcodeView;
  private BeepManager beepManager;

  private List<Item> itemList = new ArrayList<>();
  private RecyclerView recyclerView;
  private ItemAdapter itemAdapter;

  private BarcodeCallback callback = new BarcodeCallback() {
    @Override
    public void barcodeResult(BarcodeResult result) {
      if (result.getText() == null) {
        // Prevent duplicate scans
        return;
      }

      beepManager.playBeepSoundAndVibrate();

      Item item = new Item();
      item.setName(result.getText());
      item.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));
      itemList.add(item);
      itemAdapter.addItemToList(item);
    }

    @Override
    public void possibleResultPoints(List<ResultPoint> resultPoints) {
    }
  };

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
