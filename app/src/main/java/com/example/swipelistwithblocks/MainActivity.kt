package com.example.swipelistwithblocks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.swipelistwithblocks.stopwatch.Stopwatch
import kotlinx.android.synthetic.main.stopwatch_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var stopwatch: Stopwatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Инициализация секундомера
        stopwatch = Stopwatch()

        // Устанавливаем слушатель для обновления UI (TextView)
        stopwatch.setOnTickListener { time ->
            timeView.text = time
        }

        // Обработка нажатий на кнопки
        startButton.setOnClickListener {
            stopwatch.start()
        }

        pauseButton.setOnClickListener {
            stopwatch.pause()
        }

        resetButton.setOnClickListener {
            stopwatch.reset()
        }
    }
}