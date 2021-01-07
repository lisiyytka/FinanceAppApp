package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class EntranceActivity : AppCompatActivity() {
    lateinit var local_user: User

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
            val text = findViewById<TextView>(R.id.entrance_txt)
            proverka(text)
        }
    }
}