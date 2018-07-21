package gawendeng.org.scanshop;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
  private List<Item> itemList;
  private Listener listener;

  public ItemAdapter(List<Item> itemList, Listener listener) {
    this.itemList = itemList;
    this.listener = listener;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.item_preview, parent, false);

    return new ViewHolder(itemView);
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
    notifyDataSetChanged();
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    final Item item = itemList.get(position);
    holder.name.setText(item.getName());
    holder.priceStrikeThrough.setText(item.getPriceStrikethrough());
    holder.priceStrikeThrough.setPaintFlags(holder.priceStrikeThrough.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    holder.price.setText(item.getPrice());
    holder.quantity.setText(item.getQuantity());
    Ion.with(holder.image).load(item.getImageUrl());
    //TODO set hapus button
    holder.removeItem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.onDelete(item.getId());
      }
    });
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

  public interface Listener {
    void onDelete(String id);
  }

}
