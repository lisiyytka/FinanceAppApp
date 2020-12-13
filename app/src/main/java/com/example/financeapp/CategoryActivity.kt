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
        setOnClick()
    }

    fun setOnClick(){

        val back = findViewById<ImageView>(R.id.back_arrow)
        back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val changeOperation = findViewById<TextView>(R.id.change_operation)
        changeOperation.setOnClickListener {
            if (changeOperation.text == "+")
                changeOperation.text = "-"
            else changeOperation.text = "+"
        }

        val accept = findViewById<ImageView>(R.id.okey)
        accept.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }
    }
}