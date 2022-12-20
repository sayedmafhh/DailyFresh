package com.thetrusttech.getacarparts.ui.home.ui.auth.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.forgot.ForgotMyPasswordActivity
import com.thetrusttech.getacarparts.ui.home.ui.auth.signup.RegisterActivity
import com.thetrusttech.getacarparts.utils.changeStatusBarColor

class SigninActivity : AppCompatActivity() {

    private lateinit var inputEmail : EditText
    private lateinit var inputPassword : EditText
    private lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signin)

        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {

            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()

            if (email.isEmpty()){
                inputEmail.error = "Email Required"
            }
             if (password.isEmpty()){
                inputPassword.error = "Password Required"
            }
        }

       // var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        var txtGoToRegister = findViewById<TextView>(R.id.gotoRegister)
        var txtGoToForgot = findViewById<TextView>(R.id.forgotpassword)

        setToolbar()

        txtGoToRegister.setOnClickListener {
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)
        }

        txtGoToForgot.setOnClickListener {
            val intent = Intent( this, ForgotMyPasswordActivity::class.java)
            startActivity(intent)
        }

        changeStatusBarColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.colorPrimary
            ), false
        )

    }

    private fun setToolbar() {
        var txtTitle = findViewById<TextView>(R.id.topText)
        txtTitle.text = getString(R.string.txt_signin_title)
    }

}