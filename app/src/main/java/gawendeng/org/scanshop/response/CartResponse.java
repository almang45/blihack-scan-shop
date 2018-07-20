package gawendeng.org.scanshop.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartResponse {

  @SerializedName("items")
  @Expose
  private List<CartItem> items = null;

  @SerializedName("totalPrice")
  @Expose
  private float totalPrice;


  public List<CartItem> getItems() {
    return items;
  }

  public void setItems(List<CartItem> items) {
    this.items = items;
  }

  public float getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }
}
