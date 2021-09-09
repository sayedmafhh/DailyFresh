package com.thetrusttech.getacarparts.ui.splash

import android.content.Intent
import android.os.Bundle
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.items.activity.MainActivity
import com.thetrusttech.getacarparts.ui.base.BaseActivity
import kotlinx.coroutines.delay
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