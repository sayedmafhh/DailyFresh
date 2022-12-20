package com.thetrusttech.getacarparts.ui.home.ui.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.thetrusttech.getacarparts.R
import com.thetrusttech.getacarparts.ui.home.ui.auth.login.SigninActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_signup)
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