package gawendeng.org.scanshop;

public class Item {

  private String id, name, priceStrikethrough, price, quantity, imageUrl;

  public Item() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPriceStrikethrough() {
    return priceStrikethrough;
  }

  public void setPriceStrikethrough(String priceStrikethrough) {
    this.priceStrikethrough = priceStrikethrough;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
