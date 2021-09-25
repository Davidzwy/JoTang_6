package com.example.jotang.MyFragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jotang.R;

public class MyBlogActivity extends AppCompatActivity {
    private WebView mWv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blog);
        mWv = findViewById(R.id.blog);
        mWv.getSettings().setJavaScriptEnabled(true);
        mWv.loadUrl("https://davidzwy.github.io/");
    }
}