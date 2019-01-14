package com.project.inki.customviewexam;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class BitmapButton extends AppCompatButton {

    int nomal=R.drawable.bitmap_button_normal;
    int click=R.drawable.bitmap_button_clicked;

    public BitmapButton(Context context) {          //객체 생성시 호출되는 생성자
        super(context);

        init();
    }

    public BitmapButton(Context context, AttributeSet attrs) {          //xml에 추가된 버튼이 인플레이션 될때 호출되는 생성자
        super(context, attrs);

        init();
    }

    public void init(){
        setBackgroundResource(nomal);

        setTextColor(Color.WHITE);
        setTextSize(getResources().getDimension(R.dimen.textsize));          //자바소스코드로 values/dimen.xml을 이용하여 텍스트 크기 설정
        setTypeface(Typeface.DEFAULT_BOLD);
    }


    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);

        int act=event.getAction();

        switch (act){
            case MotionEvent.ACTION_DOWN:                                           //버튼이 클릭되는 모션이 발생했을경우
                setBackgroundResource(this.click);                                  //백그라운드리소스를 눌렀을때의 상태로 변경
                break;

            case MotionEvent.ACTION_UP:
                setBackgroundResource(this.nomal);                                  //백그라운드리소스를 떼어졌을때의 상태로 변경
                break;
        }

        invalidate();                                                       //다시그리기

        return true;
    }
}
