package com.thetrusttech.getacarparts.ui.home.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.login.SigninActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signup)
        // var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        var txtGoToLogin = findViewById<TextView>(R.id.gotoLogin)


        txtGoToLogin.setOnClickListener {
            val intent = Intent( this, SigninActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}