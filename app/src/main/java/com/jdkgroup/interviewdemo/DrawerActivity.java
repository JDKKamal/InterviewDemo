package com.jdkgroup.interviewdemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.customviews.fancydialoglib.Animation;
import com.jdkgroup.customviews.fancydialoglib.FancyAlertDialog;
import com.jdkgroup.customviews.fancydialoglib.FancyAlertDialogListener;
import com.jdkgroup.customviews.fancydialoglib.Icon;
import com.jdkgroup.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

public class DrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AppConstant {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private EventBus eventBus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        bindViews();
        eventBus.register(this);
        setSupportActionBar(toolBar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null)
        {
            AppUtils.launchFragmentActivity(getActivity(), LAUNCH_PROFILE_FRAGMENT, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            AppUtils.launchFragmentActivity(getActivity(), LAUNCH_PROFILE_FRAGMENT, null);
        } else if (id == R.id.nav_slideshow) {
            AppUtils.launchFragmentActivity(getActivity(), LAUNCH_CONTACT_FRAGMENT, null);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe
    public void onEvent(String event) {
        toolBar.setTitle(event);
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = this.getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 1) {
            appExist();
        } else {
            manager.popBackStack();
        }
    }
}
