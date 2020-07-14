package com.example.newsapi;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewer extends AppCompatActivity {

    WebView webView;
    TextView txt_load;
    String value;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=(WebView)findViewById(R.id.webView);
        txt_load=(TextView)findViewById(R.id.txt_load);
        value = getIntent().getExtras().getString("URL");
        final ProgressBar pb = (ProgressBar) findViewById(R.id.pb);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    // Update the progress status
                    progressStatus +=1;

                    // Try to sleep the thread for 20 milliseconds
                    try{
                        Thread.sleep(80);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(progressStatus);
                            // Show the progress on TextView
//                            tv.setText(progressStatus+"");
                            Log.i("Value",String.valueOf(progressStatus));
                            // If task execution completed
                            if(progressStatus == 100){
                                // Set a message of completion
                                //tv.setText("Operation completed.");
                                Toast.makeText(WebViewer.this, "Ready To Go !!", Toast.LENGTH_SHORT).show();
                                webView.setVisibility(View.VISIBLE);
                                txt_load.setVisibility(View.INVISIBLE);
                                displayWebView();
                            }
                        }
                    });
                }
            }
        }).start(); // Start the operation


    }
    public void displayWebView(){

        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(value);
        // this will enable the javascipt.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());

    }
}
