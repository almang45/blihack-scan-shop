package gawendeng.org.scanshop.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartRequest {

  @SerializedName("id")
  @Expose
  public String id;

  @SerializedName("quantity")
  @Expose
  public int quantity;

  @SerializedName("type")
  @Expose
  public String type;

  public CartRequest(String id) {
    this.id = id;
    this.quantity = 1;
    this.type = "REGULAR";
  }

  public CartRequest(String id, int quantity) {
    this.id = id;
    this.quantity = quantity;
    this.type = "REGULAR";
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
