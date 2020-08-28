package ru.rosbank.mbdg.myapplication.tasks

import android.util.Log

class LoggingRunnable(private val header: String) : PriorityRunnable {

    private var index: Int = 0

    override val priority: Int = Int.MAX_VALUE / 2
    override fun run() {

        val name = Thread.currentThread().name
        while (index < 10) {
            Log.e("WOW", "header: $header index: $index $name")
            index++
            Thread.sleep(100)
        }
    }
}