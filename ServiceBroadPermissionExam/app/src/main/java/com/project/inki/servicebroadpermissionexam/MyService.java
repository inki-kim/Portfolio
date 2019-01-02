package com.project.inki.servicebroadpermissionexam;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyServicce";

    public MyService() {
    }

    @Override
    public void onCreate() {                  //서비스가 생성될때 실행
        super.onCreate();

        Log.d(TAG, "onCreate호출");
    }

    @Override                                  //서비스가 종료될때 실행
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {          //서비스가 실행되고 있는상태에서 인텐트를 이용하여 정보를 받기위해 사용
        Log.d(TAG, "onStartCommand호출");

        if (intent == null) {
            return Service.START_STICKY;                                    //인텐트가null일때 서비스 재시작을위한 명령어
        } else {
            processCommand(intent);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void processCommand(Intent intent) {
        String data = intent.getStringExtra("Key");

        Log.d(TAG, "받은DATA : " + data);


        for (int i = 0; i < 5; i++) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(TAG,"대기:"+i+"seconds");
        }
        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("str","서비스에서 전달한 값.");
        startActivity(intent1);                                              //플래그를 지정하고 서비스->메인액티비티로 값을 전달해주기위해 사용
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
