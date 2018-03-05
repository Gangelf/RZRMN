package com.rzrmn.Services

import android.content.Intent
import android.net.Uri
import android.support.constraint.solver.widgets.Snapshot
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.rzrmn.Controller.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.nav_header_main.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by Gangelf on 3/2/2018.
 */
object AuthService : AppCompatActivity(){

    lateinit var mDatabase: DatabaseReference
    lateinit var fbAuth: FirebaseAuth
    private lateinit var currentUserDB: DatabaseReference
    private lateinit var filePath : StorageReference
    lateinit var mStorage: StorageReference
    var profileUri : Uri? = null


    fun registerUser(email: String, password: String, firstName: String, lastName: String, complete: (Boolean) -> Unit){

        fbAuth = FirebaseAuth.getInstance()
        mStorage = FirebaseStorage.getInstance().reference
        //mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")

        fbAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, { task ->
            if (task.isSuccessful){

                val userId = fbAuth.currentUser?.uid
                currentUserDB = mDatabase.child(userId)
                currentUserDB.child("first name").setValue(firstName)
                currentUserDB.child("last name").setValue(lastName)

                var fbUser = fbAuth.currentUser

//                filePath = mStorage.child("images").child(uri.lastPathSegment)
//                filePath.putFile(uri).addOnSuccessListener{taskSnapshot ->
//                    profileUri = taskSnapshot.downloadUrl
//                    val test = ""
//                }.addOnFailureListener { exception ->
//                    println(exception.toString())
//                    val test = ""
//                }

                val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName("$firstName $lastName")
                        .setPhotoUri(profileUri)
                        .build()

                fbUser?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        fbUser = fbAuth.currentUser

                        UserDataService.userName = fbUser?.displayName.toString()
                        UserDataService.email = fbUser?.email.toString()
                        //val test = fbUser?.photoUrl
                        //UserDataService.profileUri = fbUser?.photoUrl!!

                        complete(true)
                    } else {
                        complete(false)
                    }
                }
            }
        }).addOnFailureListener { exception ->
            Log.d("ERR", exception.toString())
        }
    }
}


