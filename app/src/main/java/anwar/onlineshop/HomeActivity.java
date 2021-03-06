package anwar.onlineshop;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static anwar.onlineshop.R.id.*;

import anwar.onlineshop.Fragment.CartFragment;
import anwar.onlineshop.Fragment.CategoryFragment;
import anwar.onlineshop.Fragment.CyclicTransitionDrawable;
import anwar.onlineshop.Fragment.HomeFragment;
import anwar.onlineshop.Fragment.OrderFragment;
import anwar.onlineshop.Fragment.ViewFragment;
import anwar.onlineshop.api.EndPoints;
import anwar.onlineshop.api.FakeProducts;
import anwar.onlineshop.api.JsonObjectUtil;
import anwar.onlineshop.api.JsonRequest;
import anwar.onlineshop.storage.SharedPref;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView collapsing_image;
    private AppBarLayout appbar;
    private boolean isVisible;
    private Toolbar toolbar;
    private RelativeLayout cart_relativeLayout;
    private ImageButton btn_open_cart;
    private PopupWindow popupWindow;
    private TextView item_quantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cart_relativeLayout=(RelativeLayout)findViewById(R.id.cart_relative_layout);
        btn_open_cart=(ImageButton)findViewById(R.id.img_btn_cart);
        HomeFragment homeFragment=new HomeFragment();
        OrderFragment orderFragment=new OrderFragment();
        ViewFragment v=new ViewFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                homeFragment, homeFragment.getTag()).commit();
        SharedPref sharedPref=new SharedPref(this);
        int i;
        if((i=sharedPref.getCartQuantity())!=0)
            ShowAndHide(i);
        btn_open_cart.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager=getSupportFragmentManager();;
        HomeFragment homeFragment;
        CategoryFragment categoryFragment;
        int id = item.getItemId();
        switch (item.getItemId()){
            case R.id.nav_home:
                for (int i = 0; i <fragmentManager.getBackStackEntryCount() ; i++) {
                    fragmentManager.popBackStack();
                }
                homeFragment=new HomeFragment();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        homeFragment, homeFragment.getTag()).commit();
                break;
            case R.id.nav_mycart:
                CartFragment c=new CartFragment();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        c, c.getTag()).addToBackStack(null).commit();
                break;
            case R.id.nav_men:
                for (int i = 0; i <fragmentManager.getBackStackEntryCount()-1 ; i++) {
                    fragmentManager.popBackStack();
                }
                 categoryFragment=new CategoryFragment().newInstance("MEN","");
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        categoryFragment, categoryFragment.getTag()).commit();
                break;
            case R.id.nav_women:
                for (int i = 0; i <fragmentManager.getBackStackEntryCount()-1 ; i++) {
                    fragmentManager.popBackStack();
                }
                categoryFragment=new CategoryFragment().newInstance("WOMEN","");
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        categoryFragment, categoryFragment.getTag()).commit();
                break;
            case R.id.nav_address:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_exit:
                finish();
                break;
        }
/*        if (id == R.id.nav_home) {
                FragmentManager fragmentManager=getSupportFragmentManager();
                for (int i = 0; i <fragmentManager.getBackStackEntryCount() ; i++) {
                    fragmentManager.popBackStack();
                }
                HomeFragment homeFragment=new HomeFragment();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        homeFragment, homeFragment.getTag()).commit();
        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void imageTransition(View view,Activity activity,String titel){
        settingUpActionBar(view,titel);
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsingtool);
        collapsing_image=(ImageView)view.findViewById(R.id.toolbart_product_img);
        FakeProducts fakeProducts=new FakeProducts();
        Drawable[] layer=fakeProducts.getHeaderImage(activity);
        CyclicTransitionDrawable ctd=new CyclicTransitionDrawable(layer);
        collapsing_image.setImageDrawable(ctd);
        ctd.startTransition(1000, 3000);
       /* TransitionDrawable transition=new TransitionDrawable(layer);
        collapsing_image.setImageDrawable(transition);
        collapsing_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        transition.setCrossFadeEnabled(true);
        transition.startTransition(1000);*/

    }
    public void settingUpActionBar(View view,String titel){
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titel);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void ShowAndHide(int quantity){
        item_quantity=(TextView)findViewById(R.id.cart_quantity);
        if (!isVisible && quantity !=0){
            cart_relativeLayout.setVisibility(View.VISIBLE);
            item_quantity.setText(String.valueOf(quantity));
            isVisible=true;
        }
        else if(quantity==0){
            cart_relativeLayout.setVisibility(View.GONE);
            isVisible=false;
        } else  item_quantity.setText(String.valueOf(quantity));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_btn_cart:
                CartFragment c=new CartFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                        c, c.getTag()).addToBackStack(null).commit();
                break;
        }
    }
    public void progressDialog(View view,int x,int y){
        popupWindow = new PopupWindow(this);
        LayoutInflater inflater=getLayoutInflater();
        View v=inflater.inflate(R.layout.dot_progressbar, null);
        popupWindow.setFocusable(false);
        popupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(v);
        popupWindow.setOutsideTouchable(false);
        popupWindow.showAtLocation(view, Gravity.CENTER,x,y);
    }
}
