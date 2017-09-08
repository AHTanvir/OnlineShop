package anwar.onlineshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 8/16/2017.
 */

public class recomListAdapter extends BaseAdapter {
    Context context;
    List<ProductModel> productModels;
    private LayoutInflater inflater;

    public recomListAdapter(Context context, List<ProductModel> productModels) {
        this.context = context;
        this.productModels = productModels;
    }

    public recomListAdapter(Context context) {
        this.context = context;
    }

    public int getCount() {
        return productModels.size();
    }

    public Object getItem(int position) {
        return productModels.get(position);
    }

    public long getItemId(int position) {
        return productModels.indexOf(getItem(position));
    }

    private class ViewHolder {
        ImageView imageView;
        TextView name;
        TextView details;
        TextView price;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        recomListAdapter.ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context.
                getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            holder = new recomListAdapter.ViewHolder();
            convertView = mInflater.inflate(R.layout.recom_list_item, null);
            holder.imageView=(ImageView)convertView.findViewById(R.id.product_img);
            holder.name = (TextView) convertView.findViewById(R.id.product_name);
            holder.details = (TextView) convertView.findViewById(R.id.product_details);
            holder.price = (TextView) convertView.findViewById(R.id.product_price);
            convertView.setTag(holder);
        } else {
            holder = (recomListAdapter.ViewHolder) convertView.getTag();
        }
        ProductModel row_pos = productModels.get(position);
      //  holder.imageView.setImageResource(row_pos.getDrawable());
        holder.name.setText(row_pos.getName());
        holder.details.setText(row_pos.getDetails());
        holder.price.setText("Tk "+row_pos.getPrice());
        return convertView;
    }

    public void updateAdapter(List<ProductModel> updateList) {
        //and call notifyDataSetChanged
        productModels = updateList;
        notifyDataSetChanged();
    }
}
