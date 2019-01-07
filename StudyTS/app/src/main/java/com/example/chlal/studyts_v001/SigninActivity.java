package com.example.chlal.studyts_v001;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        mEmail = findViewById(R.id.email_text);
        mPassword = findViewById(R.id.password_text);
        queue = Volley.newRequestQueue(this);

    }
    public void signIn(View v) {
        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("email", mEmail.getText().toString());
            userInfo.put("password", mPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest object = new JsonObjectRequest(Request.Method.POST, Constant.SIGN_IN_URL, userInfo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("message").equals("Exist")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "계정을 찾을 수 없습니다.", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "에러 발생", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(object);
    }

    public void signUp(View v){
        Intent intent_signUp = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent_signUp);
    }

    public void forgot(View v){ // 비밀번호 찾기 액티비티 아직 안만듦

    }
}
