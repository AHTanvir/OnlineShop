package anwar.onlineshop.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Interface.OnItemClickListeners;
import anwar.onlineshop.Model.CategoryModel;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;


/**
 * Created by anwar on 3/4/2017.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder> implements View.OnClickListener{
    private List<CategoryModel> categoryList =new ArrayList<>();
    private Context context;
    private OnItemClickListeners listeners;

    public HomeRecyclerAdapter(List<CategoryModel> rowItems, OnItemClickListeners listeners) {
        this.categoryList = rowItems;
        this.listeners = listeners;
    }

    public HomeRecyclerAdapter(OnItemClickListeners listeners) {
        this.listeners = listeners;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context=parent.getContext();
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_row_home_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        CategoryModel pos= categoryList.get(position);
        holder.image.setImageResource(Integer.parseInt(pos.getImageUrl()));
        //Picasso.with(context).load(pos.getImageUrl()).into(holder.image);
        holder.text.setText(pos.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView(),position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onClick(v.getRootView().findViewById(R.id.home_recyclerView),position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listeners.onLongClick(v.getRootView().findViewById(R.id.home_recyclerView),position);
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    @Override
    public void onClick(View v) {

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=(ImageView)itemView.findViewById(R.id.thumb_img);
            text=(TextView)itemView.findViewById(R.id.catagory_name);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
        }
    }
    public void addProducts(List<CategoryModel> categoryList){
        this.categoryList=categoryList;
        notifyDataSetChanged();
    }
}
/*
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custum_row_home_item,parent,false);
        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        RowItem pos= rowItems.get(position);
        holder.image.setImageResource(pos.getDrawable());
        holder.text.setText(pos.getCatagory());
    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }
public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView text;
    public RecyclerViewHolder(View itemView) {
        super(itemView);
        image=(ImageView)itemView.findViewById(R.id.thumb_img);
        text=(TextView)itemView.findViewById(R.id.catagory_name);
    }
}*/
