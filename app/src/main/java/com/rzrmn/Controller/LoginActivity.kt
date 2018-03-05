package com.rzrmn.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import com.google.android.gms.tasks.*
import com.google.firebase.auth.FirebaseAuth
import com.rzrmn.R
import com.rzrmn.Services.UserDataService
import com.rzrmn.Utilities.BROADCAST_USER_DATA_CHANGE
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {


    lateinit var fbAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fbAuth = FirebaseAuth.getInstance()
    }


    fun signUpClicked(view: View) {
        val intent = Intent(this, SignUpActivity:: class.java)
        startActivity(intent)
    }

    fun loginClicked(view: View) {

        val email = loginEmailText.text.toString()
        val password = loginPassText.text.toString()

//        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
//            if (task.isSuccessful) {
//                println("Test")
//            }
//        }).addOnFailureListener { exception ->
//            println(exception.toString())
//        }

        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener { task ->
            if(task.isSuccessful) {

                val fbUser = fbAuth.currentUser

                UserDataService.userName = fbUser?.displayName.toString()
                UserDataService.email = fbUser?.email.toString()


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        })
    }


}
