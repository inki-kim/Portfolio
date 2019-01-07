package com.project.inki.eventexam;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    ProgressDialog dialog;
    Button button3, button4, button5, startanime, menuopen;

    SeekBar seekBar;
    TextView textView, movetext;

    LinearLayout menulinear;

    Animation animation, animation_left, animation_right;                    //애니메이션 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(80);                                //xml과 동일


        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(SecondActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.setMessage("데이터를 확인하는 중입니다.");
                dialog.show();
            }
        });

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        seekBar = findViewById(R.id.seekBar);
        button5 = findViewById(R.id.button5);
        textView = findViewById(R.id.seekBarText);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar.setVisibility(View.VISIBLE);
                textView.setVisibility(View.VISIBLE);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.screenBrightness = (float) progress / 100;
                getWindow().setAttributes(params);

                textView.setText("시크바의 값" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        startanime = findViewById(R.id.startanime);
        movetext = findViewById(R.id.movetext);
        animation = AnimationUtils.loadAnimation(this, R.anim.animation_flow);         //animation_flow.xml를 가져와 loadAnimaiton()메소드를 호출함으로써 Animation 객체로 생성
        animation.setAnimationListener(new Animation.AnimationListener() {                  //애니메이션 리스너 설정
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getApplicationContext(), "애니메이션 시작", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getApplicationContext(), "애니메이션 종료", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        startanime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movetext.startAnimation(animation);               //버튼을 클릭했을때 텍스트뷰에 애니메이션 적용
            }
        });

        //-------------------------------메뉴오픈 소스---------------------------------
        menulinear = findViewById(R.id.menulinear);
        menuopen = findViewById(R.id.menuopen);
        animation_left = AnimationUtils.loadAnimation(this, R.anim.animation_left);
        animation_right = AnimationUtils.loadAnimation(this, R.anim.animation_right);

        menuopen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuopen.getText().toString().equals("오픈")) {
                    menulinear.setVisibility(View.VISIBLE);
                    menulinear.startAnimation(animation_left);
                    menuopen.setText("닫기");
                }
                else if(menuopen.getText().toString().equals("닫기")){
                    menulinear.setVisibility(View.INVISIBLE);
                    menulinear.startAnimation(animation_right);
                    menuopen.setText("오픈");
                }
            }
        });
    }
}
