package com.example.chlal.studyts_v001.Community;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chlal.studyts_v001.Constant;
import com.example.chlal.studyts_v001.R;
import com.example.chlal.studyts_v001.Study.StudyFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CommunityPostActivity extends AppCompatActivity {

    // attributes
    private EditText mTitle;
    private EditText mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post);
        setTitle("New Post");
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
            PostConnection connection = new PostConnection();
            connection.execute();
        }
        return true;
    }
    private class  PostConnection extends AsyncTask<JSONObject, JSONObject, JSONObject> {
        JSONObject response;
        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            try {
                JSONObject json = new JSONObject();
                json.put("title", mTitle.getText().toString());
                json.put("content", mContent.getText().toString());
                json.put("username", getSharedPreferences("Session", MODE_PRIVATE).getString("username", ""));

                URL url = new URL(Constant.POST_UPLOAD_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.connect();

                OutputStream writer = connection.getOutputStream();
                writer.write(json.toString().getBytes());
                writer.flush();
                writer.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder buffer = new StringBuilder();
                while ((line = reader.readLine()) != null) buffer.append(line);

                response = new JSONObject(buffer.toString().trim());
            } catch(JSONException | IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                if (jsonObject.getString("message").equals("OK")) {
                    Toast.makeText(getApplicationContext(), "업로드 성공하였습니다.", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "업로드 실패", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            super.onPostExecute(jsonObject);
        }
    }
}
