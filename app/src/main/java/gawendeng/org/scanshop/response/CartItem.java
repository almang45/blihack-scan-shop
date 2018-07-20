package gawendeng.org.scanshop.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

  @SerializedName("itemCode")
  @Expose
  private String itemCode;

  @SerializedName("itemSku")
  @Expose
  private String itemSku;

  @SerializedName("name")
  @Expose
  private String name;

  @SerializedName("price")
  @Expose
  private Price price;

  @SerializedName("primary")
  @Expose
  private boolean primary;

  @SerializedName("productCode")
  @Expose
  private String productCode;

  @SerializedName("productSku")
  @Expose
  private String productSku;

  @SerializedName("productUrl")
  @Expose
  private String productUrl;

  @SerializedName("quantity")
  @Expose
  private int quantity;

  @SerializedName("thumbnailUrl")
  @Expose
  private String thumbnailUrl;


  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getItemSku() {
    return itemSku;
  }

  public void setItemSku(String itemSku) {
    this.itemSku = itemSku;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Price getPrice() {
    return price;
  }

  public void setPrice(Price price) {
    this.price = price;
  }

  public boolean isPrimary() {
    return primary;
  }

  public void setPrimary(boolean primary) {
    this.primary = primary;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductSku() {
    return productSku;
  }

  public void setProductSku(String productSku) {
    this.productSku = productSku;
  }

  public String getProductUrl() {
    return productUrl;
  }

  public void setProductUrl(String productUrl) {
    this.productUrl = productUrl;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  @Override
  public String toString() {
    return "CartItem{" +
        "itemSku='" + itemSku + '\'' +
        ", name='" + name + '\'' +
        ", offeredPrice=" + price.getOffered() +
        ", listedPrice=" + price.getListed() +
        ", quantity=" + quantity +
        ", thumbnailUrl='" + thumbnailUrl + '\'' +
        '}';
  }
}
