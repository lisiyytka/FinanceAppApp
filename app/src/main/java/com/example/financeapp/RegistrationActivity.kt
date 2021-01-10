package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.FirebaseDatabase



class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val addd = LocalDataBaseHandler(this)
        addd.deleteData()
        REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
        val entrance = findViewById<TextView>(R.id.already_have_acc)
        val next = findViewById<ImageView>(R.id.next_btn)
        val check = findViewById<CheckBox>(R.id.checkbox)
        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val prov = findViewById<TextView>(R.id.budget)
        fireBaseHelp(this)
        setOnClick(entrance, next)
    }

    fun setOnClick(entrance: View, next: View){
        entrance.setOnClickListener {
            startActivity(Intent(this, EntranceActivity::class.java))
        }
        val check = findViewById<CheckBox>(R.id.checkbox)
        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val reg_balance = findViewById<EditText>(R.id.balance)
        next.setOnClickListener {
            setOnClick(entrance, next)
            startActivity(Intent(this,MainActivity::class.java))
//            if (check.isChecked && login.text.isNotEmpty() && password.text.isNotEmpty()) {
//                val newUser = User(login.text.toString(),"null","null",password.text.toString(),"null", reg_balance.text.toString(),"null")
//                val local_db = LocalDataBaseHandler(this)
//                local_db.insertUser(newUser)
//                REF_DATABASE_ROOT.child("Users").child(login.text.toString()).setValue(newUser)
//                startActivity(Intent(this, MainActivity::class.java))
//            }
//            else { Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show() }
        }
    }
}