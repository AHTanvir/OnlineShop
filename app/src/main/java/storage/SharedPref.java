package storage;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Model.cartModel;

/**
 * Created by anwar on 8/31/2017.
 */

public class SharedPref {
    private SharedPreferences pref;
    private cartModel cartmodel;
    private SharedPreferences.Editor editor;
    private Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "ShopPref";
    private static final String PREFS_KEY= "ShopPref";
    private static final String CART_KEY= "cart_item";
    private static final String ORDER_KEY= "order_item";
    private  static final String PRODUCTID="productid";
    private  static final String NAME="name";
    private  static final String SIZE="size";
    private  static final String COLOR="color";
    private  static final String PRICE="price";
    private  static final String QUANTITY="quantity";
    private  static final String IMGURL="imageurl";

    public SharedPref(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE); //1
        editor = pref.edit();
    }
    public void clearSharedPreference(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();

        editor.clear();
        editor.commit();
    }

    public void removeValue(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(PREFS_KEY);
        editor.commit();
    }
    public void putCartItem(cartModel cartmodel) {
        JSONArray array=null;
        String json=pref.getString(CART_KEY, null);
        try {
            if(json !=null)
                array = new JSONArray(json);
            else array = new JSONArray();
            JSONObject obj = new JSONObject();
            obj.put(PRODUCTID,cartmodel.getProductid());
            obj.put(NAME,cartmodel.getName());
            obj.put(COLOR,cartmodel.getColor());
            obj.put(SIZE,cartmodel.getName());
            obj.put(PRICE,cartmodel.getPrice());
            obj.put(QUANTITY,cartmodel.getQuantity());
            obj.put(IMGURL,cartmodel.getImageurl());
            array.put(obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        editor = pref.edit();
        editor.putString(CART_KEY, array.toString());
        editor.commit();
    }
    public List getCartItem() {
        List<cartModel> cartModelList=new ArrayList<>();
        String json = pref.getString(CART_KEY, null);
        if(json !=null){
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i <array.length() ; i++) {
                    JSONObject job=array.getJSONObject(i);
                    cartModel model=new cartModel(job.getString(PRODUCTID),job.getString(NAME),job.getString(COLOR),
                            job.getString(SIZE),job.getString(PRICE),job.getString(QUANTITY),job.getString(IMGURL));
                    cartModelList.add(model);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  cartModelList;
    }
   /* JSONObject obj = new JSONObject();
            try {
        obj.put("id", "3");
        obj.put("name", "NAME OF STUDENT");
        obj.put("year", "3rd");
        obj.put("curriculum", "Arts");
        obj.put("birthday", "5/5/1993");

    } catch (JSONException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    JSONArray js=new JSONArray(obj.toString());
    JSONObject obj2 = new JSONObject();
             obj2.put("student", js.toString());*/
}
