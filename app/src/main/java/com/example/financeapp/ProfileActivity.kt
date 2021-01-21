package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior


class ProfileActivity : AppCompatActivity() {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val db = LocalDataBaseHandler(this)
        val user = db.getUser()
        val nameSurname = findViewById<TextView>(R.id.nameSurname)
        nameSurname.text = user.name + " " + user.surname
        val fieldLogin = findViewById<TextView>(R.id.profile_login)
        fieldLogin.text = user.login
        val familyBtn = findViewById<Button>(R.id.family_btn)
        familyBtn.setOnClickListener {
            if (user.accessCodeToFamily != "") {
                isFromLogActivity = true
                getOperationsFamily(user)
                startActivity(Intent(this, LoadScreen::class.java))
            }
            else
                startActivity(Intent(this, LoginToFamilyAccActivity::class.java))
        }
    }
}