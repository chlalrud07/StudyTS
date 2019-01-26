package com.example.chlal.studyts_v001.Community;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.chlal.studyts_v001.Constant;
import com.example.chlal.studyts_v001.R;

import javax.xml.datatype.Duration;

public class CommunityPostActivity extends AppCompatActivity {
    private ValueCallback<Uri[]> mFilePathCallback;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post);
        setTitle("New Post");

        // attributes
        WebView webView = findViewById(R.id.new_form);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(true);

        webView.setWebChromeClient(new MyChromeClient());
        webView.setWebViewClient(new MyClient());
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        webView.loadUrl(Constant.POST_NEW_URL+getSharedPreferences("SESSION", MODE_PRIVATE).getString("username", "")+"/");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constant.FILE_SELECT_CODE) {
            if (mFilePathCallback == null) return;
            mFilePathCallback.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
            mFilePathCallback=null;
        }
    }

    private class MyChromeClient extends WebChromeClient{
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            if (mFilePathCallback != null) {
                mFilePathCallback.onReceiveValue(null);
            }
            mFilePathCallback = filePathCallback;
            System.out.println(mFilePathCallback);

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, Constant.FILE_SELECT_CODE);
            return true;
        }
    }
    private class MyClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            if (url.equals(Constant.POST_COMPLETE_URL)) {
                finish();
            } else if (url.equals(Constant.COMMUNITY_ERROR_URL)){
                System.out.println("+===+++=+++==+++===+++=+++==+++===+++=+++==++ERROR+===+++=+++==+++===+++=+++==+++===+++=+++==++");
                Toast.makeText(getApplicationContext(), "업로드 실패", Toast.LENGTH_LONG*3);
                finish();
            }
        }
    }
}
