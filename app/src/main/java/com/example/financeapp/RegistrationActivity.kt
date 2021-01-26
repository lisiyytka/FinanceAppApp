package com.example.financeapp

import android.content.Context
import android.content.Intent
import android.content.pm.LauncherApps
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        setOnClick(entrance, next)

        val phone = findViewById<EditText>(R.id.phone)
        var lastChar = " "
        phone.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                val digits = phone.text.length
                if (digits > 1)
                    lastChar = phone.text.substring(digits-1)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val digits = phone.text.length
                if (digits == 1)
                    phone.text.append("+7",0,0)
                if (lastChar != "-"){
                    if (digits == 2)
                        phone.text.append(" ")
                if (digits == 5 || digits == 9|| digits == 11 || digits == 12)
                    phone.text.append("-")
            }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

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
                && phone.text.isNotEmpty() && PIN.text.isNotEmpty() && PIN.text.length == 4 && password.text.length >= 4) {
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
