package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class LoginToFamilyAccActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_to_family_acc)

        setOnClick();
    }

    fun setOnClick(){

        val signIn = findViewById<ImageView>(R.id.next_btn)

        signIn.setOnClickListener {
            startActivity(Intent(this, MainFamilyActivity::class.java))
        }

    }
}