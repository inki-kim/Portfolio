package com.project.inki.webviewdisplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {
    Animation animation_left, animation_right;
    LinearLayout menulinear;
    Button move;
    EditText editText;
    LinearLayout.LayoutParams params;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");

        final WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());


        move = findViewById(R.id.move);
        editText = findViewById(R.id.editText);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://" + editText.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);

        View v = menu.findItem(R.id.actionbar).getActionView();

        animation_left = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_left);
        animation_right = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation_right);
        menulinear = findViewById(R.id.layout1);

        final Button button = v.findViewById(R.id.down);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText().toString().equals("오픈")) {
                    menulinear.setVisibility(View.VISIBLE);
                    menulinear.startAnimation(animation_left);
                    menulinear.bringToFront();
                    button.setText("닫기");
                } else if (button.getText().toString().equals("닫기")) {
                    menulinear.setVisibility(View.INVISIBLE);
                    menulinear.startAnimation(animation_right);
                    button.setText("오픈");
                }
            }
        });
        return true;
    }


}
