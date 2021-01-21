package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
var isFromLogActivity: Boolean = false
class LoginToFamilyAccActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_to_family_acc)

        setOnClick();
    }

    fun setOnClick(){
        initFirebase()
        val db = LocalDataBaseHandler(this)
        val user = db.getUser()
        val signIn = findViewById<ImageView>(R.id.next_btn)
        val createFamilyAccBtn = findViewById<ImageView>(R.id.create_family_acc_btn)
        val fieldCode = findViewById<EditText>(R.id.code)
        createFamilyAccBtn.setOnClickListener {
            REF_DATABASE_ROOT.child(NODE_USERS).child(user.login).child("accessCodeToFamily")
                    .setValue(user.login)
            REF_DATABASE_ROOT.child(NODE_FAMILY).child(user.login).child(user.login).setValue(user.login)
            user.accessCodeToFamily = user.login
            db.deleteData()
            db.insertUser(user)
        }

        signIn.setOnClickListener {
            val ref = REF_DATABASE_ROOT.child(NODE_FAMILY)
            ref.addListenerForSingleValueEvent(
                    AppValueEventListener {
                        if (it.hasChild(fieldCode.text.toString())) {
                            ref.child(fieldCode.text.toString()).child(user.login).setValue(user.login)
                            user.accessCodeToFamily = fieldCode.text.toString()
                            REF_DATABASE_ROOT.child(NODE_USERS).child(user.login).child("accessCodeToFamily")
                                    .setValue(user.accessCodeToFamily)
                            db.deleteData()
                            db.insertUser(user)
                            getOperationsFamily(user)
                            isFromLogActivity = true
                            startActivity(Intent(this,LoadScreen::class.java))
                        }
                        else
                            makeToast(this, "Такой семьи нет")
                    })
        }
    }
}
