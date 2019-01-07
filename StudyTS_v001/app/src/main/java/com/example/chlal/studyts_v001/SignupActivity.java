package com.example.chlal.studyts_v001;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("회원가입") ;
    }

    public void checkEmail(View v){ //이메일 중복검사 구현해야함

    }

    public void register(View v){ // 모든 필드 채워지고, 조건이 맞으면 회원가입되고 signin으로 돌아가도록 수정
        Intent intent_signIn = new Intent(getApplicationContext(), SigninActivity.class);
        startActivity(intent_signIn);
    }
}

