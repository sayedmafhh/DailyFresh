package com.thetrusttech.getacarparts.ui.home.ui.auth.forgot

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.signup.RegisterActivity

class ForgotMyPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_forgot)
        // var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        /*var txtGoToForgotPassword = findViewById<TextView>(R.id.forgotpassword)


        txtGoToForgotPassword.setOnClickListener {
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }*/
    }
}