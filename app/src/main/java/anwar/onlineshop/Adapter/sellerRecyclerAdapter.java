package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
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
 * Created by anwar on 8/15/2017.
 */

public class sellerRecyclerAdapter extends RecyclerView.Adapter<sellerRecyclerAdapter.MyViewHolder>{
    private List<RowItem> rowItems =new ArrayList<>();
    private Context context;
    public sellerRecyclerAdapter(List<RowItem> rowItems) {
        this.rowItems = rowItems;
    }

    @Override
    public sellerRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.best_sellers_item,parent,false);
        sellerRecyclerAdapter.MyViewHolder holder=new sellerRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(sellerRecyclerAdapter.MyViewHolder holder, int position) {
        RowItem pos= rowItems.get(position);
        holder.image.setImageResource(pos.getDrawable());
        holder.name.setText(pos.getName());
        holder.price.setText(pos.getPrice());

    }
    @Override
    public int getItemCount() {
        return rowItems.size();
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
