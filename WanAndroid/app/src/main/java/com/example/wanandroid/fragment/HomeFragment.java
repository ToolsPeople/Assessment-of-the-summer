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

import com.bumptech.glide.Glide;
import com.example.wanandroid.AgentWebActivity;
import com.example.wanandroid.R;
import com.example.wanandroid.RegisterActivity;
import com.example.wanandroid.bean.BannerBean;
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

public class HomeFragment extends Fragment implements OnBannerListener {
    private List<String>  listPath;
    private List<String>  listTitle;
    private  Banner banner;
    private BannerBean bannerBean;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,container,false);
        banner  = view.findViewById(R.id.banner);
        textView  = view.findViewById(R.id.banner_tv);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.wanandroid.com/banner/json").build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Message message = new Message();
                message.what = 2;
                handler.sendMessage(message);
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    String responseDate = response.body().string();

                    Gson gson  =  new Gson();

                    bannerBean =  gson.fromJson(responseDate,BannerBean.class);
                    listPath  = new ArrayList<>();
                    listTitle = new ArrayList<>();
                    for (int i = 0;i<3;i++){
                        listPath.add(bannerBean.getData().get(i).getImagePath());
                        listTitle.add(bannerBean.getData().get(i).getTitle());
                    }
                    banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                    banner.setImageLoader(new MyLoader());
                    banner.setImages(listPath);
                    banner.setBannerTitles(listTitle);
                    banner.setDelayTime(2000);
                    banner.isAutoPlay(true);
                    banner.setIndicatorGravity(BannerConfig.CENTER);
                    banner.setOnBannerListener(HomeFragment.this);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                }
            }
        });
        return view;
    }
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 1){
                banner.start();
            }else if (msg.what == 2){
                textView.setText("请连接网络");
                Toast.makeText(getContext(), "网络未连接", Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    });


    @Override
    public void OnBannerClick(int position) {
        Uri uri = Uri.parse(bannerBean.getData().get(position).getUrl());
        Intent intent = new Intent(getContext(), AgentWebActivity.class);
        intent.putExtra("Uri",uri.toString());
        startActivity(intent);
    }


    private class MyLoader implements ImageLoaderInterface{

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load((String) path).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }
}
