package com.example.chlal.studyts;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    // attributes
    private EditText email_text;
    private EditText pw_text;
    private EditText pwagain_text;
    private EditText nickname_text;
    private Button register_button;

    //
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar ab = getSupportActionBar() ;
        ab.setTitle("회원가입") ;

        // init attributes
        email_text = (EditText) findViewById(R.id.email_text);
        pw_text = (EditText) findViewById(R.id.pw_text);
        pwagain_text = (EditText) findViewById(R.id.pwagain_text);
        nickname_text = (EditText) findViewById(R.id.nickname_text);
        register_button = (Button) findViewById(R.id.register_button);

        this.requestQueue = Volley.newRequestQueue(this);

        JSONObject userInfo = new JSONObject();
        try {
            userInfo.put("email", email_text.getText().toString());
            userInfo.put("pw", pw_text.getText().toString());
            userInfo.put("nickname", nickname_text.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://122.36.157.234:8000/register";
        final JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.POST, url, userInfo, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Msg :", "onResponse: "+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(jsonArrayRequest);
            }
        });
    }

    public void checkEmail(View v){ //이메일 중복검사 구현해야함

    }

    public void register(){ // 모든 필드 채워지고, 조건이 맞으면 회원가입되고 signin으로 돌아가도록 수정
//        Intent intent_signIn = new Intent(getApplicationContext(), SigninActivity.class);
//        startActivity(intent_signIn);
    }
}
