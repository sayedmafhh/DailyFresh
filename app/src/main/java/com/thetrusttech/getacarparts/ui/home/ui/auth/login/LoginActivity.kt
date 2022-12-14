package com.thetrusttech.getacarparts.ui.home.ui.auth.login

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.thetrusttech.getacarparts.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        var btn=findViewById<TextView>(R.id.textViewSignUp)
        
    }
}