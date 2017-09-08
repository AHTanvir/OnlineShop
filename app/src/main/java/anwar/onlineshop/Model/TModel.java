package anwar.onlineshop.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anwar on 9/6/2017.
 */

public class TModel implements Parcelable{
    String name;

    public TModel(String name) {
        this.name=name;
    }

    public TModel(Parcel in) {
        name=in.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Creator<CartModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
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
