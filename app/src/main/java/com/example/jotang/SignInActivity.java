package com.example.jotang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.SigningInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
//登录。英语不好，。
    private TextView mTvSignin;
    private EditText mEtname;
    private EditText mEtPwd;
    private ImageView checkImg;
    private EditText mycheck;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mTvSignin = findViewById(R.id.send);
        mEtname = findViewById(R.id.username);
        mEtPwd = findViewById(R.id.password1);
        checkImg = findViewById(R.id.checkcode);
        mycheck = findViewById(R.id.mycode);

        checkImg.setImageBitmap(CodeUtils.getInstance().createBitmap());

        checkImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkImg.setImageBitmap(CodeUtils.getInstance().createBitmap());
            }
        });

        mTvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<User> list = File.getUser(SignInActivity.this);
                boolean flag = false;
                int key = 0;

                for(int i = 0;i< list.size();i++){
                    if( list.get(i).getName().equals( mEtname.getText().toString() ) ){
                        flag = true;
                        key = i;
                    }
                }
                if(flag){
                    //有用户
                    if(list.get(key).getPassword().equals(mEtPwd.getText().toString())){
                        mycheck.setVisibility(View.VISIBLE);
                        checkImg.setVisibility(View.VISIBLE);


                        if( mycheck.getText().toString().equals( CodeUtils.getInstance().code.toLowerCase() ) ){
                            Toast.makeText(SignInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            count++;
                            if(count !=0)
                                Toast.makeText(SignInActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                            mycheck.setText("");
                        }

                    }else{
                        Toast.makeText(SignInActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(SignInActivity.this, "未找到该用户，请注册", Toast.LENGTH_SHORT).show();

            }
        });
    }
}