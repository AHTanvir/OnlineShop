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
import anwar.onlineshop.Model.CategoryModel;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/16/2017.
 */

public class RecomRecyclerAdapter extends RecyclerView.Adapter<RecomRecyclerAdapter.MyViewHolder> {
    private List<ProductModel> productModels =new ArrayList<>();
    private Context context;
    private OnItemClickListeners listeners;
    public RecomRecyclerAdapter(List<ProductModel> productModels, OnItemClickListeners listeners) {
        this.productModels = productModels;
        this.listeners=listeners;
    }

    public RecomRecyclerAdapter(OnItemClickListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public RecomRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recom_list_item,parent,false);
        RecomRecyclerAdapter.MyViewHolder holder=new RecomRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecomRecyclerAdapter.MyViewHolder holder, final int position) {
        ProductModel row_pos = productModels.get(position);
        Picasso.with(context).load(Integer.parseInt(row_pos.getUrl())).into(holder.imageView);
        //holder.imageView.setImageResource(row_pos.getDrawable());
        holder.name.setText(row_pos.getName());
        holder.details.setText(row_pos.getDetails());
        holder.price.setText("Tk "+row_pos.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView().findViewById(R.id.category_recomList),position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listeners.onLongClick(v.getRootView().findViewById(R.id.category_recomList),position);
                return false;
            }
        });

    }
    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView details;
        TextView price;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.product_img);
            name = (TextView) itemView.findViewById(R.id.product_name);
            details = (TextView) itemView.findViewById(R.id.product_details);
            price = (TextView) itemView.findViewById(R.id.product_price);
        }
    }
    public void addProducts(List<ProductModel> productModels){
        this.productModels.addAll(productModels);
        notifyDataSetChanged();
    }
}
