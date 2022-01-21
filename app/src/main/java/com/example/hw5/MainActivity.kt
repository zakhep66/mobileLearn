package com.example.hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.os.PersistableBundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import androidx.core.view.isVisible
import com.example.hw5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), TaskCallBacks {

    companion object {
        //        const val PROGRESS_IS_SHOWING = "PROGRESS_IS_SHOWING"
        const val RESULT = "RESULT"
    }

    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    private lateinit var binding: ActivityMainBinding
    private var fragment: MyFragment? = null
    private var myResult: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        savedInstanceState?.getBoolean(PROGRESS_IS_SHOWING)?.let {
//            showProgress(it)
//        }
        savedInstanceState?.getInt(RESULT)?.let {
            Log.d("MY TAG", "RESTORE STATE = $it")
        }


        val fm = supportFragmentManager  // проверка не запущен ли уже фрагмент
        val oldFragment =
            fm.findFragmentByTag(MyFragment.TAG) // после onCreate идёт проверка если активити уже есть - достаём её из бекстека
        if (oldFragment == null) {
            fragment = MyFragment()
            fm
                .beginTransaction()
                .add(fragment!!, MyFragment.TAG)
                .commit()
            fragment
        } else {
            fragment = oldFragment as MyFragment
        }

        fragment?.startTask()
    }

    override fun onSaveInstanceState(outState: Bundle) { // вызывается при дестрое вьюхи. В банл можно что-нибудь записать
        super.onSaveInstanceState(outState)
//        outState.putBoolean(PROGRESS_IS_SHOWING, binding.progress.isVisible) // мы в него кладём инфу - показывается ли прогресс
        outState.putInt(RESULT, myResult) // результат из асинк таска
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        showProgress(false)
        fragment?.cancelTask()
    }

//    private fun showProgress(show: Boolean) {
//        binding.progress.isVisible = show
//    }

    override fun onPreExecuted() {
//        showProgress(true)
    }

    override fun onCancelled() {
        Log.d("MY TAG", "CANCELLED")
    }

    override fun onPostExecuted(i: Int) {
        myResult = i
        Log.d("MY TAG", "MASSAGE = $i")
        binding.chelYa.layoutManager = verticalLinearLayoutManager
        binding.chelYa.adapter = Adapter(Holder.createCollectionPerson(i.toString()))
    }

}