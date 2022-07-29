package com.example.wanandroid;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.Adapter.MyFragmentStateVPAdapter;
import com.example.wanandroid.fragment.CategoryFragment;
import com.example.wanandroid.fragment.HomeFragment;
import com.example.wanandroid.fragment.MineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private MyFragmentStateVPAdapter mStateVPAdapter;
    private List<Fragment> mFragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();
        initData();
        mStateVPAdapter =  new MyFragmentStateVPAdapter(getSupportFragmentManager(),mFragmentList);
        mViewPager.setAdapter(mStateVPAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPagerSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //点击底部导航栏时绑定fragment滑动，双向绑定
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.menu_home:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_find:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_mine:
                        mViewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    //滑动viewpager时，带动底部导航栏
    private void onPagerSelected(int position) {
        switch (position){
            case 0 :
                mBottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;
            case 1 :
                mBottomNavigationView.setSelectedItemId(R.id.menu_find);
                break;
            case 2:
                mBottomNavigationView.setSelectedItemId(R.id.menu_mine);
                break;
            default:
                break;
        }
    }

    private void initData() {
        mFragmentList =  new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        CategoryFragment categoryFragment =  new CategoryFragment();
        MineFragment mineFragment = new MineFragment();
        mFragmentList.add(homeFragment);
        mFragmentList.add(categoryFragment);
        mFragmentList.add(mineFragment);
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_menu);
    }
}