package com.example.admin.indentifycode;

import android.os.CountDownTimer;
import android.util.Log;

/**
 * Created by admin on 2017/6/23.
 * 显示倒计时
 */

public class DubCountDownTimer {

    private CountDownTimer mCountDown;

    public DubCountDownTimer(CountDownTimerListener countDownTimerListener) {
        this.countDownTimerListener = countDownTimerListener;
    }

    public void startCountDown(long millis, long interval) {
        if (null == mCountDown) {
            mCountDown = new CountDownTimer(millis, interval) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (null != countDownTimerListener)
                        countDownTimerListener.tickTimer(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    if (null != countDownTimerListener) countDownTimerListener.finishTimer();
                }
            }.start();
            if (null != countDownTimerListener) countDownTimerListener.startTimer();
        } else {
            Log.i("         ", "倒计时中....");
        }
    }

    public void cancleCountDown() {
        if (mCountDown != null) {
            mCountDown.cancel();
            mCountDown = null;
        }
        if (countDownTimerListener != null) countDownTimerListener.cancelTimer();
    }

    private CountDownTimerListener countDownTimerListener; //这时创建了一个接回调，分别就情况而处理

    interface CountDownTimerListener {
        void startTimer();

        void finishTimer();

        void cancelTimer();

        void tickTimer(long l);
    }
}
