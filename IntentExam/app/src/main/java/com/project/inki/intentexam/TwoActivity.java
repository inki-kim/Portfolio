package com.project.inki.intentexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TwoActivity extends AppCompatActivity {

    Button button1;
    EditText editText1;
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        button1=(Button)findViewById(R.id.button);
        editText1=(EditText)findViewById(R.id.editText1);
        textView1=(TextView)findViewById(R.id.textView1);


        Intent intent=getIntent();
        String mainNum=intent.getExtras().getString("data");
        textView1.setText(mainNum);                             //메인에서 단순히 데이터를 주고 받았을경우


        button1.setOnClickListener(new View.OnClickListener() {     //두번째 액티비티에서 결과값을 메인으로 다시 보내는 경우
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("resultData",editText1.getText().toString());
                setResult(RESULT_OK,intent);

                finish();
            }
        });
    }
}
