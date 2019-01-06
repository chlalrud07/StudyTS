package com.example.chlal.studyts_v001;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private EditText mNickname;
    private TextView mExist;

    private boolean isAvailable = false;
    private RequestQueue queue;

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
        queue = Volley.newRequestQueue(this);
    }

    public void checkEmail(View v){
        if (!isValidEmail(mEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "이메일 형식이 올바르지 않습니다.", Toast.LENGTH_LONG).show();
            return;
        }
        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("email", mEmail.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest object = new JsonObjectRequest(Request.Method.POST, Constant.CHECK_EMAIL_URL, userInfo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                queue.cancelAll(Constant.SIGN_UP_URL);
                System.out.println(response);
                try {
                    String message = response.getString("message");

                    if (message.equals("exist")) {
                        mExist.setText("이미 등록되어 있는 이메일 입니다.");
                    } else {
                        isAvailable = true;
                        mExist.setText("사용 가능한 이메일 입니다.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_LONG).show();
            }
        });
        object.setTag(Constant.CHECK_EMAIL_URL);
        queue.add(object);
    }

    public void register(View v){
        queue.cancelAll(Constant.CHECK_EMAIL_URL);
        JSONObject userInfo = new JSONObject();
        if (isAvailable && isValidPassword(mPassword.getText().toString()) && mPassword.getText().toString().equals(mPasswordConfirm.getText().toString())) {
            try {
                userInfo.put("email", mEmail.getText().toString());
                userInfo.put("password", mPassword.getText().toString());
                userInfo.put("nickname", mNickname.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (!isAvailable) {
            Toast.makeText(getApplicationContext(), "이메일 중복검사를 실행해주십시오.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "비밀번호를 확인해주십시오.(6자리 이상)", Toast.LENGTH_LONG).show();
        }

        JsonObjectRequest object = new JsonObjectRequest(Request.Method.POST, Constant.SIGN_UP_URL, userInfo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Intent intent_signIn = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(intent_signIn);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        object.setTag(Constant.SIGN_UP_URL);
        queue.add(object);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }
}

