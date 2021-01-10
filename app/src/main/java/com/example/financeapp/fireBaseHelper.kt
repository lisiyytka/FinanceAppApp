package com.example.financeapp

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList
import javax.security.auth.callback.Callback


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

fun getUserByLogin(login: String, firebaseCallback: FirebaseCallback){
    initFirebase()
    val ref = REF_DATABASE_ROOT.child(NODE_USERS).child(login)
    var listData = ArrayList<DataUser?>()
    val listener =object: ValueEventListener {
        override fun onCancelled(databaseError: DatabaseError) {
        }
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val new_user = dataSnapshot.getValue(DataUser::class.java)
            listData.add(new_user)
            firebaseCallback.onCallback(listData)
        }
    }
    ref.addValueEventListener(listener)
}

fun xui(aaa: DataUser?): DataUser?{
    val a= aaa
    return a
}

fun fireBaseHelp( context: Context){
    val log = "lisiy"
//    val prov = findViewById<TextView>(R.id.budget)
    val addd = LocalDataBaseHandler(context)
    var user = DataUser()
    getUserByLogin(log, object: FirebaseCallback{
        override fun onCallback(list: MutableList<DataUser?>) {
            super.onCallback(list)
            user.balance = list[0]!!.balance
            addd.insertUser(user)
        }
    })
}




