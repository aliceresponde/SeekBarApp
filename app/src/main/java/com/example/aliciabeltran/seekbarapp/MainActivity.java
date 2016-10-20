package com.example.aliciabeltran.seekbarapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.example.aliciabeltran.seekbarapp.R.color.redColor;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView tvID;

    LinearLayout linearLayoutRoot;
    ToggleButton toggleButton;

    ImageView imageView;

    WebView webView;
    EditText etURL;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tvID = (TextView) findViewById(R.id.tvId);
        linearLayoutRoot = (LinearLayout) findViewById(R.id.root);
        toggleButton = (ToggleButton) findViewById(R.id.toggleB);
        imageView = (ImageView) findViewById(R.id.imgV);
        webView = (WebView)findViewById(R.id.webV);
        etURL = (EditText) findViewById(R.id.etURL);


        clickOnSeekBar();
        setOnToggleClick();
        loadURLinWebView();

    }

    /**
     * You must to specify, a WebViewClient if you want to deploy the URL inside your WebView,
     * avoiding do it in your phone navigator.
     * Because by default Google.com will be opened by phone navegator
     *
     * TODO :  check Cannot call determinedVisibility() - never saw a connection for the PID #
     */
    private void loadURLinWebView() {
        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        //webView.loadUrl("http://www.google.com");
    }

    private void setOnToggleClick() {
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    linearLayoutRoot.setBackgroundColor(Color.GREEN);
                } else {
                    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //    linearLayoutRoot.setBackgroundColor(MainActivity.this.getColor(redColor));
                    //}
                    linearLayoutRoot.setBackgroundColor(getResources().getColor(R.color.redColor));
                }
            }
        });
    }

    private void clickOnSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvID.setText("Value : " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    /**
     * this method is referenced  from layout
     * @param view
     */
    public void showTW(View view) {
        imageView.setImageResource(R.mipmap.ic_tw);
    }

    public void showFB(View view) {
        imageView.setImageResource(R.mipmap.ic_fb);
    }

    public void goButton(View view) {
        Log.d("click", "GO");
        String strUrl = etURL.getText().toString();
        try {
        webView.loadUrl(strUrl);
        }catch (Exception e){
            Log.e("Error loading url", e.getMessage());
        }
    }

    public void reloadButton(View view) {
        Log.d("click", "RELOAD");
        webView.reload();
    }

    public void forwardButton(View view) {
        Log.d("click", "FORWARD");
        if (webView.canGoForward()){
            webView.goForward();
        }else{
            Toast.makeText(this, "No more Forward", Toast.LENGTH_SHORT).show();
        }
    }

    public void backButton(View view) {
        Log.d("click", "BACK");
        if (webView.canGoBack()){
            webView.canGoBack();
        }
        else{
            Toast.makeText(this, "No more back", Toast.LENGTH_SHORT).show();
        }
    }
}
