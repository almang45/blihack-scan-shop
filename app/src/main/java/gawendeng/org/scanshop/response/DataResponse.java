package gawendeng.org.scanshop.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataResponse {

  @SerializedName("code")
  @Expose
  private int code;

  @SerializedName("status")
  @Expose
  private String status;

  @SerializedName("data")
  @Expose
  private CartResponse cartResponse;

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public CartResponse getCartResponse() {
    return cartResponse;
  }

  public void setCartResponse(CartResponse cartResponse) {
    this.cartResponse = cartResponse;
  }
}
