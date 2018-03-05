package com.rzrmn.Controller

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.rzrmn.R
import com.rzrmn.Services.AuthService
import com.rzrmn.Utilities.GALLERY_INTENT
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    lateinit var fbAuth: FirebaseAuth
    lateinit var mStorage: StorageReference
    private lateinit var currentUserDB: DatabaseReference
    lateinit var uri: Uri
    lateinit var filePath : StorageReference
    var profileUri : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        fbAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
        mStorage = FirebaseStorage.getInstance().reference
    }

//    fun chooseImageClicked(view: View){
//        val intent = Intent(Intent.ACTION_PICK)
//        intent.setType("image/*")
//        startActivityForResult(intent, GALLERY_INTENT)
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == GALLERY_INTENT && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
//            uri = data.data
//        }
//    }

    fun registerUser(view: View) {

        val firstName = signUpFirstName.text.toString()
        val lastName = signUpLastName.text.toString()
        val email = signUpEmail.text.toString()
        val password = signUpPassword.text.toString()



        AuthService.registerUser(email, password, firstName, lastName)  { loginSucess ->
            if(loginSucess){
                Toast.makeText(this, "USER CREATED", Toast.LENGTH_LONG).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else
                Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
        }




//
//
//
//                        val fbUser = FirebaseAuth.getInstance().currentUser
//                        val profileUpdates = UserProfileChangeRequest.Builder()
//                                .setDisplayName("$firstName $lastName")
//                                .build()
//                        fbUser?.updateProfile(profileUpdates)
//                                ?.addOnCompleteListener(OnCompleteListener { task ->
//                                    if (task.isSuccessful){
//
//
//                                        Log.d("User Profile", "User Profile Updated")
//                                    }
//                                })
//                        val intent = Intent(this, MainActivity:: class.java)
//                        startActivity(intent)
//
//                    }
//                })
//
//
//    }
//
//
//    fun test(email: String, password: String){
//        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener {task ->
//
//        })
   }



}
