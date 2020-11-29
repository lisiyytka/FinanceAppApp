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


class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val editText = findViewById<EditText>(R.id.comment_text)
        setOnClick()

//        editText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
//            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
//                startActivity(Intent(this, MainActivity::class.java))
//                return@OnKeyListener true
//            }
//            false
//        })

    }

    fun setOnClick(){

        val back = findViewById<ImageView>(R.id.back_arrow)
        val acc = intent.getIntExtra("account", 0)

        back.setOnClickListener {
            if (acc == 0)
            startActivity(Intent(this, MainActivity::class.java))
            else startActivity(Intent(this, MainFamilyActivity::class.java))
        }

        val changeOperation = findViewById<TextView>(R.id.change_operation)
        changeOperation.setOnClickListener {
            if (changeOperation.text == "+")
                changeOperation.text = "-"
            else changeOperation.text = "+"
        }
    }
}