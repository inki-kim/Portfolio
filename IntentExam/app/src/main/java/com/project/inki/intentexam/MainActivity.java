package com.project.inki.intentexam;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;

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
                Button openButton=(Button) linearLayout.findViewById(R.id.openButton);

                final EditText inputPdfName=(EditText) linearLayout.findViewById(R.id.inputPdfName);

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

                openButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String filename = inputPdfName.getText().toString();    //PDF파일명을 가져와 filename에 저장

                        if (filename.length() > 0) {                          //filename에 어떤것이 적혀져있을경우 openPdf라는 메소드에 파일명을 파라메터로 전달하며 호출
                            openPdf(filename.trim());
                        }
                        else{                                                 //다시입력
                            Toast.makeText(getApplicationContext(),"pdf파일명을 다시 입력하세요.",Toast.LENGTH_LONG).show();
                        }
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

    private void openPdf(String filename){                          //pdf를 열기위한 메소드
        String sdcardFolder=Environment.getExternalStorageDirectory().getAbsolutePath();         //내장메모리 파일의 절대경로 지정
        String filepath=sdcardFolder+ File.separator+filename;                                  //원하는 파일을 열기위한 "/" 추가와 파일명 추가작업
        Toast.makeText(getApplicationContext(),filepath,Toast.LENGTH_LONG).show();
        File file=new File(filepath);                           //파일의 경로로 파일객체를 생성

        if(file.exists()){                                      //파일이 존재할경우
            Uri uri = Uri.fromFile(file);     //Uri 객체로 생성

            Intent intent=new Intent(Intent.ACTION_VIEW);              //액션을 지정한 인텐트 생성
            intent.setDataAndType(uri,"application/pdf");       //uri객체와 MIME 타입 지정

            try {
                startActivity(intent);
            }catch(ActivityNotFoundException ex){
                Toast.makeText(getApplicationContext(),"pdf파일을 보기위한 뷰어가 없습니다.",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"pdf파일이 없습니다.",Toast.LENGTH_LONG).show();
        }
    }
}
