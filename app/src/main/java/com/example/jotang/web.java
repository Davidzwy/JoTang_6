package com.example.jotang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class web extends AppCompatActivity {
    private WebView mWvJo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWvJo = findViewById(R.id.guanwang);

        mWvJo.getSettings().setJavaScriptEnabled(true);
        mWvJo.loadUrl("https://d.jotang.club/t/topic/705/8");

    }
}