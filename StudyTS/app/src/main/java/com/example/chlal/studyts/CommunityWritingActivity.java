package com.example.chlal.studyts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CommunityWritingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_writing);

        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("게시글 작성") ;
    }

    public void attachment(View v){ // 음성파일 첨부 구현

    }

    public void post(View v){ // 제목 내용이 모두 채워지면 db에 저장하고 community main으로 돌아감
        Intent intent_post = new Intent(getApplicationContext(), CommunityMainActivity.class);
        startActivity(intent_post);
    }
}
