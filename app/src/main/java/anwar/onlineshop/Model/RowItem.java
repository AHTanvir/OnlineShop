package anwar.onlineshop.Model;

import android.graphics.Bitmap;

/**
 * Created by anwar on 12/16/2016.
 */

public class RowItem {
   private int drawable;
    private String id;
    private String Product_id;
    private String catagory;
    private String name;
    private String price;
    private String total;
    private String details;
    private String size;
    private String color;
    private String url;
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

    public RowItem( String product_id, String name, String price, String size, String color, String url) {
        Product_id = product_id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.url = url;
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

    public String getProduct_id() {
        return Product_id;
    }

    public void setProduct_id(String product_id) {
        Product_id = product_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
