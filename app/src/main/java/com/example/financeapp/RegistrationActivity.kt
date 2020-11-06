package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val entrance = findViewById<TextView>(R.id.already_have_acc)

        entrance.setOnClickListener {
            startActivity(Intent(this, EntranceActivity::class.java))
        }

        val next = findViewById<Button>(R.id.next_btn)
        val check = findViewById<CheckBox>(R.id.checkbox)
        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)

        next.setOnClickListener {
            if (check.isChecked && login.text.isNotEmpty() && password.text.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
            }
            else { Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show() }
        }
    }
}