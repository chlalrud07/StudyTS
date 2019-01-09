package com.example.chlal.studyts_v001.Community;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.chlal.studyts_v001.R;

import org.json.JSONException;
import org.json.JSONObject;

public class CommunityPostActivity extends AppCompatActivity {

    // attributes
    private EditText mTitle;
    private EditText mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mTitle = findViewById(R.id.title_editText);
        mContent = findViewById(R.id.content_editText);

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
        if (item.getItemId() == R.id.update_post) {
            JSONObject json = new JSONObject();
            try {
                json.put("title", mTitle.getText().toString());
                json.put("content", mContent.getText().toString());
            } catch(JSONException e) {
                e.printStackTrace();
            }

            System.out.println(getSharedPreferences("Session", MODE_PRIVATE).getString("nickname", ""));
        }
        return true;
    }
}
