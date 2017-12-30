package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.interviewdemo.R;
import com.jdkgroup.interviewdemo.fragment.FragmentTab;

import butterknife.BindView;

public class TabNoScrollActivity extends BaseActivity {

    public static TabNoScrollActivity instance;

    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private FragmentTab fragmentContact;
    private FragmentTab fragmentFriend;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_no_scroll);
        bindViews();
        instance = this;

        getAllWidgets();
        bindWidgetsWithAnEvent();
        setupTabLayout();
    }

    public static TabNoScrollActivity getInstance() {
        return instance;
    }

    private void getAllWidgets() {
        tabLayout = findViewById(R.id.tabLayout);
    }

    private void setupTabLayout() {
        fragmentContact = new FragmentTab();
        fragmentFriend = new FragmentTab();

        tabLayout.addTab(tabLayout.newTab().setText("Friend"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Contact"));
    }

    private void bindWidgetsWithAnEvent() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                toolBar.getMenu().clear();
                switch (tab.getPosition()) {
                    case 0:
                        toolBar.inflateMenu(R.menu.menu_one);
                        toolBar.setTitle("Friend");
                        break;
                    case 1:
                        toolBar.inflateMenu(R.menu.menu_two);
                        toolBar.setTitle("Contact");
                        break;
                }

                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setCurrentTabFragment(int tabPosition) {
        switch (tabPosition) {
            case 0:
                replaceFragment(fragmentContact);
                break;
            case 1:
                replaceFragment(fragmentFriend);
                break;
        }
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frame_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_one, menu);
        return true;
    }
}
