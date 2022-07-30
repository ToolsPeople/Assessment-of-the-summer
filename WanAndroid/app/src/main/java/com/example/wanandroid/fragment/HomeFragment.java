package com.example.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.wanandroid.Adapter.MyFragmentStateVpTitleAdapter;
import com.example.wanandroid.AgentWebActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.RegisterActivity;
import com.example.wanandroid.bean.BannerBean;
import com.example.wanandroid.ffragment.FriendFragment;
import com.example.wanandroid.ffragment.PageFragment;
import com.example.wanandroid.ffragment.UnknowFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.just.agentweb.AgentWeb;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {



    private ViewPager mViewpager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragmentList;
    private List<String> mTitleList;
    private MyFragmentStateVpTitleAdapter mStVpTitleAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,container,false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewpager = view.findViewById(R.id.home_vp);
        mTabLayout = view.findViewById(R.id.tab_layout);
        initData();
        mStVpTitleAdapter = new MyFragmentStateVpTitleAdapter(getChildFragmentManager(),mFragmentList,mTitleList);
        mViewpager.setAdapter(mStVpTitleAdapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        PageFragment pageFragment = new PageFragment();
        FriendFragment friendFragment = new FriendFragment();
        UnknowFragment unknowFragmen = new UnknowFragment();
        mFragmentList.add(pageFragment);
        mFragmentList.add(friendFragment);
        mFragmentList.add(unknowFragmen);

        mTitleList = new ArrayList<>();
        mTitleList.add("推荐");
        mTitleList.add("交流圈");
        mTitleList.add("更多");
    }


}


