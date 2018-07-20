package gawendeng.org.scanshop;

import android.graphics.Bitmap;

public class Item {

  private String name, priceStrikethrough, price, quantity, imageUrl;
  private Bitmap imageBitmap;


  public Item() {
  }

  public Item(String name, String priceStrikethrough, String price, String quantity, String imageUrl) {
    this.name = name;
    this.priceStrikethrough = priceStrikethrough;
    this.price = price;
    this.quantity = quantity;
    this.imageUrl = imageUrl;
  }

  public Item(String name, String priceStrikethrough, String price, String quantity, String imageUrl, Bitmap imageBitmap) {
    this.name = name;
    this.priceStrikethrough = priceStrikethrough;
    this.price = price;
    this.quantity = quantity;
    this.imageUrl = imageUrl;
    this.imageBitmap = imageBitmap;
  }

  public Bitmap getImageBitmap() {
    return imageBitmap;
  }

  public void setImageBitmap(Bitmap imageBitmap) {
    this.imageBitmap = imageBitmap;
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
