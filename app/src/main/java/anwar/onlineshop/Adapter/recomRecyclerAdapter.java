package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/16/2017.
 */

public class recomRecyclerAdapter extends RecyclerView.Adapter<recomRecyclerAdapter.MyViewHolder> {
    private List<RowItem> rowItems =new ArrayList<>();
    private Context context;
    public recomRecyclerAdapter(List<RowItem> rowItems) {
        this.rowItems = rowItems;
    }

    @Override
    public recomRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recom_list_item,parent,false);
        recomRecyclerAdapter.MyViewHolder holder=new recomRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(recomRecyclerAdapter.MyViewHolder holder, int position) {
        RowItem row_pos = rowItems.get(position);
        holder.imageView.setImageResource(row_pos.getDrawable());
        holder.name.setText(row_pos.getName());
        holder.details.setText(row_pos.getDetails());
        holder.price.setText(row_pos.getPrice());

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
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.product_img);
            name = (TextView) itemView.findViewById(R.id.product_name);
            details = (TextView) itemView.findViewById(R.id.product_details);
            price = (TextView) itemView.findViewById(R.id.product_price);
        }
    }
}
