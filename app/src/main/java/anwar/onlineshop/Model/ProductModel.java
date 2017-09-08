package anwar.onlineshop.Model;

import java.io.Serializable;

/**
 * Created by anwar on 9/2/2017.
 */

public class ProductModel implements Serializable{
    private String product_id;
    private String name;
    private String details;
    private String size;
    private String color;
    private String price;
    private String url;

    public ProductModel(String product_id, String name, String details, String size, String color, String price, String url) {
        this.product_id = product_id;
        this.name = name;
        this.details = details;
        this.size = size;
        this.color = color;
        this.price = price;
        this.url = url;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
