package com.example.jotang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MyAppActivity extends AppCompatActivity {

    private ImageView imageView;
    private ClipDrawable clipDrawable;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg)
        {
            if(msg.what == 1)
            {
                //修改ClipDrawable的level值
                clipDrawable.setLevel(clipDrawable.getLevel() +50);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        imageView = findViewById(R.id.image);
        clipDrawable = (ClipDrawable)imageView.getDrawable();

        final Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                Message msg = new Message();
                msg.what = 1;
                //发送消息,通知应用修改ClipDrawable对象的level值 截取区域的大小
                handler.sendMessage(msg);
                //取消定时器
                if(clipDrawable.getLevel() >= 10000)
                {
                    timer.cancel();
                    Intent intent = new Intent(MyAppActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },0,20);

    }
}