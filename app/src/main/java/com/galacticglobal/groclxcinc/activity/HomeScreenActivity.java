package com.galacticglobal.groclxcinc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.fragment.BasketFragment;
import com.galacticglobal.groclxcinc.fragment.CategoryFragment;
import com.galacticglobal.groclxcinc.fragment.HomeFragment;
import com.galacticglobal.groclxcinc.fragment.OffersFragments;
import com.galacticglobal.groclxcinc.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.SecureRandom;

public class HomeScreenActivity extends BaseActivity implements View.OnClickListener {

    public static BottomNavigationView navigation;

    FrameLayout frame_layout;
    Activity activity;
    public static DrawerLayout drawer;
    private ActionBarDrawerToggle t;
    private boolean doubleBackToExitPressedOnce = false;
    String orderSession = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        orderSession = HelperClass.getOrderSession(HomeScreenActivity.this);
        if (orderSession != null && !orderSession.isEmpty()) {
            // Do Nothing
        } else {
            HelperClass.setOrderSession(randomNo(10) + "_" + randomString(32), HomeScreenActivity.this);
        }
        init();
    }

    private void init() {
        activity = HomeScreenActivity.this;
        drawer = findViewById(R.id.drawer);
        frame_layout = findViewById(R.id.frame_layout);
        navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.action_home);
        t = new ActionBarDrawerToggle(this, drawer, R.string.Open, R.string.Close);
        drawer.addDrawerListener(t);

    }



    private void bottombar(Fragment fragment) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();


    }

    public static void openCloseDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);

        }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.action_home:
                    fragment = new HomeFragment();
                    bottombar(fragment);
                    return true;
                case R.id.action_category:
                    fragment = new CategoryFragment();
                    bottombar(fragment);
                    return true;
                case R.id.action_discover:
                    fragment = new SearchFragment();
                    bottombar(fragment);
                    return true;


                case R.id.action_basket:
                    fragment = new BasketFragment();
                    bottombar(fragment);
                    return true;
            }
            return false;
        }
    };


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

}
