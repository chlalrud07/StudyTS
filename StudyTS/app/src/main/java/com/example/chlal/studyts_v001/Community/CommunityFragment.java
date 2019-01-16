package com.example.chlal.studyts_v001.Community;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.chlal.studyts_v001.Constant;
import com.example.chlal.studyts_v001.MainActivity;
import com.example.chlal.studyts_v001.R;
import com.example.chlal.studyts_v001.Study.StudyFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CommunityFragment extends Fragment {
    ArrayList<String> postTitle = new ArrayList<String>();
    ArrayList<String> postDetail = new ArrayList<String>();
    ListView postListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);
        CommunityConnection connection = new CommunityConnection();
        connection.execute();

        this.postListView = rootView.findViewById(R.id.post_board);
        return rootView;
    }

    private class CommunityConnection extends AsyncTask<JSONObject, JSONObject, JSONArray> {

        HttpURLConnection connection;
        JSONArray response;

        @Override
        protected JSONArray doInBackground(JSONObject[] objects) {
            try {
                URL url = new URL(Constant.POST_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(false);
                connection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder buffer = new StringBuilder();
                while ((line = reader.readLine()) != null) buffer.append(line);

                response = new JSONArray(buffer.toString().trim());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return this.response;
        }

        @Override
        protected void onPostExecute(JSONArray response) {
            try {
                for (int i=0; i<response.length(); i++) {
                    postTitle.add(response.getJSONObject(i).getString("title"));
                    postDetail.add(response.getJSONObject(i).getString("author_id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CustomListAdapter adapter = new CustomListAdapter(getContext(), generateItemsList());
            postListView.setAdapter(adapter);
        }
    }
    private ArrayList<Post> generateItemsList() {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < postTitle.size(); i++) {
            posts.add(new Post(postTitle.get(i), postDetail.get(i)));
        }
        return posts;
    }
}