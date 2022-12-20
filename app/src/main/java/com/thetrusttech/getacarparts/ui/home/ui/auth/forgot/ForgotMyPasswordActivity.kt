package com.thetrusttech.getacarparts.ui.home.ui.auth.forgot

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.signup.RegisterActivity
import com.thetrusttech.getacarparts.utils.changeStatusBarColor

class ForgotMyPasswordActivity : AppCompatActivity() {
    private lateinit var inputEmail : EditText
    private lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_forgot)
        inputEmail = findViewById(R.id.inputEmail)
        btnLogin = findViewById(R.id.btnLogin)

        setToolbar()

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
    private fun setToolbar() {
        var textTitle = findViewById<TextView>(R.id.topText)
        var btnBackArrow = findViewById<ImageButton>(R.id.ib_back_button)
        btnBackArrow.visibility = View.VISIBLE

        btnBackArrow.setOnClickListener{
            onBackPressed()
        }
        textTitle.text = getString(R.string.txt_forgot_title)

        changeStatusBarColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.colorPrimary
            ), false
        )
    }
}