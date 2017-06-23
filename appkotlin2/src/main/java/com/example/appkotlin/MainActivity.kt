package com.example.appkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var text: TextView? = null
    private var mCountDownTimer: DubCountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = byViewID(R.id.indentify_code)


        mCountDownTimer = DubCountDownTimer(object :DubCountDownTimer.CountDownListener{
            override fun cancleTimer() {
            }

            override fun finishTimer() {
                text!!.isEnabled = true
                text!!.setText("获取")
            }

            override fun tickTimer(millisUntilFinished: Long) {
                text!!.text = getDate(millisUntilFinished,"mm:ss")

            }

            override fun startTimer() {
                text!!.isEnabled = false
            }
        })

        text!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                if(mCountDownTimer != null){
                    mCountDownTimer!!.startTimer(4000,100)
                    thread { Runnable {
                        SystemClock.sleep(4000)
                        mCountDownTimer!!.ancleTimers()
                    } }.start()

                    Thread(Runnable {
                        SystemClock.sleep(4000)
                        mCountDownTimer!!.ancleTimers()
                    }).start()
                }
            }
        })
    }

    private fun getDate(millisUntilFinished: Long, s: String): String {
        val date = Date(millisUntilFinished)
        val gregorianCalendar = GregorianCalendar()
        gregorianCalendar.time = date
        val simpleDateFormat = SimpleDateFormat(s)
        return simpleDateFormat.format(gregorianCalendar.time)
    }


    fun <T : View> byViewID(id: Int): T {
        return findViewById(id) as T
    }

    override fun finish() {
        super.finish()
        mCountDownTimer!!.ancleTimers()
    }

    companion object{
        private var Tags = MainActivity::class.java
    }
}
