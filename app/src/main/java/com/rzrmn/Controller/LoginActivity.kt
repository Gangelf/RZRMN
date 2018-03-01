package com.rzrmn.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rzrmn.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun signUpClicked(view: View) {

        val intent = Intent(this, SignUpActivity:: class.java)
        startActivity(intent)

    }
}
