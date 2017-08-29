package anwar.onlineshop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static anwar.onlineshop.R.id.*;
import static anwar.onlineshop.api.EndPoints.PRODUCTS;

import anwar.onlineshop.Fragment.CategoryFragment;
import anwar.onlineshop.Fragment.CyclicTransitionDrawable;
import anwar.onlineshop.Fragment.HomeFragment;
import anwar.onlineshop.Fragment.OrderFragment;
import anwar.onlineshop.api.EndPoints;
import anwar.onlineshop.api.JsonRequest;
import anwar.onlineshop.api.OkHttpStack;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView collapsing_image;
    private RequestQueue mRequestQueue;
    private final Context mContext = this;
    private int mStatusCode = 0;
    private AppBarLayout appbar;
    private boolean isVisible;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeFragment homeFragment=new HomeFragment();
        OrderFragment orderFragment=new OrderFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                homeFragment, homeFragment.getTag()).commit();
       //ShowAndHide();
        init();
       //volley();
    }
    public void init(){
        queue = Volley.newRequestQueue(this);
        String url =EndPoints.PRODUCTS;
        String u=" https://api.androidhive.info/contacts/";
        JsonRequest req = new JsonRequest(Request.Method.GET, u, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println("Respons = "+response);
                } catch (Exception e) {
                    System.out.println("Respons exception = "+response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("volleyError = "+error);

            }
        });
        req.setShouldCache(false);
        queue.add(req);
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
        int id = item.getItemId();
        if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void imageTransition(View view,Activity activity){
        toolbar = (Toolbar)view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        collapsingToolbarLayout=(CollapsingToolbarLayout)view.findViewById(R.id.collapsingtool);
        collapsing_image=(ImageView)view.findViewById(R.id.toolbart_product_img);
        Drawable[] layer=new Drawable[4];
        layer[0]= ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.w6);
        layer[1]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.watch);
        layer[2]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.women6);
        layer[3]=ContextCompat.getDrawable(activity.getApplicationContext(),R.drawable.women3);
        CyclicTransitionDrawable ctd=new CyclicTransitionDrawable(layer);
        collapsing_image.setImageDrawable(ctd);
        ctd.startTransition(1000, 3000);
       /* TransitionDrawable transition=new TransitionDrawable(layer);
        collapsing_image.setImageDrawable(transition);
        collapsing_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        transition.setCrossFadeEnabled(true);
        transition.startTransition(1000);*/

    }
    public void ShowAndHide(){
        if (!isVisible){
            getSupportActionBar().show();
            appbar.setVisibility(View.VISIBLE);
            isVisible=true;
        }else {
            fab.hide();
            appbar.setVisibility(View.GONE);
            getSupportActionBar().hide();
            isVisible=false;
        }
    }
}
