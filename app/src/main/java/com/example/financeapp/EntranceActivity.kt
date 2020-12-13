package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class EntranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        val registration = findViewById<TextView>(R.id.registration_btn)
        val next = findViewById<ImageView>(R.id.next_btn)

        setOnClick(registration, next)

    }

    fun setOnClick(registration: View, next: View) {
        registration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        next.setOnClickListener {
            val local_db_helper = LocalDataBaseHandler(this)
            val login = findViewById<EditText>(R.id.login)
            val password = findViewById<EditText>(R.id.password)
            val user = local_db_helper.getUser()
            if (user != null)
                if (user.Password == password.text.toString() && user.Login == login.text.toString())
                    startActivity(Intent(this, MainActivity::class.java))
                else
                    print("Что-то неправильно")
            else
                print("Такого нет")

        }
    }
}