package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class CreateFamilyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_family)
        val createFamilyAccBtn = findViewById<ImageView>(R.id.next_btn)
        val db = LocalDataBaseHandler(this)
        val user = db.getUser()
        val balanceFamily = findViewById<EditText>(R.id.code)
        val passwordFamily = findViewById<EditText>(R.id.code1)
        val familyId = findViewById<TextView>(R.id.family_id)
        familyId.append(" "+ user.login)

        createFamilyAccBtn.setOnClickListener {
            if (balanceFamily.text.toString() != ""){
                REF_DATABASE_ROOT.child(NODE_USERS).child(user.login).child("accessCodeToFamily")
                        .setValue(user.login)
                REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.login).child("balance").setValue(balanceFamily.text.toString())
                REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.login).child("password").setValue(passwordFamily.text.toString())
                REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.login).child(user.login).setValue(user.login)
                user.accessCodeToFamily = user.login
                db.deleteData()
                db.insertUser(user)
                isFromLogActivity = true
                startActivity(Intent(this, LoadScreen::class.java))
            }
            else
                makeToast(this,"Заполните поле баланса")
        }
    }
}