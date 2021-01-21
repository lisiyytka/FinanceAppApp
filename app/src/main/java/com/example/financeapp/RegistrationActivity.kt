package com.example.financeapp

import android.content.Context
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
        REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
        val entrance = findViewById<TextView>(R.id.already_have_acc)
        val next = findViewById<ImageView>(R.id.next_btn)
        val check = findViewById<CheckBox>(R.id.checkbox)
        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val prov = findViewById<TextView>(R.id.budget)
        setOnClick(entrance, next)
    }

    fun setOnClick(entrance: View, next: View) {
        entrance.setOnClickListener {
            startActivity(Intent(this, EntranceActivity::class.java))
        }
        val check = findViewById<CheckBox>(R.id.checkbox)
        val login = findViewById<EditText>(R.id.login)
        val password = findViewById<EditText>(R.id.password)
        val reg_balance = findViewById<EditText>(R.id.balance)
        val name = findViewById<EditText>(R.id.name)
        val surname = findViewById<EditText>(R.id.surname)
        val phone = findViewById<EditText>(R.id.phone)
        val PIN = findViewById<EditText>(R.id.pin)

        next.setOnClickListener {
            onRightEnter(check, login, password, reg_balance, name, surname, phone, PIN)
        }
    }

    fun onRightEnter(check: CheckBox, login: EditText, password: EditText,
                     reg_balance: EditText, name: EditText, surname: EditText, phone: EditText, PIN: EditText) {
        if (check.isChecked && login.text.isNotEmpty() && password.text.isNotEmpty()
                && reg_balance.text.isNotBlank() && name.text.isNotEmpty() && surname.text.isNotEmpty()
                && phone.text.isNotEmpty() && PIN.text.isNotEmpty()) {
            checkUserOnRepeat(login, password, reg_balance, name, surname, phone, PIN, this)
        } else {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show()
        }
    }

    fun checkUserOnRepeat(login: EditText, password: EditText,
                          reg_balance: EditText, name: EditText, surname: EditText,
                          phone: EditText, PIN: EditText, context: Context) {
        REF_DATABASE_ROOT.child(NODE_USERS).addListenerForSingleValueEvent(
                AppValueEventListener {
                    if (it.hasChild(login.text.toString())) {
                        Toast.makeText(context, "Такой пользователь уже существует", Toast.LENGTH_SHORT).show()
                    } else {
                        addNewUser(login, password, reg_balance, name, surname, phone, PIN)
                    }
                })
    }

    fun addNewUser(login: EditText, password: EditText,
                   reg_balance: EditText, name: EditText, surname: EditText, phone: EditText, PIN: EditText) {
        val newUser = DataUser(login.text.toString(),
                name.text.toString(),
                surname.text.toString(),
                password.text.toString(),
                phone.text.toString(),
                reg_balance.text.toString(),
                PIN.text.toString(), "")
        val local_db = LocalDataBaseHandler(this)
        local_db.deleteData()
        local_db.insertUser(newUser)
        REF_DATABASE_ROOT.child("Users").child(login.text.toString()).setValue(newUser)
        startActivity(Intent(this, EntranceActivity::class.java))
    }

}
