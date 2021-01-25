package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class CodeActivity : AppCompatActivity() {
    val db = LocalDataBaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code)
        val fieldCode = findViewById<TextView>(R.id.login)
        val btn_0 = findViewById<TextView>(R.id.btn_0)
        val btn_1 = findViewById<TextView>(R.id.btn_1)
        val btn_2 = findViewById<TextView>(R.id.btn_2)
        val btn_3 = findViewById<TextView>(R.id.btn_3)
        val btn_4 = findViewById<TextView>(R.id.btn_4)
        val btn_5 = findViewById<TextView>(R.id.btn_5)
        val btn_6 = findViewById<TextView>(R.id.btn_6)
        val btn_7 = findViewById<TextView>(R.id.btn_7)
        val btn_8 = findViewById<TextView>(R.id.btn_8)
        val btn_9 = findViewById<TextView>(R.id.btn_9)
        val btnDelete = findViewById<ImageView>(R.id.btn_delete)
        val loginAct = findViewById<TextView>(R.id.forget_code)
        btn_0.setOnClickListener { setTextFields("0") }
        btn_1.setOnClickListener { setTextFields("1") }
        btn_2.setOnClickListener { setTextFields("2") }
        btn_3.setOnClickListener { setTextFields("3") }
        btn_4.setOnClickListener { setTextFields("4") }
        btn_5.setOnClickListener { setTextFields("5") }
        btn_6.setOnClickListener { setTextFields("6") }
        btn_7.setOnClickListener { setTextFields("7") }
        btn_8.setOnClickListener { setTextFields("8") }
        btn_9.setOnClickListener { setTextFields("9") }

        loginAct.setOnClickListener {
            db.deleteData()
            startActivity(Intent(this,EntranceActivity::class.java))
        }

        btnDelete.setOnClickListener {
            val str=fieldCode.text.toString()
                if(str.isNotEmpty()){
                    fieldCode.text=str.substring(0,str.length-1)
                }
        }

        fieldCode.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val user = db.getUser()
                if(s.toString()==user.pin)
                {
                    OperationList = getOperations(user)
                    startActivity(Intent(fieldCode.context,LoadScreen::class.java))
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun setTextFields(str: String) {
        val fieldCode = findViewById<TextView>(R.id.login)
        fieldCode.append(str)
    }
}