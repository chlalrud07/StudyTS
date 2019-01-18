package com.example.chlal.studyts_v001.Community;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.chlal.studyts_v001.Constant;
import com.example.chlal.studyts_v001.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class CommunityPostDetail extends AppCompatActivity {
    private TextView mTitle;
    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post_detail);

        this.mTitle = findViewById(R.id.postDetailTitle);
        this.mContent = findViewById(R.id.postDetailContent);
        JSONObject pid = new JSONObject();
        try {
            pid.put("post_id", getIntent().getExtras().getString("pid"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        PostConnection connection = new PostConnection();
        connection.execute(pid);
    }

    private class PostConnection extends AsyncTask<JSONObject, JSONObject, JSONObject> {

        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            JSONObject response = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(Constant.POST_DETAIL_URL);

                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                OutputStream writer = connection.getOutputStream();
                writer.write(jsonObjects[0].toString().getBytes());
                writer.flush();
                writer.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder buffer = new StringBuilder();
                while ((line = reader.readLine()) != null) buffer.append(line);

                System.out.println(buffer.toString().trim());
                response = new JSONObject(buffer.toString().trim());
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            return response;
        }
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {
                mTitle.setText(jsonObject.getString("title"));
                mContent.setText(jsonObject.getString("content"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
