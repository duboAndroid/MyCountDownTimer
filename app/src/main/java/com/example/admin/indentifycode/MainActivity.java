package com.example.admin.indentifycode;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private DubCountDownTimer dubCountDownTimer;
    private TextView code_num;
    private TextView text;
    private TextView code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = byViewId(R.id.telephone);
        code = byViewId(R.id.indentify_code);
        code_num = byViewId(R.id.code_num);
        code.setOnClickListener(new getCode());
        dubCountDownTimer = new DubCountDownTimer(new DubCountDownTimer.CountDownTimerListener() {
            @Override
            public void startTimer() {
                code.setEnabled(false);
            }

            @Override
            public void finishTimer() {
                code.setEnabled(true);
                code.setText("获取");
            }

            @Override
            public void cancelTimer() {
            }

            @Override
            public void tickTimer(long l) {
                code.setText(getDate(l, "mm:ss"));
            }
        });
    }

    private String getDate(long l, String i) {
        Date date = new Date(l);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat(i);
        return sdf.format(gc.getTime());
    }

    public <T extends View> T byViewId(int id) {
        return (T) findViewById(id);
    }

    private class getCode implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(dubCountDownTimer != null){
                int random = (int) (Math.random() * 9999);
                dubCountDownTimer.startCountDown(6000, 100);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(6000);
                        dubCountDownTimer.cancleCountDown();
                    }
                }).start();
            }
        }
    }

    @Override
    public void finish() {
        super.finish();
        dubCountDownTimer.cancleCountDown();
    }
}
