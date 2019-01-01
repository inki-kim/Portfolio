package com.project.inki.intentexam;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Button button;
    CheckBox checkBox;
    public static final int REQUEST_CODE_MENU = 101;  //어떤 액티비티에서 전달받았는지 확인할수있는 상수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.Linear1);
        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  //부분화면 인플레이터 과정
                layoutInflater.inflate(R.layout.sub1, linearLayout, true);

                checkBox = (CheckBox) linearLayout.findViewById(R.id.checkBox1);
                checkBox.setText("Check값 확인");

                Button button2 = (Button) linearLayout.findViewById(R.id.finish);
                Button button1 = (Button) linearLayout.findViewById(R.id.newActivity);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), TwoActivity.class);  //새로운창을 띄우기
                        intent.putExtra("data", "Check Box 상태 : " + checkBox.isChecked());
                        startActivityForResult(intent, REQUEST_CODE_MENU);

                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {  //요청코드,결과코드,데이터를 받아올수있는 메소드
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            Toast.makeText(getApplicationContext(), "요청코드 : " + requestCode + "\n결과 코드 :  " + resultCode, Toast.LENGTH_LONG).show();
        }

        if (resultCode == RESULT_OK) {
            String name = data.getExtras().getString("resultData");
            Toast.makeText(getApplicationContext(), "입력된 값 : " + name, Toast.LENGTH_LONG).show();

            if (name.equals("체크")) {
                checkBox.setChecked(true);
            } else if (name.equals("언체크")) {
                checkBox.setChecked(false);
            }
        }
    }

}
