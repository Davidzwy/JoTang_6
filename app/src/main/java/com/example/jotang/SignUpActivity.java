package com.example.jotang;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SignUpActivity extends AppCompatActivity {
//注册。假设这个单词是。
    private ImageView imageView;
    private EditText etname;
    private EditText etpwd1;
    private EditText etpwd2;
    private EditText ettele;
    private TextView tvsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        imageView = findViewById(R.id.myicon);
        etname = findViewById(R.id.username);
        etpwd1 = findViewById(R.id.password1);
        etpwd2 = findViewById(R.id.password2);
        ettele = findViewById(R.id.edtele);
        tvsend = findViewById(R.id.send);

        etname.setOnTouchListener(new View.OnTouchListener() {
            int count = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if (count== 2){ //按下，和离开算一次点击
                    count= 0; //还原计数
                }
                imageView.setImageResource(R.mipmap.user);
                return false;
            }
        });

        etpwd1.setOnTouchListener(new View.OnTouchListener() {
            int count = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if (count== 2){ //按下，和离开算一次点击
                    count= 0; //还原计数
                }
                imageView.setImageResource(R.mipmap.password);
                return false;
            }
        });

        etpwd2.setOnTouchListener(new View.OnTouchListener() {
            int count = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                count++;
                if (count== 2){ //按下，和离开算一次点击
                    count= 0; //还原计数
                }
                imageView.setImageResource(R.mipmap.password);
                return false;
            }
        });

        tvsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(etname.getText()) && !TextUtils.isEmpty(etpwd1.getText())&&!TextUtils.isEmpty(etpwd2.getText())){
                    if(!etpwd1.getText().toString().equals(etpwd2.getText().toString()) ){
                        Toast.makeText(SignUpActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                    }else{
                        User user = new User();
                        user.setName(etname.getText().toString());
                        user.setPassword(etpwd1.getText().toString());
                        user.setTelephone(ettele.getText().toString());

                        List<User> list = File.getUser(SignUpActivity.this);
                        Boolean flag = true;
                        for(int i = 0 ; i<list.size() ; i++){
                            if( list.get(i).getName().equals(user.getName()) )   flag = false;//名字重复 flag 为true
                        }
                        if(flag){
                            list.add(user);
                            if(user!=null) Toast.makeText(SignUpActivity.this, "注册成功,已用该账户登录", Toast.LENGTH_SHORT).show();
                            else Toast.makeText(SignUpActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            File.putUser(SignUpActivity.this,list);
                            finish();
                        }else {
                            Toast.makeText(SignUpActivity.this, "用户名重复", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else {
                    Toast.makeText(SignUpActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}