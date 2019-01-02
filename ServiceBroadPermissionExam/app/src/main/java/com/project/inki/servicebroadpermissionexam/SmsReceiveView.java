package com.project.inki.servicebroadpermissionexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsReceiveView extends AppCompatActivity {
    EditText editText1, editText2, editText3;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receive_view);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        Bundle bundle = intent.getExtras();
        String sender = bundle.getString("sender");
        String contents = bundle.getString("contents");
        String receiveDate = bundle.getString("receiveDate");


        editText1.setText(sender);
        editText2.setText(contents);
        editText3.setText(receiveDate);
    }
}
