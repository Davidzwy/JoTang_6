package com.example.jotang;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.jotang.MyFragments.BroadFragment;
import com.example.jotang.MyFragments.FragmentAdapter;
import com.example.jotang.MyFragments.IntroFragment;
import com.example.jotang.MyFragments.TimeFragment;

public class LoginActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener {
    //常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    private RadioGroup radioGroup;
    //布局
    private RadioButton rbTime;
    private RadioButton rbIntro;
    private RadioButton rbBroad;

    //fragment
    private TimeFragment timeFragment;
    private BroadFragment broadFragment;
    private IntroFragment introFragment;
    //viewpager
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("未登录").setMessage("请游客用户先注册")
                .setPositiveButton("登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(LoginActivity.this,SignInActivity.class);
                        startActivity(intent);
                    }
                }).setNeutralButton("注册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        }).setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        if(!MainActivity.islogin)   builder.show();

        //不显示标题
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();

        }

        Toast.makeText(LoginActivity.this, "加载完成", Toast.LENGTH_SHORT).show();
        //设置适配器
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        initFragment();
    }

    private void initFragment(){
        rbIntro = findViewById(R.id.intro);
        rbTime = findViewById(R.id.time);
        rbBroad = findViewById(R.id.broad);
        radioGroup = findViewById(R.id.rb_group);
        radioGroup.setOnCheckedChangeListener(this);

        viewPager = findViewById(R.id.frame_container);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.intro:
                viewPager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.time:
                viewPager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.broad:
                viewPager.setCurrentItem(PAGE_THREE);
                break;
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {
//state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (viewPager.getCurrentItem()) {
                case PAGE_ONE:
                    rbIntro.setChecked(true);
                    break;
                case PAGE_TWO:
                    rbTime.setChecked(true);
                    break;
                case PAGE_THREE:
                    rbBroad.setChecked(true);
                    break;
            }
        }
    }
}