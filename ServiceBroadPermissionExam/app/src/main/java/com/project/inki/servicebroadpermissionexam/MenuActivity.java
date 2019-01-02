package com.project.inki.servicebroadpermissionexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        textView = (TextView) findViewById(R.id.textView1);
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("name","kimkinki");
                setResult(200,intent);
                finish();
            }
        });
        Intent intent=getIntent();          //메인에서 보낸 인텐트 객체를 받는다.
        processIntent(intent);              //새로 정의한 processIntent를 호출하면서 intent를 파라메터값으로 전달.
    }

    private void processIntent(Intent intent){
        if(intent!=null){
            Bundle bundle=intent.getExtras();   //intent를 getExtras()로 반환하면 Bundle객체를 참조

            SimpleData data=(SimpleData)bundle.getParcelable("Key");    //Bundle안에있는 SimpleData 객체를 참조

            textView.setText("number: "+data.getNumber()+"\n message: "+data.getMessage());
        }
    }
}
