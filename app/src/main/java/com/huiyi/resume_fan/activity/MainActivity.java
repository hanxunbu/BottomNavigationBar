package com.huiyi.resume_fan.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.huiyi.resume_fan.fragment.HomeFragment;
import com.huiyi.resume_fan.fragment.ProductionFragment;
import com.huiyi.resume_fan.R;
import com.huiyi.resume_fan.fragment.ResumeFragment;
import com.huiyi.resume_fan.fragment.SkillFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private ArrayList<Fragment> fragments;
    private ViewPager root;
    private BottomNavigationBar bottomNavigationBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    //初始化
    private void initView() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        root = (ViewPager) findViewById(R.id.root);


        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.me, "个人").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.drawable.production, "作品").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.resume, "简历").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.drawable.skill, "技能").setActiveColorResource(R.color.brown))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        bottomNavigationBar.setTabSelectedListener(this);

        root.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 1:
                        return ProductionFragment.newInstance("作品");
                    case 2:
                        return ResumeFragment.newInstance("简历");
                    case 3:
                        return SkillFragment.newInstance("技能");
                }
                return HomeFragment.newInstance("个人");
            }

            @Override
            public int getCount() {
                return 4;
            }

        });

        root.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("个人"));
        fragments.add(ProductionFragment.newInstance("作品"));
        fragments.add(ResumeFragment.newInstance("简历"));
        fragments.add(SkillFragment.newInstance("技能"));
        return fragments;
    }


    @Override
    public void onTabSelected(int position) {
        root.setCurrentItem(position);

    }

    @Override
    public void onTabUnselected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment fragment = fragments.get(position);
                ft.remove(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}
