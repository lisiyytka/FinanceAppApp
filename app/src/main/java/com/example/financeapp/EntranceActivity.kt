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
            val local_db_helper = LocalDataBaseHandler(this)
            val user = local_db_helper.getUser()
            val login = findViewById<EditText>(R.id.login)
            val password = findViewById<EditText>(R.id.password)
            if (user != null)
                Toast.makeText(this,user.Login + user.Balance + user.Password, Toast.LENGTH_LONG).show()


        }
    }
}