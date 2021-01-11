package com.example.financeapp

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.ArrayList
import javax.security.auth.callback.Callback
import com.example.financeapp.RegistrationActivity


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

fun fireBaseHelp( context: Context, log: String){
    val addd = LocalDataBaseHandler(context)
    var user = DataUser()
    getUserByLogin(log, object: FirebaseCallback{
        override fun onCallback(list: MutableList<DataUser?>) {
            super.onCallback(list)
            user.balance = list[0]!!.balance
            user.login = list[0]!!.login
            user.name = list[0]!!.name
            user.phone = list[0]!!.phone
            user.pin = list[0]!!.pin
            user.surname = list[0]!!.surname
            user.password = list[0]!!.password
            addd.insertUser(user)
        }
    })
}

fun makeToast(context: Context,arg:String){
    Toast.makeText(context,arg,Toast.LENGTH_LONG).show()
}




