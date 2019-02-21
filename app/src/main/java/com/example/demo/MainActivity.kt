package com.example.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.utils.setupTicker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupTickers()
    }

    private fun setupTickers() {
//        main_first_ticker.animate().rotationX(30f).apply {
//            duration = 2500
//        }
//        main_second_ticker.animate().rotationX(-30f).apply {
//            duration = 2500
//        }

        setupTicker(
            listOf("One two."),
            R.layout.partial_ticker_text,
            main_first_ticker.findViewById(R.id.ticker_layout),
            main_first_ticker.findViewById(R.id.ticker_text_title),
            getString(R.string.ticker_news),
            windowManager
        )
        setupTicker(
            listOf("One two three.", "Four five."),
            R.layout.partial_ticker_text,
            main_second_ticker.findViewById(R.id.ticker_layout),
            main_second_ticker.findViewById(R.id.ticker_text_title),
            getString(R.string.ticker_breaking_news),
            windowManager
        )
    }
}
