package com.thetrusttech.getacarparts.ui.home.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.forgot.ForgotMyPasswordActivity
import com.thetrusttech.getacarparts.ui.home.ui.auth.signup.RegisterActivity

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signin)
       // var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        var txtGoToRegister = findViewById<TextView>(R.id.gotoRegister)
        var txtGoToForgot = findViewById<TextView>(R.id.forgotpassword)


        txtGoToRegister.setOnClickListener {
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

        txtGoToForgot.setOnClickListener {
            val intent = Intent( this, ForgotMyPasswordActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}