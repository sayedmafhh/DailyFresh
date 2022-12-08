package com.thetrusttech.getacarparts.ui.home.ui.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import com.thetrusttech.getacarparts.R

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        var layout = findViewById<TextInputLayout>(R.id.layout_edt_username)
        var btn = findViewById<Button>(R.id.btn)

        var flag = false

        btn.setOnClickListener {
            if (flag) {
                layout.error = "cannot leave empty"
                flag = false
            }
            else {
                layout.error = null
                flag = true
            }
        }
    }
}