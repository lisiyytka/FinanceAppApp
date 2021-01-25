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
import java.math.BigDecimal
import java.math.RoundingMode


lateinit var AUTH:FirebaseAuth
lateinit var REF_DATABASE_ROOT:DatabaseReference

const val NODE_USERS = "Users"
const val NODE_OPERATIONS = "Operations"
const val NODE_FAMILY = "Family"



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
            user.accessCodeToFamily = list[0]!!.accessCodeToFamily
            addd.insertUser(user)
        }
    })
}

fun makeToast(context: Context,arg:String){
    Toast.makeText(context,arg,Toast.LENGTH_LONG).show()
}

fun getIncomeAndLosses(user:DataUser, IncomeView:TextView, LossView:TextView){
    var income = IncomeView.text.toString().toDouble()
    var loss = LossView.text.toString().toDouble()
    REF_DATABASE_ROOT.child(NODE_OPERATIONS).child(user.login).addListenerForSingleValueEvent(
        AppValueEventListener {
            for (child in it.children) {
                val operation = child.getValue(Operation::class.java)
                if (operation != null)
                    if (operation.IsExpenses)
                        income += operation.Operation_operation.toDouble()
                    else
                        loss += operation.Operation_operation.toDouble()
            }
            var income1 = BigDecimal(income).setScale(2,RoundingMode.HALF_EVEN)
            var loss1 = BigDecimal(loss).setScale(2,RoundingMode.HALF_EVEN)
            IncomeView.text = "+$income1"
            LossView.text = "-$loss1"
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
                        var operatio = BigDecimal(operation.Operation_operation.toDouble()).setScale(2,RoundingMode.HALF_EVEN)
                        map["operation_sum"] = operatio
                        map["comment"] = operation.Comment_text
                        map["isExpenses"] = operation.IsExpenses
                        if (operation.IdImage != "")
                            map["image"] = operation.IdImage.toInt()
                        else
                            map["image"] = R.drawable.no_category
                        if (operation.IsExpenses){
                            map["image"] = R.drawable.income_pic
                            map["category"] = "Прибыль"
                        }
                        if (!OperationList.contains(map))
                            OperationList.add(0,map)

                    }
                }
            })
    return OperationList
}

fun getOperationsFamily(user:DataUser): ArrayList<HashMap<String, Any>> {
    var map: HashMap<String, Any>
    REF_DATABASE_ROOT.child("FamilyOperations").child(user.accessCodeToFamily).addListenerForSingleValueEvent(
            AppValueEventListener {
                for (child in it.children) {
                    val operation = child.getValue(FamilyOperation::class.java)
                    if (operation != null){
                        map = HashMap()
                        map["date"] = operation.Date
                        map["category"] = operation.Category
                        var operatio = BigDecimal(operation.Operation_operation.toDouble()).setScale(2,RoundingMode.HALF_EVEN)
                        map["operation_sum"] = operatio
                        map["comment"] = operation.Comment_text
                        map["nameSurname"] = operation.nameSurname
                        map["isExpenses"] = operation.IsExpenses
                        map["login"] = operation.login
                        if (operation.IdImage != "")
                            map["image"] = operation.IdImage.toInt()
                        else
                            map["image"] = R.drawable.no_category
                        if (operation.IsExpenses){
                            map["image"] = R.drawable.income_pic
                            map["category"] = "Прибыль"
                        }
                        if (!OperationListFamily.contains(map))
                            OperationListFamily.add(0,map)

                    }
                }
            })
    return OperationListFamily
}

fun getIncomeAndLossesFamily(user:DataUser, IncomeView:TextView, LossView:TextView){
    var income = IncomeView.text.toString().toDouble()
    var loss = LossView.text.toString().toDouble()
    REF_DATABASE_ROOT.child("FamilyOperations").child(user.accessCodeToFamily).addListenerForSingleValueEvent(
            AppValueEventListener {
                for (child in it.children) {
                    val operation = child.getValue(Operation::class.java)
                    if (operation != null)
                        if (operation.IsExpenses)
                            income += operation.Operation_operation.toDouble()
                        else
                            loss += operation.Operation_operation.toDouble()
                }
                var income1 = BigDecimal(income).setScale(2,RoundingMode.HALF_EVEN)
                var loss2 = BigDecimal(loss).setScale(2,RoundingMode.HALF_EVEN)
                IncomeView.text = "+$income1"
                LossView.text = "-$loss2"
            })
}

fun getBalanceFamily(user: DataUser, familyOperation: FamilyOperation){
    REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.accessCodeToFamily).addListenerForSingleValueEvent(
            AppValueEventListener{
                var balance = it.child("balance").getValue(String::class.java)
                if (familyOperation.IsExpenses)
                {
                    balance = (balance!!.toDouble() + familyOperation.Operation_operation.toDouble()).toString()
                }
                else {
                    balance = (balance!!.toDouble() - familyOperation.Operation_operation.toDouble()).toString()
                }
                REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.accessCodeToFamily).child("balance").setValue(balance)
            }
    )
}

fun setFamilyBalance(user:DataUser, view: TextView){
    REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.accessCodeToFamily).addListenerForSingleValueEvent(
            AppValueEventListener{
                var balance = it.child("balance").getValue(String::class.java)
                var bal = BigDecimal(balance!!.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
                view.text = bal + " руб"
            }
    )
}



