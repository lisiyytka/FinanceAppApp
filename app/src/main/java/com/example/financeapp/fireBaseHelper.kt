package com.example.financeapp

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

lateinit var AUTH:FirebaseAuth
lateinit var REF_DATABASE_ROOT:DatabaseReference

const val NODE_USERS = "users"
const val CHILD_LOGIN = "login"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME = "username"

fun initFirebase(){
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}

fun getUserByLogin(login: String){
    REF_DATABASE_ROOT.child(NODE_USERS).orderByChild(CHILD_LOGIN).equalTo(login);
}

fun proverka(): Operation?{
    val ref = REF_DATABASE_ROOT.child("123")
    var user: Operation? = null
    val menuListener = object : ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {
            // handle error
        }
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            user = dataSnapshot.getValue() as Operation
        }
    }
    ref.addListenerForSingleValueEvent(menuListener)
    return user
}


