package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ChooseActivityFamily : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_family)
        val newAccBtn = findViewById<TextView>(R.id.newAcc_btn)
        val logAccBtn = findViewById<TextView>(R.id.acc_btn)
        newAccBtn.setOnClickListener {
            startActivity(Intent(this,CreateFamilyActivity::class.java))
        }
        logAccBtn.setOnClickListener {
            startActivity(Intent(this, LoginToFamilyAccActivity::class.java))
        }
    }
}