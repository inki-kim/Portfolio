package com.project.inki.servicebroadpermissionexam;

import android.Manifest;
import android.app.Activity;
import android.app.MediaRouteButton;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button, button2;
    TextView textView;
    EditText editText1;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "OnStart 호출", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "OnStop 호출", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "OnDestroy 호출", Toast.LENGTH_SHORT).show();
        prefClear();
    }

    public void prefClear() {                                                                                    //액티비티가 종료될때 저장된 데이터 값 초기화
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "OnPause 호출", Toast.LENGTH_SHORT).show();
        saveInformation();
    }

    public void saveInformation() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);                   //액티비티가 갑자기 중지되었을때 값 저장
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", editText1.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "OnResume 호출", Toast.LENGTH_SHORT).show();
        restoreInformation();
    }

    public void restoreInformation() {                                                                          //액티비티가 다시 시작될때 값 씌우기
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if ((pref != null) && (pref.contains("name"))) {
            String name = pref.getString("name", "");
            editText1.setText(name);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {               //두번째 액티비티에서 요청값 반응값 데이터를 보냈을때 받는 메소드. startActivityForResult로 값을 보내고 setResult로 값 받아옴
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            textView = (TextView) findViewById(R.id.textView2);
            textView.setText("요청값:" + requestCode + "\n반응값:" + resultCode + "\n데이타값:" + data.getExtras().getString("name"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {                                                    //앱이 실행될때 자동으로 호출되는 메소드
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "OnCreate 호출", Toast.LENGTH_SHORT).show();
        editText1 = (EditText) findViewById(R.id.editText1);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "SMS 수신 권한 있음.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "SMS 수신 권한 없음.", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                Toast.makeText(getApplicationContext(), "SMS 권한 설명 필요.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 1);
            }
        }

        button = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                                               //첫번째 버튼 눌렀을때의 작동 (두번째액티비티)
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                SimpleData simpleData = new SimpleData(400, "안녕하세요.");  //Parcelable 인터페이스를 구현하여 만든 SimpleData로 객체를 생성
                intent.putExtra("Key", simpleData);                              //생성한 객체를 이용해 putExtra로 MenumActivity에 전달
                startActivityForResult(intent, 100);                        //만든 인텐트를 시작함과 동시에 요청코드 전달
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {                         //두번째 버튼 눌렀을때의작동 (서비스액티비티)
            @Override
            public void onClick(View v) {
                String data = editText1.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MyService.class);
                intent.putExtra("Key", data);
                startService(intent);                                                   //startService메소드를 이용하여 data값을 가지고 서비스 시작
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "SMS 권한을 사용자가 승인", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS 권한 거부", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onNewIntent(Intent intent) {                         //서비스에서 보낸 데이터를 받기위한 메소드 메인이 이미 메모리에 올라가있을땐 이메소드로 받음
        proIntent(intent);
        Toast.makeText(this, "onNewIntent호출", Toast.LENGTH_SHORT).show();
        super.onNewIntent(intent);
    }

    public void proIntent(Intent intent) {
        if (intent != null) {
            String receiveData = intent.getStringExtra("str");
            Toast.makeText(this, receiveData, Toast.LENGTH_SHORT).show();
        } else {
        }
    }
}
