package anwar.onlineshop.api;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Adapter.RowItem;
import anwar.onlineshop.consts;

/**
 * Created by anwar on 8/24/2017.
 */

public class JSONParser {
    private int networktaskType;
    private JSONObject jsonResponse;
    private List<RowItem> list;

    /**
     * @param networktaskType
     * @param jsonResponse
     */
    public JSONParser(int networktaskType, JSONObject jsonResponse) {
        this.networktaskType = networktaskType;
        this.jsonResponse = jsonResponse;
    }

    public List parse() {

        if (jsonResponse != null) {
            list=new ArrayList<>();
            try {
                switch (networktaskType) {
                    case consts.PRODUCTLIST:
                        JSONArray jsonArray=jsonResponse.getJSONArray("Productlist");
                        for(int i=0; i<jsonArray.length(); i++) {
                            JSONObject jsonObj= jsonArray.getJSONObject(i);
                            RowItem rowItem=new RowItem(jsonObj.getString("product_id"),jsonObj.getString("name"),
                                    jsonObj.getString("price"),jsonObj.getString("size"),jsonObj.getString("color"),
                                    jsonObj.getString("url"));
                            list.add(rowItem);
                        }
                        break;

                    default:
                        break;
                }

                // TODO parse JSON acc to request
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
        return list;
    }
}
