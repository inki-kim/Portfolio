package com.project.inki.actionbarexam;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.util.zip.Inflater;

public class Fragment3 extends Fragment {
    private Handler handler = new Handler();
    private WebView webView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);

        Button button = viewGroup.findViewById(R.id.button);
        final EditText editText = viewGroup.findViewById(R.id.editText);
        webView = viewGroup.findViewById(R.id.webview);


        WebSettings webSettings = webView.getSettings();          //웹뷰 설정하기 위해 getSettings로 받아옴
        webSettings.setJavaScriptEnabled(true);

        webView.setWebChromeClient(new WebBrowserClient());      //웹뷰에 클라이언트 객체 지정
        webView.addJavascriptInterface(new JavaScriptMethods(), "sample");
        webView.loadUrl("file:///android_asset/sample.html");

        button.setOnClickListener(new View.OnClickListener() {          //버튼클릭시 EditText에 입력한 url 로딩
            @Override
            public void onClick(View v) {
                webView.loadUrl("http://"+editText.getText().toString());
            }
        });
        return viewGroup;
    }

    final class JavaScriptMethods {
        JavaScriptMethods() {
        }

        @android.webkit.JavascriptInterface
        public void clickOnFace() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:changeFace()");
                }
            });
        }
    }


    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();

            return true;
        }
    }
}
