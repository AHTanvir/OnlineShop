package anwar.onlineshop.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anwar on 9/7/2017.
 */

public class AddressModel implements Parcelable{
    private String name;
    private String address;
    private String city;
    private String email;
    private String phone;
    public AddressModel(String name, String address, String city, String email, String phone) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public AddressModel(Parcel parcel) {
        name=parcel.readString();
        address=parcel.readString();
        city=parcel.readString();
        email=parcel.readString();
        phone=parcel.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(email);
        parcel.writeString(phone);
    }
    public static final Parcelable.Creator<AddressModel> CREATOR = new
            Parcelable.Creator<AddressModel>() {
                public AddressModel createFromParcel(Parcel in) {
                    return new AddressModel(in);
                }
                public AddressModel[] newArray(int size) {
                    return new AddressModel[size];
                }};
}
