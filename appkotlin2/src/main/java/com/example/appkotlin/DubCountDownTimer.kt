package com.example.appkotlin

import android.os.CountDownTimer
import android.util.Log

/**
 * Created by admin on 2017/6/23.
 */

class DubCountDownTimer constructor(private var countDownListener: CountDownListener?) {

    private var mCounntDownTimer: CountDownTimer? = null
    fun startTimer(millisInFuture: Long, countDownInterval: Long) {
        if (null == mCounntDownTimer ) {
            countDownListener?.startTimer()
            mCounntDownTimer = object : CountDownTimer(millisInFuture, countDownInterval) {
                override fun onFinish() {
                    countDownListener?.finishTimer()
                    Log.i(Tags.toString(), "订时结束")
                }

                override fun onTick(millisUntilFinished: Long) {
                    countDownListener?.tickTimer(millisUntilFinished)
                    Log.i(Tags.toString(), "订时开始")
                }
            }.start()
        } else {
            countDownListener?.finishTimer()
            Log.i(Tags.toString(), "倒计时正在进行")
        }
    }

    fun ancleTimers() {
        if (mCounntDownTimer != null) {
            mCounntDownTimer!!.cancel()
            mCounntDownTimer = null
        }
        countDownListener?.cancleTimer()
    }

    interface CountDownListener {
        fun startTimer()
        fun cancleTimer()
        fun finishTimer()
        fun tickTimer(millisUntilFinished: Long)
    }

    companion object {
        private var Tags = DubCountDownTimer::class.java
    }
}

