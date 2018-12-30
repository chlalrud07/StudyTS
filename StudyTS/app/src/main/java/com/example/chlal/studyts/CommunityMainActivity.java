package com.example.chlal.studyts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CommunityMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_main);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("커뮤니티") ;
    }

    public void add(View v){
        Intent intent_add = new Intent(getApplicationContext(), CommunityWritingActivity.class);
        startActivity(intent_add);
    }
}
