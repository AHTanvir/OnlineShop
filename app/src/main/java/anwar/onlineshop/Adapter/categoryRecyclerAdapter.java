package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/16/2017.
 */

public class categoryRecyclerAdapter extends RecyclerView.Adapter<categoryRecyclerAdapter.MyViewHolder> {
    private List<RowItem> rowItems =new ArrayList<>();
    private Context context;
    public categoryRecyclerAdapter(List<RowItem> rowItems) {
        this.rowItems = rowItems;
    }

    @Override
    public categoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        categoryRecyclerAdapter.MyViewHolder holder=new categoryRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(categoryRecyclerAdapter.MyViewHolder holder, int position) {
        RowItem pos= rowItems.get(position);
        holder.name.setText(pos.getCatagory());
        holder.total.setText(pos.getTotal());

    }
    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView total;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.cg_list);
            total=(TextView)itemView.findViewById(R.id.cg_total);
        }
    }
}
