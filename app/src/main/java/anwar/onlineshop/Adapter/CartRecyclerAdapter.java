package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.CartModel;
import anwar.onlineshop.R;

/**
 * Created by anwar on 9/2/2017.
 */

public class CartRecyclerAdapter extends RecyclerView.Adapter<CartRecyclerAdapter.MyViewHolder>{
    private List<CartModel> rowItems =new ArrayList<>();
    private Context context;
    private OnItemClickListeners listeners;
    public CartRecyclerAdapter(List<CartModel> rowItems, OnItemClickListeners listeners) {
        this.rowItems = rowItems;
        this.listeners=listeners;
    }


    @Override
    public CartRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item,parent,false);
        CartRecyclerAdapter.MyViewHolder holder=new CartRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartRecyclerAdapter.MyViewHolder holder, final int position) {
        CartModel row_pos = rowItems.get(position);
        Picasso.with(context).load(Integer.parseInt(row_pos.getImageurl())).into(holder.imageView);
      // holder.imageView.setImageResource(Integer.parseInt(row_pos.getImageurl()));
        holder.name.setText(row_pos.getName());
        holder.quantity.setText(row_pos.getQuantity());
        holder.price.setText(row_pos.getPrice()+" Tk");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView().findViewById(R.id.cart_recyclerview),position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listeners.onLongClick(v.getRootView().findViewById(R.id.cart_recyclerview),position);
                return false;
            }
        });
    }
    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView quantity;
        TextView price;
        public MyViewHolder(final View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.product_img);
            name = (TextView) itemView.findViewById(R.id.product_name);
            quantity = (TextView) itemView.findViewById(R.id.product_quantity);
            price = (TextView) itemView.findViewById(R.id.product_price);
        }
    }
}
