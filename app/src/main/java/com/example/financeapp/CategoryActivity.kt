package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val changeOperation = findViewById<TextView>(R.id.change_operation)

        changeOperation.setOnClickListener {
            if (changeOperation.text == "+")
            changeOperation.text = "-"
            else changeOperation.text = "+"
        }

        val back = findViewById<ImageView>(R.id.back_arrow)

        back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val enter = findViewById<EditText>(R.id.comment_text)

        enter.setOnEditorActionListener { v, actionId, event ->
            if (event.action == KeyEvent.ACTION_DOWN &&event.keyCode == KeyEvent.KEYCODE_ENTER) {
                startActivity(Intent(this, MainActivity::class.java))
            }
            false
        }
    }
}