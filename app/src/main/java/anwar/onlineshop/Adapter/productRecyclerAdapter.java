package anwar.onlineshop.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/20/2017.
 */

public class productRecyclerAdapter  extends RecyclerView.Adapter<productRecyclerAdapter.MyViewHolder> {
    private List<ProductModel> rowItems =new ArrayList<>();
    private Context context;
    private OnItemClickListeners listeners;

    public productRecyclerAdapter( OnItemClickListeners listeners) {
        this.listeners = listeners;
    }

    public productRecyclerAdapter(List<ProductModel> rowItems, OnItemClickListeners listeners) {
        this.rowItems = rowItems;
        this.listeners = listeners;
    }

    @Override
    public productRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,parent,false);
        productRecyclerAdapter.MyViewHolder holder=new productRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final productRecyclerAdapter.MyViewHolder holder, final int position) {
        ProductModel row_pos = rowItems.get(position);
/*        holder.progressBar.setVisibility(View.VISIBLE);
        Picasso.with(context).load(row_pos.getUrl()).into(holder.imageView,new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError() {}});*/
       Picasso.with(context).load(Integer.parseInt(row_pos.getUrl())).into(holder.imageView);
        holder.name.setText(row_pos.getName());
        holder.details.setText(row_pos.getDetails());
        holder.price.setText("Tk "+row_pos.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView(),position);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView().findViewById(R.id.product_recycler_view),position);
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listeners.onLongClick(v.getRootView().findViewById(R.id.product_recycler_view),position);
                return false;
            }
        });

    }
    public void addProduct(List<ProductModel> rowItems){
        this.rowItems.addAll(rowItems);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView details;
        TextView price;
        ProgressBar progressBar;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.product_img);
            progressBar=(ProgressBar)itemView.findViewById(R.id.product_progressbar);
            name = (TextView) itemView.findViewById(R.id.product_name);
            details = (TextView) itemView.findViewById(R.id.product_details);
            price = (TextView) itemView.findViewById(R.id.product_price);
        }
    }

}
