package com.thetrusttech.getacarparts.ui.home.ui.auth.forgot

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.signup.RegisterActivity

class ForgotMyPasswordActivity : AppCompatActivity() {
    private lateinit var inputEmail : EditText
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_forgot)
        inputEmail = findViewById(R.id.inputEmail)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = inputEmail.text.toString().trim()

            if (email.isEmpty()){
                inputEmail.error = "Email Required"
            }
        }
        // var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        /*var txtGoToForgotPassword = findViewById<TextView>(R.id.forgotpassword)


        txtGoToForgotPassword.setOnClickListener {
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }*/
    }
}