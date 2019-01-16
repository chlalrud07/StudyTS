package com.example.chlal.studyts_v001;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private EditText mNickname;
    private TextView mExist;

    private Button mCheck;
    private boolean isAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("회원가입") ;

        mEmail = findViewById(R.id.email_text);
        mPassword = findViewById(R.id.pw_text);
        mPasswordConfirm = findViewById(R.id.pwagain_text);
        mNickname = findViewById(R.id.nickname_text);
        mExist = findViewById(R.id.exist_error_textView);
        mCheck = findViewById(R.id.check_button);
    }

    public void checkEmail(View v) {
        if (!isValidEmail(mEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_LONG).show();
            return;
        }

        isAvailable = true;

        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("email", mEmail.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SignupConnection signupConnection = new SignupConnection(Constant.CHECK_EMAIL_URL, userInfo);
        signupConnection.execute();
    }

    public void register(View v){
        if (checkValidPassword(mPassword.getText().toString())
                || checkValidPassword(mPasswordConfirm.getText().toString())
                || !mPassword.getText().toString().equals(mPasswordConfirm.getText().toString())) {
            Toast.makeText(getApplicationContext(), "비밀번호를 확인해주십시오. (6자리 이상)", Toast.LENGTH_LONG).show();
            return;
        } else if (!isAvailable) {
            Toast.makeText(getApplicationContext(), "CHECK를 눌러주십시요.", Toast.LENGTH_LONG).show();
            return;
        }

        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("email", mEmail.getText().toString());
            userInfo.put("password", mPassword.getText().toString());
            userInfo.put("nickname", mNickname.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SignupConnection signupConnection = new SignupConnection(Constant.SIGN_UP_URL, userInfo);
        signupConnection.execute();
    }

    private class SignupConnection extends AsyncTask<JSONObject, JSONObject, JSONObject> {
        private URL url;
        private JSONObject json;
        private JSONObject response = null;

        SignupConnection(String url, JSONObject json) {
            try {
                this.url = new URL(url);
                this.json = json;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            try {
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
            } catch (IOException | JSONException e) {
                e.printStackTrace();
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
                    Toast.makeText(getApplicationContext(), "존재하는 이메일입니다.", Toast.LENGTH_LONG).show();
                    mCheck.setBackgroundColor(Color.rgb(238, 221, 170));
                } else if (message.getString("message").equals("NotExist")) {
                    mExist.setText("사용가능한 이메일입니다.");
                    mCheck.setBackgroundColor(Color.rgb(170, 221, 238));
                } else if (message.getString("message").equals("Failed")) {
                    Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "회원가입에 성공했습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), SigninActivity.class);
                    startActivity(intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean checkValidPassword(String password) {return password.length() < 6;}
    private boolean isValidEmail(String email) {return email.contains("@") && email.contains(".");}
}

