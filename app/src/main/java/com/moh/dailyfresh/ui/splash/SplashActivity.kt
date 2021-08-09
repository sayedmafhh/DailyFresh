package com.moh.dailyfresh.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope
import com.moh.dailyfresh.R
import com.moh.dailyfresh.ui.MainActivity
import com.moh.dailyfresh.ui.base.BaseActivity
import com.moh.dailyfresh.ui.items.viewmodel.ItemDetailsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        runBlocking {
            delay(8000)
            newActivity()
        }
    }

    fun newActivity() {
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}