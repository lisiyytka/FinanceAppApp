package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.LocalTime


class CategoryActivity : AppCompatActivity() {
//    val Local_db_helper = LocalDataBaseHandler(this)
//    val LocalUser: User? = Local_db_helper.getUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setOnClick()
        val carCategory = findViewById<ImageView>(R.id.car)
        val comment = findViewById<EditText>(R.id.comment_text)
        initFirebase()
    }

    fun setOnClick(){

        val back = findViewById<ImageView>(R.id.back_arrow)
        back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val changeOperation = findViewById<TextView>(R.id.change_operation)
        changeOperation.setOnClickListener {
            if (changeOperation.text == "Прибыль")
                changeOperation.text = "Затраты"
            else changeOperation.text = "Прибыль"
        }

        val accept = findViewById<ImageView>(R.id.okey)
        accept.setOnClickListener {
            val value = findViewById<EditText>(R.id.operation_sum)
            val comment = findViewById<EditText>(R.id.comment_text)
            val isExpenses = changeOperation.text == "Прибыль"
            val localBd = LocalDataBaseHandler(this)
            val user = localBd.getUser()
            var date = ""
            date = DateHelper.getDate()
             val operation = Operation(user.balance, comment.text.toString(),
                 value.text.toString(), isExpenses, "Another", date)
            REF_DATABASE_ROOT.child("Operations").child(user.login).child(date).setValue(operation)
            if (isExpenses)
                user.balance = (user.balance.toInt() + operation.Operation_operation.toInt()).toString()
            else
                user.balance = (user.balance.toInt() - operation.Operation_operation.toInt()).toString()
            REF_DATABASE_ROOT.child("Users").child(user.login).setValue(user)
            localBd.deleteData()
            localBd.insertUser(user)
            OperationList = getOperations(user)
            startActivity(Intent(this, LoadScreen::class.java))
        }
    }
}