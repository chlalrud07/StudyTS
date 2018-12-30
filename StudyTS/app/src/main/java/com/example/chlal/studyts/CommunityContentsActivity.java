package com.example.chlal.studyts;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CommunityContentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_contents);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("커뮤니티") ;
    }
}
