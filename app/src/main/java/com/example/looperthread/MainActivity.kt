package com.example.looperthread

import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var worker = SimpleWorker()
    lateinit var textView:TextView
    val handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            textView.text = msg.toString()

        }
    }


    var handler1: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a = "a"
        a+="b"
        println(" a $a")


        var handlerThread = HandlerThread("HandlerThread")
        handlerThread.start()
       var handler2 = Handler( handlerThread.looper)
        handler2.post {
            Log.i("Thread", "handler2 post  ${Thread.currentThread().name}")
        }



        var thread = object : Thread(){
            override fun run() {
                Looper.prepare()

                handler1 = Handler()

                Log.i("Thread", "Running: ${Thread.currentThread().name}")

                Looper.loop()
            }
        }

        thread.start()
        Thread.sleep(1000)
        handler1?.post {  Log.i("Thread", "Handler post  ${Thread.currentThread().name}") }


       /* textView = findViewById(R.id.textView)


        worker.execute {
            Thread.sleep(1000)
            var message = Message.obtain()
            message.obj = "Task 1 comleted"
            handler.sendMessage(message)
        }
            .execute {
                Thread.sleep(500)
                var message = Message.obtain()
                message.obj = "Task 2 comleted"
                handler.sendMessage(message)
            }.execute {
                Thread.sleep(1000)
                var message = Message.obtain()
                message.obj = "Task 3 comleted"
                handler.sendMessage(message)
            }*/
    }

    override fun onDestroy() {
        super.onDestroy()
      //  worker.quit()

    }
}