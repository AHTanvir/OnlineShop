package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/15/2017.
 */

public class sellerRecyclerAdapter extends RecyclerView.Adapter<sellerRecyclerAdapter.MyViewHolder>{
    private List<ProductModel> productModel =new ArrayList<>();
    private Context context;
    private OnItemClickListeners listeners;
    public sellerRecyclerAdapter(List<ProductModel> productModel, OnItemClickListeners listeners) {
        this.productModel = productModel;
        this.listeners = listeners;
    }

    @Override
    public sellerRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.best_sellers_item,parent,false);
        sellerRecyclerAdapter.MyViewHolder holder=new sellerRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(sellerRecyclerAdapter.MyViewHolder holder, final int position) {
        ProductModel pos= productModel.get(position);
        Picasso.with(context).load(Integer.parseInt(pos.getUrl())).into(holder.image);
        //Picasso.with(context).load(pos.getUrl()).into(holder.image);
       // holder.image.setImageResource(pos.getDrawable());
        holder.name.setText(pos.getName());
        holder.price.setText("Tk " +pos.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView(),position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView().findViewById(R.id.home_bestseller_list),position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listeners.onLongClick(v.getRootView().findViewById(R.id.home_bestseller_list),position);
                return false;
            }
        });

    }
    @Override
    public int getItemCount() {
        return productModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name,price;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.thumb_img);
            name=(TextView)itemView.findViewById(R.id.Product_name);
            price=(TextView)itemView.findViewById(R.id.Product_price);
        }
    }
}
