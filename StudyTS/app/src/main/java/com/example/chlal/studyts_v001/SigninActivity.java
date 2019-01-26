package com.example.chlal.studyts_v001;

import android.Manifest;
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


import com.example.chlal.studyts_v001.Study.Part.StudyPart1Activity;

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
        Util.requestPermission(this, Manifest.permission.RECORD_AUDIO);
        Util.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Util.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        mEmail = findViewById(R.id.email_text);
        mPassword = findViewById(R.id.password_text);

    }
    public void signIn(View v) throws IOException, JSONException {
        JSONObject userInfo = new JSONObject();
        userInfo.put("username", mEmail.getText().toString());
        userInfo.put("password", mPassword.getText().toString());

        @SuppressLint("StaticFieldLeak")
        JsonConnection connection = new JsonConnection(Constant.SIGN_IN_URL) {
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                try {
                    System.out.println("                                                                          "+jsonObject);
                    if (jsonObject == null) {
                        Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_LONG).show();
                    } else if (jsonObject.getString("message").equals("Exist")) {
                        SharedPreferences.Editor editor = getSharedPreferences("SESSION", MODE_PRIVATE).edit();
                        editor.putString("username", mEmail.getText().toString());
                        editor.apply();
                        Intent intent = new Intent();
                        if (jsonObject.getString("permission").equals("user")) {
                            intent = new Intent(getApplicationContext(), MainActivity.class);
                        } else {
                            intent = new Intent(getApplicationContext(), AdminActivity.class);
                        }
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "해당 계정이 없습니다.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        connection.execute(userInfo);
    }

    public void signUp(View v){
        Intent intent_signUp = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent_signUp);
    }

    public void forgot(View v){ // 비밀번호 찾기 액티비티 아직 안만듦

    }
}
