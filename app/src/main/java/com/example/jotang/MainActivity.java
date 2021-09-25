package com.example.jotang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static boolean islogin = false;

    private Button mBtnWeb;
    private Button mBtnApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnWeb = findViewById(R.id.web);
        mBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,web.class);
                startActivity(intent);
            }
        });
        mBtnApp = findViewById(R.id.app);

        setListeners();
    }
    private void setListeners(){
        OnClick onClick = new OnClick();
        mBtnApp.setOnClickListener(onClick);
    }
    private class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch(view.getId()){
                case R.id.app: intent = new Intent(MainActivity.this, MyAppActivity.class);break;
            }
            startActivity(intent);
        }
    }
}