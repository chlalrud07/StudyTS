package com.example.chlal.studyts_v001.Community;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.chlal.studyts_v001.MainActivity;
import com.example.chlal.studyts_v001.R;

public class CommunityPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void post(View v){//db에 저장 구현

    }

    public void attachment(View v){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.post_actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.update_post:
                Toast.makeText(this, "업로드 성공", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
