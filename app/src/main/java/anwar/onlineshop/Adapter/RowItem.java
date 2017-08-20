package anwar.onlineshop.Adapter;

import android.graphics.Bitmap;

/**
 * Created by anwar on 12/16/2016.
 */

public class RowItem {
   private int drawable;
    private String id;
    private String catagory;
    private String name;
    private String price;
    private String total;
    private String details;
    public RowItem(int drawable, String catagory) {
        this.drawable = drawable;
        this.catagory = catagory;
    }
    public RowItem(int drawable, String name, String price) {
        this.drawable = drawable;
        this.name = name;
        this.price = price;
    }
    public RowItem(String catagory, String total) {
        this.catagory = catagory;
        this.total = total;
    }

    public RowItem(String id, String catagory, String total) {
        this.id = id;
        this.catagory = catagory;
        this.total = total;
    }

    public RowItem(int drawable, String name, String details, String price) {
        this.drawable = drawable;
        this.name = name;
        this.details = details;
        this.price = price;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
