package com.example.clicker

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.content.SharedPreferences

class MainActivity : AppCompatActivity() {

    lateinit var tvTime: TextView
    private lateinit var tvClicks: TextView
    lateinit var tvRecord: TextView
    lateinit var bStart: Button
    lateinit var bClick: Button
    private lateinit var bShop: Button

    private var pref : SharedPreferences? = null

    private lateinit var timer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pref = getSharedPreferences("table", Context.MODE_PRIVATE)
        Counter.record = pref?.getInt("record", 0)!!

        tvTime = findViewById(R.id.tv_time)
        tvClicks = findViewById(R.id.tv_clicks)
        tvRecord = findViewById(R.id.tv_record)

        bStart = findViewById(R.id.b_start)
        bClick = findViewById(R.id.b_click)
        bShop = findViewById(R.id.b_shop)

        bClick.isEnabled = false

        tvRecord.text = getString(R.string.record_currentrecord, Counter.record)

        fun saveData(res: Int) {
            val editor = pref?.edit()
            editor?.putInt("record", res)
            editor?.apply()
        }


        bStart.setOnClickListener {
            Counter.currentTime = 30
            Counter.currentClicks = 0
            Counter.currentMoney = 1

            tvTime.text = getString(R.string.time_currenttime, Counter.currentTime)
            tvClicks.text = getString(R.string.clicks_currentclicks, Counter.currentClicks)

            bStart.isEnabled = false
            bClick.isEnabled = true

            timer.start()
        }

        bClick.setOnClickListener {
            Counter.currentClicks += Counter.currentMoney
            tvClicks.text = getString(R.string.clicks_currentclicks, Counter.currentClicks)
        }

        bShop.setOnClickListener {
            val intent = Intent(applicationContext, ImproveClicker::class.java).setAction("your.custom.action")
            startActivity(intent)
        }

        timer = object : CountDownTimer(Counter.currentTime.toLong() * 1000, 1000) { //10000 = 10 seconds game, 1000 = refresh each second
            override fun onTick(millisUntilFinished: Long) {
                Counter.currentTime--
                val time = Counter.currentTime + 1
                tvTime.text = getString(R.string.time, time)
            }

            override fun onFinish() {
                tvTime.text = getString(R.string.time_0)

                bStart.isEnabled = true
                bClick.isEnabled = false

                if (Counter.record < Counter.currentClicks) {
                    saveData(Counter.currentClicks)
                    Counter.record = Counter.currentClicks
                    tvRecord.text = getString(R.string.record_currentrecord, Counter.record)
                }
            }
        }
    }
}