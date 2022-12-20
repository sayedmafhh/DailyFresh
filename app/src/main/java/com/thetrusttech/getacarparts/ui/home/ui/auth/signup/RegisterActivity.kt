package com.thetrusttech.getacarparts.ui.home.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.login.SigninActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var inputUsername : EditText
    private lateinit var inputEmail : EditText
    private lateinit var inputPassword : EditText
    private lateinit var inputconfirmPassword : EditText
    private lateinit var btnSignup : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signup)

        inputUsername = findViewById(R.id.inputUsername)
        inputEmail = findViewById(R.id.inputEmail)
        inputPassword = findViewById(R.id.inputPassword)
        inputconfirmPassword = findViewById(R.id.inputconfirmPassword)
        btnSignup = findViewById(R.id.btnSignup)

        btnSignup.setOnClickListener {
            val username = inputUsername.text.toString().trim()
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            val confirmpassword = inputconfirmPassword.text.toString().trim()

            if (username.isEmpty()){
                inputUsername.error = "Username Required"
            }
            if (email.isEmpty()){
                inputEmail.error = "Email Required"
            }
            if (password.isEmpty()){
                inputPassword.error = "Password Required"
            }
            if (confirmpassword.isEmpty()){
                inputconfirmPassword.error = "Confirm Password Required"
            }
        }

        // var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        var txtGoToLogin = findViewById<TextView>(R.id.gotoLogin)

        setToolbar()

        txtGoToLogin.setOnClickListener {
            val intent = Intent( this, SigninActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setToolbar() {
        var txtTitle = findViewById<TextView>(R.id.topText)
        //var btnBackArrow = findViewById<ImageButton>(R.id.ib_back_button)
        //btnBackArrow.visibility = View.GONE

        txtTitle.text = getString(R.string.txt_signup_title)
        /*
        btnBackArrow.setOnClickListener {
            onBackPressed()
        }*/
    }
}