package com.example.chlal.studyts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class SigninActivity extends AppCompatActivity {
    // attributes
    private EditText email_text;
    private EditText password_text;
    private Button signin_button;
    private Button signup_button;
    private Button forgot_button;

    // request queue
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ActionBar ab = getSupportActionBar();
        ab.hide();

        email_text = (EditText) findViewById(R.id.email_text);
        password_text = (EditText) findViewById(R.id.password_text);
        signin_button = (Button) findViewById(R.id.signin_button);
        signup_button = (Button) findViewById(R.id.signup_button);
        forgot_button = (Button) findViewById(R.id.forgot_button);
        requestQueue = Volley.newRequestQueue(this);

        String url = "http://122.36.157.234:8000";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("TRUE")) {
                    signIn();
                } else {
                    Toast.makeText(SigninActivity.this, "Not matched email or password", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email_text.getText().toString());
                params.put("password", password_text.getText().toString());
                return params;
            }
        };

        stringRequest.setTag("LOGIN");
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);
            }
        });
    }

    public void signUp(View v){
        Intent intent_signUp = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent_signUp);
    }

    public void signIn(){ // 회원정보 일치하면 메인으로 넘어가도록 수정해야함
        Intent intent_signIn = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent_signIn);
    }

    public void forgot(View v){ // 비밀번호 찾기 액티비티 아직 안만듦

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll("LOGIN");
        }
    }
}
