package com.project.inki.compositenesswidgetexam;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.Calendar;


public class DTPicker extends LinearLayout {

    public static interface OnDateTimeChangedListener {                                                 //시간날짜가 바뀌었을때 동작할 리스너 인터페이스 구현
        void onDateTimeChanged(DTPicker view, int year, int month, int day, int hour, int minute);
    }

    public void setOnDateTimeChangeListener(OnDateTimeChangedListener dateTimeChangeListener) {
        this.listener = dateTimeChangeListener;
    }

    private OnDateTimeChangedListener listener;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private CheckBox checkBox;

    public DTPicker(Context context) {
        super(context);
        init(context);
    }

    public DTPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.picker, this, true);                                    //xml 레이아웃 인플레이션

        Calendar calendar = Calendar.getInstance();                                                   //시간정보 가져오기
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        datePicker = findViewById(R.id.datepicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                if (listener != null) {
                    listener.onDateTimeChanged(DTPicker.this, year, monthOfYear, dayOfMonth, timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                }
            }
        });

        checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                timePicker.setEnabled(isChecked);
                if (checkBox.isChecked()) {
                    timePicker.setVisibility(VISIBLE);
                } else {
                    timePicker.setVisibility(INVISIBLE);
                }
            }
        });

        timePicker = findViewById(R.id.timepicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (listener != null) {
                    listener.onDateTimeChanged(DTPicker.this, datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), hourOfDay, minute);
                }
            }
        });

        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);
        timePicker.setEnabled(checkBox.isChecked());
        if (checkBox.isChecked()) {
            timePicker.setVisibility(VISIBLE);
        } else {
            timePicker.setVisibility(INVISIBLE);
        }

    }


}
