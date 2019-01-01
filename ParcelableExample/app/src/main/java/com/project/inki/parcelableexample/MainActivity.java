package com.project.inki.parcelableexample;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==200){
            textView=(TextView)findViewById(R.id.textView2);
            textView.setText("요청값:"+requestCode+"\n반응값:"+resultCode+"\n데이타값:"+data.getExtras().getString("name"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MenuActivity.class);
                SimpleData simpleData=new SimpleData(400,"안녕하세요.");  //Parcelable 인터페이스를 구현하여 만든 SimpleData로 객체를 생성
                intent.putExtra("Key",simpleData);                              //생성한 객체를 이용해 putExtra로 MenumActivity에 전달
                startActivityForResult(intent,100);                        //만든 인텐트를 시작함과 동시에 요청코드 전달
            }
        });
    }
}
