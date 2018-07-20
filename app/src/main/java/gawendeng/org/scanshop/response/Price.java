package gawendeng.org.scanshop.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {
  @SerializedName("discounted")
  @Expose
  private float discounted;

  @SerializedName("listed")
  @Expose
  private float listed;

  @SerializedName("offered")
  @Expose
  private float offered;

  @SerializedName("discountPercentage")
  @Expose
  private float discountPercentage;

  public float getDiscounted() {
    return discounted;
  }

  public void setDiscounted(float discounted) {
    this.discounted = discounted;
  }

  public float getListed() {
    return listed;
  }

  public void setListed(float listed) {
    this.listed = listed;
  }

  public float getOffered() {
    return offered;
  }

  public void setOffered(float offered) {
    this.offered = offered;
  }

  public float getDiscountPercentage() {
    return discountPercentage;
  }

  public void setDiscountPercentage(float discountPercentage) {
    this.discountPercentage = discountPercentage;
  }
}
