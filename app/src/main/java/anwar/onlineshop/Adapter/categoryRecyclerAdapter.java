package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/16/2017.
 */

public class categoryRecyclerAdapter extends RecyclerView.Adapter<categoryRecyclerAdapter.MyViewHolder> {
    private List<RowItem> rowItems =new ArrayList<>();
    private Context context;
    private OnItemClickListeners listeners;
    public categoryRecyclerAdapter(List<RowItem> rowItems,OnItemClickListeners listeners) {
        this.rowItems = rowItems;
        this.listeners=listeners;
    }

    @Override
    public categoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        categoryRecyclerAdapter.MyViewHolder holder=new categoryRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(categoryRecyclerAdapter.MyViewHolder holder, final int position) {
        RowItem pos= rowItems.get(position);
        holder.name.setText(pos.getCatagory());
        holder.total.setText(pos.getTotal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView().findViewById(R.id.category_list),position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listeners.onLongClick(v.getRootView().findViewById(R.id.category_list),position);
                return false;
            }
        });

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
