package com.example.android.jagriti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DefenceTricks extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_defence_tricks );

        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        webView=(WebView)findViewById( R.id.webview );
        webView.setWebViewClient( new WebViewClient() );
        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setDomStorageEnabled( true );
        webView.setOverScrollMode( WebView.OVER_SCROLL_NEVER );
        webView.loadUrl( "https://brightside.me/inspiration-tips-andtricks/14-self-defence-tips-might-save-your-life-387260/" );
    }
}
