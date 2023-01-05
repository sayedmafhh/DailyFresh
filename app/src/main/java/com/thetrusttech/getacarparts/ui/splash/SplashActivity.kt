package com.thetrusttech.getacarparts.ui.splash

import android.content.Intent
import android.os.Bundle
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.base.BaseActivity
import com.thetrusttech.getacarparts.ui.home.HomeActivity
import com.thetrusttech.getacarparts.ui.home.ui.auth.login.SigninActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        runBlocking {
            delay(1000)
            newActivity()
        }
    }

    fun newActivity() {
        val intent = Intent( this, SigninActivity::class.java)
        startActivity(intent)
        finish()
    }
}