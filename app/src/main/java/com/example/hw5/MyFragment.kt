package com.example.hw5

import android.content.AsyncQueryHandler
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import java.util.concurrent.TimeUnit

interface TaskCallBacks {

    fun onPreExecuted()
    fun onCancelled()
    fun onPostExecuted(i: Int)
}

class MyFragment : Fragment() {

    companion object {
        const val TAG = ""
    }

    private var callbacks: TaskCallBacks? = null
    private var task: MyTask? = null
    private var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        startTask()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = requireActivity() as TaskCallBacks
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    fun startTask() {
        task = MyTask()
        val handlerCallback: Handler.Callback = object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                callbacks?.onPostExecuted(msg.what)
                return false
            }
        }
        handler = Handler(handlerCallback)
        task?.execute()
    }

    fun cancelTask() {
        if (task == null) return
        task?.cancel(true)
    }

    inner class MyTask : AsyncTask<Unit, Int, Unit>() {

        override fun onPreExecute() {
            callbacks?.onPreExecuted()
        }

        override fun doInBackground(vararg params: Unit?) {
            Log.d("MY_TAG", "start task")
            try {
                for (i in 0..4) {
                    publishProgress(143) // этот порядок цифр - будет выводиться на экране
                    TimeUnit.SECONDS.sleep(1)
                    if (isCancelled) break
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

        override fun onCancelled() {
            callbacks?.onCancelled()
        }

//        override fun onPostExecute(result: Unit?) {
//            callbacks?.let {
//                handler?.sendEmptyMessage(1)
//                handler?.sendEmptyMessageDelayed(2, 5)
//            }
//        }

        override fun onProgressUpdate(vararg values: Int?) {
            handler?.sendEmptyMessage(values[0]!!) // !! - если будет null - сработает исключение
        }

    }

}
