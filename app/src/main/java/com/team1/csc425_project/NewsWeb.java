package com.team1.csc425_project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class NewsWeb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        String url = null;
        if (extras != null) {
            url = extras.getString("urlString");
        }

        WebView newsWebView = (WebView)findViewById(R.id.newsWebView);
        newsWebView.getSettings().setJavaScriptEnabled(true);

        Toast urlplz = Toast.makeText(getApplicationContext(),url,Toast.LENGTH_SHORT);
        urlplz.show();

        newsWebView.setWebViewClient(new WebViewClient());

        newsWebView.loadUrl(url);

    }

}
