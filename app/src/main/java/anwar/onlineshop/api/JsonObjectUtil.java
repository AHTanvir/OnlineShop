package anwar.onlineshop.api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import anwar.onlineshop.Model.AddressModel;
import anwar.onlineshop.Model.CartModel;

/**
 * Created by anwar on 9/6/2017.
 */

public class JsonObjectUtil {
   public JSONObject CreateOrderObject(ArrayList<CartModel> cardModel, AddressModel amodel){
      JSONObject jsonObject=new JSONObject();
      try {
         System.out.println("addreess name "+amodel.getName());
         JSONArray jsonArray=new JSONArray();
         JSONObject object=new JSONObject();
         object.put("name",amodel.getName());
         object.put("address",amodel.getAddress());
         object.put("city",amodel.getCity());
         object.put("email",amodel.getEmail());
         object.put("phone",amodel.getPhone());
         jsonArray.put(object);
         jsonObject.accumulate("Address",jsonArray);
         jsonArray=new JSONArray();
         for (int i = 0; i <cardModel.size(); i++) {
            object=new JSONObject();
            object.put("productid",cardModel.get(i).getProductid());
            object.put("name",cardModel.get(i).getName());
            object.put("size",cardModel.get(i).getSize());
            object.put("color",cardModel.get(i).getColor());
            object.put("price",cardModel.get(i).getPrice());
            object.put("quantity",cardModel.get(i).getQuantity());
            object.put("imageurl",cardModel.get(i).getImageurl());
            jsonArray.put(object);
         }
         jsonObject.accumulate("Order",jsonArray);

      } catch (JSONException e) {
         e.printStackTrace();
      }
      return jsonObject;
              /*
         //jsonObject parser
         try{
            JSONArray ja=jsonObject.getJSONArray("Address");
            for (int i = 0; i <ja.length() ; i++) {
               JSONObject j=ja.getJSONObject(i);
               System.out.println("Address "+j.getString("name")+"  "+j.getString("address")+"  "+j.getString("city")
                       +"  "+j.getString("email")+"  "+j.getString("phone") );
            }
            ja=jsonObject.getJSONArray("Order");
            for (int i = 0; i <ja.length() ; i++) {
               JSONObject j=ja.getJSONObject(i);
               System.out.println("Order "+j.getString("productid")+"  "+j.getString("name")+"  "+j.getString("size")
                       +"  "+j.getString("color")+"  "+j.getString("price")+"  "+j.getString("quantity")+"  "+j.getString("imageurl") );
            }
         } catch (JSONException e) {
            e.printStackTrace();
         }*/
   }
}
