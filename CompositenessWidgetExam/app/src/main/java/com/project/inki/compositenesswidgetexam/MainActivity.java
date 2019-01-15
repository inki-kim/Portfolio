package com.project.inki.compositenesswidgetexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    DTPicker dtPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dtPicker=findViewById(R.id.DTPicker);

        dtPicker.setOnDateTimeChangeListener(new DTPicker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(DTPicker view, int year, int month, int day, int hour, int minute) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(year,month,day,hour,minute);

            }
        });
        }
}
