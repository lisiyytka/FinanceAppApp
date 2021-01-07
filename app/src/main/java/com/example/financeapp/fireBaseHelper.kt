package com.example.financeapp

import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


lateinit var AUTH:FirebaseAuth
lateinit var REF_DATABASE_ROOT:DatabaseReference

const val NODE_USERS = "Users"
const val CHILD_LOGIN = "login"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"


fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}

fun getUserByLogin(login: String){
    REF_DATABASE_ROOT.child(NODE_USERS).equalTo(login);
}

fun proverka(view: TextView){
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS)
    val listener =object: ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {
            // handle error
        }
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val user = dataSnapshot.getValue(User::class.java)
            view.text = user?.login.toString()
        }
    }
    ref.addValueEventListener(listener)
}


