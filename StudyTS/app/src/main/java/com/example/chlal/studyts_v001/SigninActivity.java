package com.example.chlal.studyts_v001;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SigninActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        mEmail = findViewById(R.id.email_text);
        mPassword = findViewById(R.id.password_text);

    }
    public void signIn(View v) {
        SigninConnection connection = new SigninConnection();
        connection.execute();
    }

    public void signUp(View v){
        Intent intent_signUp = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent_signUp);
    }

    public void forgot(View v){ // 비밀번호 찾기 액티비티 아직 안만듦

    }
    @SuppressLint("StaticFieldLeak")
    private class SigninConnection extends AsyncTask<JSONObject, JSONObject, JSONObject> {

        @Override
        protected JSONObject doInBackground(JSONObject[] objects) {
            JSONObject response = null;
            HttpURLConnection connection = null;
            try {
                JSONObject userInfo = new JSONObject();
                userInfo.put("email", mEmail.getText().toString());
                userInfo.put("password", mPassword.getText().toString());

                URL url = new URL(Constant.SIGN_IN_URL);

                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.connect();

                OutputStream writer = connection.getOutputStream();
                writer.write(userInfo.toString().getBytes());
                writer.flush();
                writer.close();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder buffer = new StringBuilder();
                while ((line = reader.readLine()) != null) buffer.append(line);

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
        protected void onPostExecute(JSONObject message) {
            try {
                System.out.println(message);
                if (message == null) {
                    Toast.makeText(getApplicationContext(), "에러가 발생했습니다.", Toast.LENGTH_LONG).show();
                } else if (message.getString("message").equals("Exist")) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    SharedPreferences session = getSharedPreferences("Session", MODE_PRIVATE);
                    SharedPreferences.Editor editor = session.edit();
                    editor.putString("username", message.getString("username"));
                    editor.putString("nickname", message.getString("nickname"));
                    editor.apply();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "해당 계정이 존재하지 않습니다.\n다시 확인해주십시오!", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
