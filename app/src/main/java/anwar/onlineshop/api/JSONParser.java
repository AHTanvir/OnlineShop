package anwar.onlineshop.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Model.CategoryModel;
import anwar.onlineshop.Model.ProductCategoryModel;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.consts;

/**
 * Created by anwar on 8/24/2017.
 */

public class JSONParser {
    private int networktaskType;
    private JSONObject jsonResponse;
    private List<RowItem> list;

    public JSONParser() {
    }

    public List parseProductList(JSONObject jsonResponse) {
        List<ProductModel> list=new ArrayList<>();
        try {
            JSONArray array=jsonResponse.getJSONArray("products");
            for (int i = 0; i <array.length() ; i++) {
                JSONObject j=array.getJSONObject(i);
                ProductModel p=new ProductModel(j.getString("productid"),j.getString("name"),j.getString("details"),
                        j.getString("size"),j.getString("color"),j.getString("price"),j.getString("imageurl"));
                list.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List parseCategory(JSONObject jsonResponse) {
        List<CategoryModel> list=new ArrayList<>();
        try {
            JSONArray array=jsonResponse.getJSONArray("category");
            for (int i = 0; i <array.length() ; i++) {
                JSONObject j=array.getJSONObject(i);
                CategoryModel p=new CategoryModel(j.getString("imageurl"),j.getString("name"));
                list.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    public List parseProductCategory(JSONObject jsonResponse) {
        List<ProductCategoryModel> list=new ArrayList<>();
        try {
            JSONArray array=jsonResponse.getJSONArray("category");
            for (int i = 0; i <array.length() ; i++) {
                JSONObject j=array.getJSONObject(i);
                ProductCategoryModel p=new ProductCategoryModel(j.getString("name"),j.getString("total"));
                list.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
