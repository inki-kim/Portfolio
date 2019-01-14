package com.project.inki.customviewexam;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ItemView extends LinearLayout {                //각 아이템을 Inflate 시킨 레이아웃을 만들기 위한 클래스정의


    TextView member_name, phone_number, list_number;
    ImageView imageView;

    public ItemView(Context context) {
        super(context);
        init(context);
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.list_item, this, true);

        member_name = findViewById(R.id.member_name);
        phone_number = findViewById(R.id.phone_number);
        list_number = findViewById(R.id.list_number);
        imageView = findViewById(R.id.imageview);
    }

    public void setMember_name(String name) {
        member_name.setText(name);
    }

    public void setPhone_number(String number) {
        phone_number.setText(number);
    }

    public void setList_number(String listnumber) { list_number.setText(listnumber); }

    public void setImageView(int res) {
        imageView.setImageResource(res);
    }
}
