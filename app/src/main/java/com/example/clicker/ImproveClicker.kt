package com.example.clicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ImproveClicker : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_improve_clicker)
        findViewById<TextView>(R.id.tv_clicks).text = getString(R.string.clicks_currentclicks, Counter.currentClicks)
        findViewById<TextView>(R.id.tv_money).text = getString(R.string.money_currentmoney, Counter.currentMoney)
        findViewById<Button>(R.id.b_improve1).setOnClickListener {
            if (Counter.currentClicks > 0) {
                Counter.currentMoney += 1
            }
            findViewById<TextView>(R.id.tv_money).text = getString(R.string.money_currentmoney, Counter.currentMoney)
        }
        findViewById<Button>(R.id.b_improve2).setOnClickListener {
            if (Counter.currentClicks > 10) {
                Counter.currentMoney += 3
            }
            findViewById<TextView>(R.id.tv_money).text = getString(R.string.money_currentmoney, Counter.currentMoney)
        }
        findViewById<Button>(R.id.b_improve3).setOnClickListener {
            if (Counter.currentClicks > 100) {
                Counter.currentMoney += 10
            }
            findViewById<TextView>(R.id.tv_money).text = getString(R.string.money_currentmoney, Counter.currentMoney)
        }
        findViewById<Button>(R.id.b_back).setOnClickListener {
            onBackPressed()
        }
    }
}