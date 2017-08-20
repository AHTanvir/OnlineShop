package anwar.onlineshop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
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

import static anwar.onlineshop.R.id.*;

import anwar.onlineshop.Fragment.CategoryFragment;
import anwar.onlineshop.Fragment.CyclicTransitionDrawable;
import anwar.onlineshop.Fragment.HomeFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView collapsing_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        HomeFragment homeFragment=new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(Relative_layoutfor_fragments,
                homeFragment, homeFragment.getTag()).commit();
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
    public void imageTransition(View view){
        collapsingToolbarLayout=(CollapsingToolbarLayout)this.findViewById(R.id.collapsingtool);
        collapsing_image=(ImageView)this.findViewById(R.id.toolbart_product_img);
        Drawable[] layer=new Drawable[4];
        layer[0]= ContextCompat.getDrawable(this.getApplicationContext(),R.drawable.w6);
        layer[1]=ContextCompat.getDrawable(this.getApplicationContext(),R.drawable.watch);
        layer[2]=ContextCompat.getDrawable(this.getApplicationContext(),R.drawable.women6);
        layer[3]=ContextCompat.getDrawable(this.getApplicationContext(),R.drawable.women3);
        CyclicTransitionDrawable ctd=new CyclicTransitionDrawable(layer);
        collapsing_image.setImageDrawable(ctd);
        ctd.startTransition(1000, 3000);
       /* TransitionDrawable transition=new TransitionDrawable(layer);
        collapsing_image.setImageDrawable(transition);
        collapsing_image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        transition.setCrossFadeEnabled(true);
        transition.startTransition(1000);*/

    }
}
