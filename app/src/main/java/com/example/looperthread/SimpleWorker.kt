package com.example.looperthread

import android.util.Log
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.atomic.AtomicBoolean

class SimpleWorker : Thread(){

    private val TAG = "SimpleWorker"
    private var alive = AtomicBoolean(true)
    private var taskQueue = ConcurrentLinkedQueue<Runnable>()

    init {
        start() //when start method called it execute run method
    }

    override fun run() {
        while (alive.get()) {
            taskQueue.poll()?.run()
        }
        Log.i(TAG, "SimpleWorker Terminated")
    }

    fun execute(task: Runnable): SimpleWorker{
        taskQueue.add(task)
        return this
    }

    fun quit(){
        alive.set(false)
    }
}