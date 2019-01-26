package com.example.chlal.studyts_v001.Study;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chlal.studyts_v001.Study.CustomListAdapter;
import com.example.chlal.studyts_v001.Community.Post;
import com.example.chlal.studyts_v001.Constant;
import com.example.chlal.studyts_v001.R;
import com.example.chlal.studyts_v001.Study.Part.StudyPart1Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StudyFragment extends Fragment {
    ArrayList<String> postID = new ArrayList<String>();
    ArrayList<String> postTitle = new ArrayList<String>();
    ArrayList<String> postDetail = new ArrayList<String>();
    ListView postListView;
    CustomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_study, container, false);
        CommunityConnection connection = new CommunityConnection();
        connection.execute();

        this.postListView = rootView.findViewById(R.id.items_board);
        postListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(getContext(), StudyPart1Activity.class);
                intent.putExtra("pid", adapter.getPostID(pos));
                startActivity(intent);
            }
        });
        return rootView;
    }

    private class CommunityConnection extends AsyncTask<JSONObject, JSONObject, JSONArray> {

        HttpURLConnection connection;
        JSONArray response;

        @Override
        protected JSONArray doInBackground(JSONObject[] objects) {
            try {
                URL url = new URL(Constant.PART1_INDEX_URL);
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
                System.out.println(response);
                for (int i=0; i<response.length(); i++) {
                    postID.add(response.getJSONObject(i).getString("item_id"));
                    postTitle.add(response.getJSONObject(i).getString("item_name"));
                    postDetail.add(response.getJSONObject(i).getString("subpart"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter = new CustomListAdapter(getContext(), generateItemsList());
            adapter.notifyDataSetChanged();
            postListView.setAdapter(adapter);
        }
    }
    private ArrayList<Item> generateItemsList() {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < postTitle.size(); i++) {
            items.add(new Item(postID.get(i), postTitle.get(i), postDetail.get(i)));
        }
        return items;
    }
}