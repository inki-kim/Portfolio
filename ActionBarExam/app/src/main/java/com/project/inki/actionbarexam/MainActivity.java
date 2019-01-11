package com.project.inki.actionbarexam;


import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,fragment1).commit();

        TabLayout tabLayout=findViewById(R.id.tab);
        tabLayout.addTab(tabLayout.newTab().setText("통화기록"));
        tabLayout.addTab(tabLayout.newTab().setText("스팸기록"));
        tabLayout.addTab(tabLayout.newTab().setText("웹뷰"));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos=tab.getPosition();

                Fragment select=null;
                if(pos==0){
                    select=fragment1;
                }else if(pos==1){
                    select=fragment2;
                }else if(pos==2){
                    select=fragment3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout,select).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
