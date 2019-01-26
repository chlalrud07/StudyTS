package com.example.chlal.studyts_v001;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonConnection extends AsyncTask<JSONObject, JSONObject, JSONObject> {
    private HttpURLConnection connection;

    public JsonConnection(String url) throws IOException {
        URL urls = new URL(url);
        connection = (HttpURLConnection) urls.openConnection();
    }

    @Override
    protected JSONObject doInBackground(JSONObject[] json) {
        JSONObject response = null;
        try {
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            OutputStream writer = connection.getOutputStream();
            writer.write(json[0].toString().getBytes());
            writer.flush();
            writer.close();

            response = this.getResponse();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return response;
    }

    public JSONObject getResponse() throws JSONException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuilder buffer = new StringBuilder();
        while ((line = reader.readLine()) != null) buffer.append(line);

        return new JSONObject(buffer.toString().trim());
    }
}
