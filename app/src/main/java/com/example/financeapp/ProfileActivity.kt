package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

class ProfileActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val familyAcc = findViewById<Button>(R.id.familyAccount_btn)
        val myAcc = findViewById<Button>(R.id.account_btn)

        setOnClick(familyAcc, myAcc)
    }

    fun setOnClick(familyAcc: View, myAcc: View) {
        familyAcc.setOnClickListener {
            startActivity(Intent(this, LoginToFamilyAccActivity::class.java))
        }

        myAcc.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}