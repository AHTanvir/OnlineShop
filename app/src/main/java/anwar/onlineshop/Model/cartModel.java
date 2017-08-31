package anwar.onlineshop.Model;

/**
 * Created by anwar on 8/31/2017.
 */

public class cartModel {
    private String productid;
    private String name;
    private String size;
    private String color;
    private String price;
    private String quantity;
    private String imageurl;

    public cartModel(String productid, String name, String size, String color, String price, String quantity, String imageurl) {
        this.productid = productid;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.imageurl = imageurl;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
