package gawendeng.org.scanshop.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
  @SerializedName("id")
  @Expose
  private String id;

  @SerializedName("label")
  @Expose
  private String label;

  @SerializedName("level")
  @Expose
  private int level;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }
}
