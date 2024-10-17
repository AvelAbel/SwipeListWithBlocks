package com.example.swipelistwithblocks.stopwatch

import android.os.Handler
import android.os.Looper
import java.util.Locale

class Stopwatch (){
    private var isRunning = false
    private var elapsedTime = 0L // Время в секундах
    private val handler = Handler(Looper.getMainLooper())
    private var onTickListener: ((String) -> Unit)? = null

    // Интерфейс для обновления UI
    fun setOnTickListener(listener: (String) -> Unit) {
        onTickListener = listener
    }

    // Метод для запуска секундомера
    fun start() {
        if (!isRunning) {
            isRunning = true
            handler.post(runnable)
        }
    }

    // Метод для паузы секундомера
    fun pause() {
        isRunning = false
        handler.removeCallbacks(runnable)
    }

    // Метод для сброса секундомера
    fun reset() {
        isRunning = false
        handler.removeCallbacks(runnable)
        elapsedTime = 0L
        updateUI()
    }

    // Runnable, который обновляет время каждую секунду
    private val runnable = object : Runnable {
        override fun run() {
            if (isRunning) {
                elapsedTime++
                updateUI()
                handler.postDelayed(this, 1000)
            }
        }
    }

    // Метод для форматирования времени и вызова обновления UI
    private fun updateUI() {
        val hours = elapsedTime / 3600
        val minutes = (elapsedTime % 3600) / 60
        val seconds = elapsedTime % 60

        val timeString = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, seconds)
        onTickListener?.invoke(timeString)
    }
}

