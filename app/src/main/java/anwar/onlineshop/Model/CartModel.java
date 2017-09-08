package anwar.onlineshop.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anwar on 8/31/2017.
 */

public class CartModel implements Parcelable{
    private String productid;
    private String name;
    private String size;
    private String color;
    private String price;
    private String quantity;
    private String imageurl;

    public CartModel(String productid, String name, String size, String color, String price, String quantity, String imageurl) {
        this.productid = productid;
        this.name = name;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.imageurl = imageurl;
    }
    public CartModel(Parcel parcel){
        productid=parcel.readString();
        name=parcel.readString();
        size=parcel.readString();
        color=parcel.readString();
        price=parcel.readString();
        quantity=parcel.readString();
        imageurl=parcel.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(productid);
        parcel.writeString(name);
        parcel.writeString(size);
        parcel.writeString(color);
        parcel.writeString(name);
        parcel.writeString(price);
        parcel.writeString(quantity);
        parcel.writeString(imageurl);
    }
    public static final Parcelable.Creator<CartModel> CREATOR = new
            Parcelable.Creator<CartModel>() {
                public CartModel createFromParcel(Parcel in) {
                    return new CartModel(in);
                }
                public CartModel[] newArray(int size) {
                    return new CartModel[size];
                }};
}
