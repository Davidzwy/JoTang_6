package com.example.jotang.MyFragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jotang.MyCircleView;
import com.example.jotang.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TimeFragment extends Fragment implements Runnable{
    private TextView textView;
    private Handler handler;
    private MyCircleView myCircleView;
    private TextView mtext;
    private Button mBtnStart;

    private Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBtnStart = view.findViewById(R.id.start);
        myCircleView = view.findViewById(R.id.circle);
        mtext = view.findViewById(R.id.met);

        textView=view.findViewById(R.id.time);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                textView.setText((String)msg.obj);
            }
        };
        new Thread(this).start();

        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //随机数
                Random random = new Random();
                int js = random.nextInt(1560);
                int last = random.nextInt(360) + 360;
                //旋转动画
                RotateAnimation rotateAnimation = new RotateAnimation(0, js + last, myCircleView.getmWidth() / 2, myCircleView.getmWidth() / 2);
                //旋转时间
                rotateAnimation.setDuration(3000);
                //保留最后执行完的位置
                rotateAnimation.setFillAfter(true);
                //启动动画
                myCircleView.startAnimation(rotateAnimation);
                String str = "";
                if((js+last)%360 <30||(js+last)%360>330) str = "计组";
                else if((js+last)%360 < 90 && (js+last)%360 >=30) str = "概率论";
                else if((js+last)%360 < 150 && (js+last)%360 >=90) str = "C语言";
                else if((js+last)%360 < 210 && (js+last)%360 >=150) str = "线性代数";
                else if((js+last)%360 < 270 && (js+last)%360 >=180) str = "微积分";
                else  str = "软工";

                String finalStr = str;
                mHandler = new Handler(){
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        switch (msg.what){
                            case 1:
                                Toast.makeText(getActivity(), "恭喜获得"+ finalStr, Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                }.start();

                }
            });
        }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            while(true){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
                String str=sdf.format(new Date());
                handler.sendMessage(handler.obtainMessage(100,str));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
