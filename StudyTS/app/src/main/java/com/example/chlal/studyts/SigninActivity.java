package com.example.chlal.studyts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        ActionBar ab = getSupportActionBar();
        ab.hide();
    }

    public void signUp(View v){
        Intent intent_signUp = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent_signUp);
    }

    public void signIn(View v){ // 회원정보 일치하면 메인으로 넘어가도록 수정해야함
        Intent intent_signIn = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_signIn);
    }

    public void forgot(View v){ // 비밀번호 찾기 액티비티 아직 안만듦

    }
}
