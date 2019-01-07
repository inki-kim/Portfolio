package com.project.inki.eventexam;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    View view1,view2;
    TextView textView;
    Button button,button2;
    EditText inputtext;
    String name;
    GestureDetector gestureDetector;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart호출",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop호출",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestroy호출",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"onCreate호출",Toast.LENGTH_LONG).show();

        view1 = (View) findViewById(R.id.view1);
        view2 = (View) findViewById(R.id.view2);
        textView = (TextView) findViewById(R.id.textView1);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        inputtext=(EditText)findViewById(R.id.inputText);

        view1.setOnTouchListener(new View.OnTouchListener() {                           //터치이벤트
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int act = event.getAction();

                float x = event.getX();
                float y = event.getY();

                if (act == MotionEvent.ACTION_DOWN) {                //act값이 0
                    println("손가락 눌림 : " + x + "," + y);
                } else if (act == MotionEvent.ACTION_UP) {           //act값이 1
                    println("손가락 뗌 : " + x + "," + y);
                } else if (act == MotionEvent.ACTION_MOVE) {         //act값이 2
                    println("손가락 움직임 : " + x + "," + y);
                }

                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;
            }
        });

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {               //제스쳐 이벤트
            @Override
            public boolean onDown(MotionEvent e) {
                println("onDown 호출");
                return true;
            }

            @Override
            public void onShowPress(MotionEvent e) {
                println("onShowPress 호출");
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                println("onSingleTapUp 호출");
                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                println("onScroll 호출"+distanceX+","+distanceY);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                println("onLongPress 호출");
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                println("onFling 호출"+velocityX+","+velocityY);
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {                  //전역변수인 name에 값을 저장
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater=getLayoutInflater();
                View layout=layoutInflater.inflate(R.layout.toast_image,(ViewGroup)findViewById(R.id.toast_Linear));  //LayoutInflater를 이용한 toast메세지 외형 변경

                TextView textView=(TextView) layout.findViewById(R.id.textView);           //oast_image.xml의 textView를 참조

                Toast toast=new Toast(getApplicationContext());
                toast.setGravity(Gravity.TOP,50,400);           //Toast위치변경
                toast.setDuration(Toast.LENGTH_LONG);
                textView.setText("name이라는 변수에"+inputtext.getText().toString()+" 값 저장");
                toast.setView(layout);
                toast.show();
                name=inputtext.getText().toString();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(intent);
            }
        });

        if(savedInstanceState!=null){                                   //savedInstanceState가 비어있지 않을때 key값으로 데이터를 가져와 복원.
            name=savedInstanceState.getString("name");
            Toast.makeText(getApplicationContext(),"name값 복원 : "+name,Toast.LENGTH_LONG).show();
        }
    }


    public void println(String str) {               //textView에 차례로 추가
        textView.append(str + "\n");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {                         //back키가 눌렸을때의 이벤트
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Snackbar.make(view1,"back키가 눌렸다.",Snackbar.LENGTH_LONG).show();
            dialogMessage();
            return true;
        }
        return false;
    }

    private void dialogMessage(){                                   //대화상자 띄우기 메소드
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("안내");
        builder.setMessage("종료 하시겠습니까?");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {                  //예 버튼 클릭햇을때의 이벤트정의
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"프로그램을 종료합니다.",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {             //아니오 버튼 클릭햇을때의 이벤트정의
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {                ////취소 버튼 클릭햇을때의 이벤트정의
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog=builder.create();
        dialog.show();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {               //화면이 전환될때 변수값을 저장하기 위한 메소드
        super.onSaveInstanceState(outState);

        outState.putString("name",name);
    }
}
