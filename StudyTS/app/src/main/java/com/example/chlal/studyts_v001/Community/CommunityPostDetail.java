package com.example.chlal.studyts_v001.Community;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.chlal.studyts_v001.Constant;
import com.example.chlal.studyts_v001.JsonConnection;
import com.example.chlal.studyts_v001.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class CommunityPostDetail extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post_detail);

        this.webView = findViewById(R.id.post_detail_webview);
        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                System.out.println("A : "+ url);
                System.out.println("B : "+ userAgent);
                System.out.println("C : "+ contentDisposition);
                System.out.println("D : "+ mimetype);
                System.out.println("E : "+ contentLength);

                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                String filename = URLUtil.guessFileName(url, contentDisposition, mimetype);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
                DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
            }
        });
        this.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (url.equals(Constant.POST_COMPLETE_URL)) {
                    finish();
                }
            }
        });

        this.webView.getSettings().setAllowFileAccess(true);
        this.webView.getSettings().setAllowFileAccess(true);

        this.webView.loadUrl(Constant.POST_DETAIL_URL+getIntent().getExtras().getString("pid")+"/");
    }
}
