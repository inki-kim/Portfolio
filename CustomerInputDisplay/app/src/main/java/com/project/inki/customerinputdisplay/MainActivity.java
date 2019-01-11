package com.project.inki.customerinputdisplay;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import java.util.Date;

import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {
    EditText input_name, input_age;
    Button input_birth, save;
    SimpleDateFormat simpleDateFormat;
    String birtInfomation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_name = findViewById(R.id.input_name);
        input_age = findViewById(R.id.input_age);
        input_birth = findViewById(R.id.input_birth);

        save = findViewById(R.id.save);

        final Date date = new Date();

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        input_birth.setText(simpleDateFormat.format(date));

        input_birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(MainActivity.this);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이름:" + input_name.getText().toString() + "\n나이:" + input_age.getText().toString() + "\n생년월일:" + birtInfomation, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void createDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.activity_alertdialog);
        dialog.show();

        final EditText editText1 = (EditText) dialog.findViewById(R.id.editText1);
        final EditText editText2 = (EditText) dialog.findViewById(R.id.editText2);
        final EditText editText3 = (EditText) dialog.findViewById(R.id.editText3);

        Button button1 = (Button) dialog.findViewById(R.id.button1);
        Button button2 = (Button) dialog.findViewById(R.id.button2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                birtInfomation = editText1.getText().toString() + "-" + editText2.getText().toString() + "-" + editText3.getText().toString();
                input_birth.setText(birtInfomation);
                dialog.cancel();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }
}
