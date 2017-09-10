package anwar.onlineshop.api;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import anwar.onlineshop.Fragment.CyclicTransitionDrawable;
import anwar.onlineshop.Model.ProductModel;
import anwar.onlineshop.Model.RowItem;
import anwar.onlineshop.R;

/**
 * Created by anwar on 9/3/2017.
 */

public class FakeProducts {
    private Context context;

 /*   public FakeProducts(Context context) {
        this.context = context;
    }*/

    public List getHomeCategories(){
        List<RowItem> rowItems=new ArrayList<>();
        RowItem row=new RowItem(R.drawable.men_shop,"MEN");
        rowItems.add(row);
        RowItem rowItem=new RowItem(R.drawable.h5,"WOMEN");
        rowItems.add(rowItem);
        RowItem row2=new RowItem(R.drawable.home_category,"KID");
        rowItems.add(row2);
        RowItem rowIte=new RowItem(R.drawable.home_category,"OTHER");
        rowItems.add(rowIte);

        return rowItems;
    }
    public List getBestSellerProducts(){
        List<ProductModel> productList=new ArrayList<>();
        ProductModel ro0 = new ProductModel("001","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w1));
        productList.add(ro0);
        ProductModel ro11 = new ProductModel("002","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp2));
        productList.add(ro11);
        ProductModel ro2 = new ProductModel("003","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w4));
        productList.add(ro2);
        ProductModel ro00 = new ProductModel("004","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms6));
        productList.add(ro00);
        ProductModel ro111 = new ProductModel("005","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_6));
        productList.add(ro111);
        ProductModel ro22 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_14));
        productList.add(ro22);
        return productList;
    }
    public List getProductType(String name){
        List<RowItem> rowItems=new ArrayList<>();
        if(name.equals("MEN")){
            RowItem r=new RowItem("PANT","2");
            rowItems.add(r);
            RowItem r1=new RowItem("SHART","12");
            rowItems.add(r1);
            RowItem r12=new RowItem("T-SHART","10");
            rowItems.add(r12);
        }else if (name.contains("WOMEN")){
            RowItem r=new RowItem("WATCH","2");
            rowItems.add(r);
            RowItem r1=new RowItem("BAG","0");
            rowItems.add(r1);
            RowItem r12=new RowItem("T-SHART","6");
            rowItems.add(r12);
        }
        return rowItems;
    }
    public List getWomenProductType(){
        List<RowItem> rowItems=new ArrayList<>();
        RowItem r=new RowItem("WATCH","2");
        rowItems.add(r);
        RowItem r1=new RowItem("BAG","0");
        rowItems.add(r1);
        RowItem r12=new RowItem("T-SHART","6");
        rowItems.add(r12);
        return rowItems;
    }
    public List getRecomProduct(){
        List<ProductModel> productList=new ArrayList<>();
        ProductModel ro0 = new ProductModel("001","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w2));
        productList.add(ro0);
        ProductModel ro11 = new ProductModel("002","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp7));
        productList.add(ro11);
        ProductModel ro2 = new ProductModel("003","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms1));
        productList.add(ro2);
        ProductModel ro00 = new ProductModel("004","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp3));
        productList.add(ro00);
        ProductModel ro111 = new ProductModel("005","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms7));
        productList.add(ro111);
        ProductModel ro22 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms8));
        productList.add(ro22);
        return productList;
    }
    public List getProductList(String category, String name){
        List<ProductModel> productList=new ArrayList<>();
        if(category.equals("MEN"))
        {
            if(name.equals("PANT")){
                ProductModel ro0 = new ProductModel("001","PANT","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp2));
                productList.add(ro0);
                ProductModel ro11 = new ProductModel("002","PANT","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp3));
                productList.add(ro11);
                ProductModel ro2 = new ProductModel("003","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp4));
                productList.add(ro2);
                ProductModel ro00 = new ProductModel("004","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp6));
                productList.add(ro00);
                ProductModel ro111 = new ProductModel("005","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp7));
                productList.add(ro111);
                ProductModel ro22 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mp9));
                productList.add(ro22);
/*                ProductModel ro0 = new ProductModel("001","PANT","descriptio","large,medium,small","Black,Red,White","100","https://imgur.com/uwYn8G9.png");
                productList.add(ro0);
                ProductModel ro11 = new ProductModel("002","PANT","descriptio","large,medium,small","Black,Red,White","100","https://imgur.com/xfZZani.png");
                productList.add(ro11);
                ProductModel ro2 = new ProductModel("003","T-SHART","descriptio","large,medium,small","Black,Red,White","100","https://imgur.com/XjKaS9V.png");
                productList.add(ro2);
                ProductModel ro00 = new ProductModel("004","T-SHART","descriptio","large,medium,small","Black,Red,White","100","https://imgur.com/G4kFNjL.png");
                productList.add(ro00);
                ProductModel ro111 = new ProductModel("005","T-SHART","descriptio","large,medium,small","Black,Red,White","100","https://imgur.com/sntuRGw.png");
                productList.add(ro111);
                ProductModel ro22 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100","https://imgur.com/3uKhTzT.png");
                productList.add(ro22);*/
            }
            else if (name.equals("T-SHART")){
                ProductModel ro0 = new ProductModel("001","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mt3));
                productList.add(ro0);
                ProductModel ro11 = new ProductModel("002","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mt5));
                productList.add(ro11);
                ProductModel ro2 = new ProductModel("003","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mt6));
                productList.add(ro2);
                ProductModel ro00 = new ProductModel("004","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mt8));
                productList.add(ro00);
                ProductModel ro111 = new ProductModel("005","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mt9));
                productList.add(ro111);
                ProductModel ro22 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.mt6));
                productList.add(ro22);
            }
            else if (name.equals("SHART")){
                ProductModel ro0 = new ProductModel("001","SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms1));
                productList.add(ro0);
                ProductModel ro11 = new ProductModel("002","SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms7));
                productList.add(ro11);
                ProductModel ro2 = new ProductModel("003","SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms6));
                productList.add(ro2);
                ProductModel ro00 = new ProductModel("004","SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms8));
                productList.add(ro00);
                ProductModel ro111 = new ProductModel("005","SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms9));
                productList.add(ro111);
                ProductModel ro22 =new ProductModel("006","SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.ms6));
                productList.add(ro22);
            }
        }
        else if (category.equals("WOMEN")){
            if(name.equals("WATCH")){
                ProductModel ro2 = new ProductModel("003","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w1));
                productList.add(ro2);
                ProductModel ro = new ProductModel("002","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w2));
                productList.add(ro);
                ProductModel ro21 = new ProductModel("003","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w3));
                productList.add(ro21);
                ProductModel ro1 = new ProductModel("002","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w4));
                productList.add(ro1);
                ProductModel ro22 = new ProductModel("003","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w5));
                productList.add(ro22);
                ProductModel ro23 = new ProductModel("002","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w6));
                productList.add(ro23);
                ProductModel ro223 = new ProductModel("003","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w7));
                productList.add(ro223);
                ProductModel ro231 = new ProductModel("002","WATCH","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w6));
                productList.add(ro231);
            }
            else if (name.equals("T-SHART")){
                ProductModel ro0 = new ProductModel("001","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_14));
                productList.add(ro0);
                ProductModel ro11 = new ProductModel("002","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_6));
                productList.add(ro11);
                ProductModel ro2 = new ProductModel("003","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_9));
                productList.add(ro2);
                ProductModel ro00 = new ProductModel("004","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_10));
                productList.add(ro00);
                ProductModel ro111 = new ProductModel("005","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_11));
                productList.add(ro111);
                ProductModel ro22 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_12));
                productList.add(ro22);
                ProductModel ro223 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_15));
                productList.add(ro223);
                ProductModel ro2233 =new ProductModel("006","T-SHART","descriptio","large,medium,small","Black,Red,White","100",String.valueOf(R.drawable.w_t_13));
                productList.add(ro2233);
            }

        }
        return productList;
    }

    public Drawable[] getHeaderImage(Context activity){
        Drawable[] layer=new Drawable[6];
        layer[0]= ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.h);
        layer[1]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.h1);
        layer[2]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.h2);
        layer[3]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.h3);
        layer[4]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.h4);
        layer[5]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.h5);

     return layer;
    }
}

