package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class EntranceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)
        initFirebase()
        val registration = findViewById<TextView>(R.id.registration_btn)
        val next = findViewById<ImageView>(R.id.next_btn)
        setOnClick(registration, next)
    }

    fun setOnClick(registration: View, next: View) {
        registration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        val loginView = findViewById<EditText>(R.id.login)
        val pswrdView = findViewById<EditText>(R.id.password)
       next.setOnClickListener {
           onEqualOurUser(loginView,pswrdView)
       }
    }

    fun onEqualOurUser(loginView:EditText, pswrdView: EditText) {
        val localDb = LocalDataBaseHandler(this)
        val login = loginView.text.toString()
        val pswrd = pswrdView.text.toString()
        val checkedUser = localDb.getUser()
        if(login == "" || pswrd == "")
            {Toast.makeText(this,"Заполните все поля",Toast.LENGTH_LONG).show()}
        else{
            if(checkedUser.login==login && checkedUser.password == pswrd)
                startActivity(Intent(this, LoadScreen::class.java))
            else{
                    REF_DATABASE_ROOT.child(NODE_USERS).addListenerForSingleValueEvent(
                        AppValueEventListener {
                            if (it.hasChild(login)) {
                                REF_DATABASE_ROOT.child(NODE_USERS).child(login).addListenerForSingleValueEvent(
                                    AppValueEventListener {
                                        val pswd = it.getValue(DataUser::class.java)!!.password
                                        if (pswd == pswrd)
                                        { localDb.deleteData()
                                            fireBaseHelp(this,login)
                                            startActivity(Intent(this, LoadScreen::class.java))
                                        }else{Toast.makeText(this,"Данные неверны",Toast.LENGTH_SHORT).show()}
                                    })
                            } else {
                                Toast.makeText(this, "Такой пользователь не существует", Toast.LENGTH_SHORT).show()
                            }
                        })
            }
            }

    }
}


