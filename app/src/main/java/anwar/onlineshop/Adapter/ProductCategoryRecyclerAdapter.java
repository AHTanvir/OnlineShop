package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.CategoryModel;
import anwar.onlineshop.Model.ProductCategoryModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/16/2017.
 */

public class ProductCategoryRecyclerAdapter extends RecyclerView.Adapter<ProductCategoryRecyclerAdapter.MyViewHolder>
        implements Filterable{
    private List<ProductCategoryModel> categoryList =new ArrayList<>();
    private List<ProductCategoryModel> filterResult=new ArrayList<>();
    private ValueFilter valueFilter;
    private Context context;
    private OnItemClickListeners listeners;
    public ProductCategoryRecyclerAdapter(List<ProductCategoryModel> rowItems, OnItemClickListeners listeners) {
        this.categoryList = rowItems;
        this.listeners=listeners;
        filterResult=rowItems;
    }

    public ProductCategoryRecyclerAdapter(OnItemClickListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public ProductCategoryRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        ProductCategoryRecyclerAdapter.MyViewHolder holder=new ProductCategoryRecyclerAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductCategoryRecyclerAdapter.MyViewHolder holder, final int position) {
        ProductCategoryModel pos= categoryList.get(position);
        holder.name.setText(pos.getName());
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
        return categoryList.size();
    }

    @Override
    public Filter getFilter() {
        if(valueFilter==null) {
            valueFilter=new ProductCategoryRecyclerAdapter.ValueFilter();
        }
        return valueFilter;
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
    public void addProducts(List<ProductCategoryModel> categoryList){
        this.categoryList=categoryList;
        notifyDataSetChanged();
    }
    private class ValueFilter extends Filter {
        //Invoked in a worker thread to filter the data according to the constraint.
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<ProductCategoryModel> filterList = new ArrayList<>();
                for (int i = 0; i < filterResult.size(); i++) {
                    if ((filterResult.get(i).getName().toUpperCase())
                            .startsWith(constraint.toString().toUpperCase())) {
                        ProductCategoryModel contacts =
                                new ProductCategoryModel(filterResult.get(i).getName(), filterResult.get(i).getTotal());
                        filterList.add(contacts);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = filterResult.size();
                results.values = filterResult;
            }
            return results;
        }
        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categoryList=(ArrayList<ProductCategoryModel>) results.values;
            notifyDataSetChanged();

        }
    }
}
