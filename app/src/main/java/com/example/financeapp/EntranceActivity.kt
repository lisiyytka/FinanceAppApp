package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class EntranceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        val registration = findViewById<TextView>(R.id.registration_btn)

        registration.setOnClickListener{
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        val next = findViewById<Button>(R.id.next_btn)

        next.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}