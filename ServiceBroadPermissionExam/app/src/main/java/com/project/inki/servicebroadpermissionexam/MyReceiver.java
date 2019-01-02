package com.project.inki.servicebroadpermissionexam;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
    public static final String TAG = "SMSReceiver";

/*
* 메세지가 도착했을경우 onReceive 메소드를 호출하며 각종 정보를 스캔한다.
* 그리고 intent로 메세지로 받은 정보들을 가지고 새창을 띄운다.
* 띄운 새창에 받은 메세지 정보를 출력해준다.
* */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive 호출");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if (messages!=null && messages.length>0) {
            String sender = messages[0].getOriginatingAddress();        //발신자 확인
            Log.i(TAG, "SMS sender : " + sender);

            String contents = messages[0].getMessageBody();             //수신내용 확인
            Log.i(TAG, "SMS contents : " + contents);

            Date receiveDate = new Date(messages[0].getTimestampMillis());  //보낸 시각확인
            Log.i(TAG, "SMS receiveDate : " + receiveDate);

            Intent intent1=new Intent(context,SmsReceiveView.class);                //SmsReceiveView로 값을 보내기위한 Intent

            intent1.addFlags(intent1.FLAG_ACTIVITY_NEW_TASK|intent1.FLAG_ACTIVITY_SINGLE_TOP|intent1.FLAG_ACTIVITY_CLEAR_TOP);

            intent1.putExtra("sender",sender);
            intent1.putExtra("contents",contents);
            intent1.putExtra("receiveDate",receiveDate.toString());

            context.startActivity(intent1);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objects = (Object[]) bundle.get("pdus");
        SmsMessage[] messages=new SmsMessage[objects.length];

        int smsCount=objects.length;

        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                String format =bundle.getString("format");
                messages[i]=SmsMessage.createFromPdu((byte[]) objects[i],format);
            }else{
                messages[i]=SmsMessage.createFromPdu((byte[])objects[i]);
            }
        }
        return messages;
    }

}
