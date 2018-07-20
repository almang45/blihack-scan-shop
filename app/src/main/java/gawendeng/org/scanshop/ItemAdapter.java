package gawendeng.org.scanshop;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
  private List<Item> itemList;

  public ItemAdapter(List<Item> itemList) {
    this.itemList = itemList;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_preview, parent, false);

    return new ViewHolder(itemView);
  }

  public void addItemToList(Item newItem) {
    this.itemList.add(newItem);
    notifyDataSetChanged();
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    Item item = itemList.get(position);
    holder.name.setText(item.getName());
    holder.priceStrikeThrough.setText(item.getPriceStrikethrough());
    holder.priceStrikeThrough.setPaintFlags(holder.priceStrikeThrough.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    holder.price.setText(item.getPrice());
    holder.quantity.setText(item.getQuantity());
    holder.image.setImageBitmap(item.getImageBitmap());
    //TODO set hapus button
  }

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView name, priceStrikeThrough, price, quantity;
    public ImageView image;
    public Button removeItem;

    public ViewHolder(View view) {
      super(view);
      name = view.findViewById(R.id.tv_item_name);
      priceStrikeThrough = view.findViewById(R.id.tv_item_price_strikethrough);
      price = view.findViewById(R.id.tv_item_price);
      quantity = view.findViewById(R.id.tv_item_quantity);
      image = view.findViewById(R.id.img_item_image);
      removeItem = view.findViewById(R.id.btn_item_remove);
    }
  }

}
