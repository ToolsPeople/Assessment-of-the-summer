package com.example.wanandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wanandroid.bean.RegisterErroBean;
import com.example.wanandroid.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "1111a";

    private EditText registerUsernameEdit;
    private EditText registerPasswordEdit;
    private EditText registerConfirmPasswordEdit;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initListener();

        Toolbar toolbar = (Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.returngo);
        }

    }

    private void initListener() {
        registerBtn.setOnClickListener(this);
    }


    public void registerWithOkHttp(String username, String password, String repassword) {
        HttpUtil.registerWithOkHttp(username, password, repassword, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegisterActivity.this, "??????????????????", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //???????????????????????????
                final String responseDate = response.body().string();
                //???Gson???????????????????????????
                Gson gson = new Gson();
                RegisterErroBean registerErroBean = gson.fromJson(responseDate, RegisterErroBean.class);
                //????????????????????????????????????Int????????????
                Integer w = new Integer(registerErroBean.getErrorCode());
                int i = w.intValue();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (i == -1) {
                            Toast.makeText(RegisterActivity.this, registerErroBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(RegisterActivity.this, "????????????", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
            }
        });
    }

    //???????????????
    private void initView() {
        registerUsernameEdit = findViewById(R.id.register_username);
        registerPasswordEdit = findViewById(R.id.register_password);
        registerConfirmPasswordEdit = findViewById(R.id.register_confirmpassword);
        registerBtn = findViewById(R.id.register_button);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_button:
                String registerUsername = registerUsernameEdit.getText().toString();
                String registerPassword = registerPasswordEdit.getText().toString();
                String registerConfirmPassword = registerConfirmPasswordEdit.getText().toString();
                registerWithOkHttp(registerUsername, registerPassword, registerConfirmPassword);
                break;
        }
    }

    //Toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        return true;
    }


}