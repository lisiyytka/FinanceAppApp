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
const val NODE_OPERATIONS = "Operations"



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

fun getIncomeAndLosses(user:DataUser, IncomeView:TextView, LossView:TextView){
    var income = IncomeView.text.toString().toInt()
    var loss = LossView.text.toString().toInt()
    REF_DATABASE_ROOT.child(NODE_OPERATIONS).child(user.login).addListenerForSingleValueEvent(
        AppValueEventListener {
            for (child in it.children) {
                val operation = child.getValue(Operation::class.java)
                if (operation != null)
                    if (operation.IsExpenses)
                        income += operation.Operation_operation.toInt()
                    else
                        loss += operation.Operation_operation.toInt()
            }
            IncomeView.text = "+$income"
            LossView.text = "-$loss"
        })
}

fun getOperations(user:DataUser): ArrayList<HashMap<String, Any>> {
    var map: HashMap<String, Any>
    REF_DATABASE_ROOT.child(NODE_OPERATIONS).child(user.login).addListenerForSingleValueEvent(
            AppValueEventListener {
                for (child in it.children) {
                    val operation = child.getValue(Operation::class.java)
                    if (operation != null){
                        map = HashMap()
                        map["date"] = operation.Date
                        map["category"] = operation.Category
                        map["operation_sum"] = operation.Operation_operation
                        map["comment"] = operation.Comment_text
                        if (operation.IdImage != "")
                            map["image"] = operation.IdImage.toInt()
                        else
                            map["image"] = R.drawable.no_category
                        if (!OperationList.contains(map))
                            OperationList.add(0,map)

                    }
                }
            })
    return OperationList
}



