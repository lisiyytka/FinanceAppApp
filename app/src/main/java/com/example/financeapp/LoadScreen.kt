package com.example.financeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoadScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val localDb = LocalDataBaseHandler(this)
        setContentView(R.layout.activity_load_screen)
//        OperationList = getOperations(localDb.getUser())
        Thread.sleep(2000)
        if(isFromLogActivity)
        {
            isFromLogActivity = false
            startActivity(Intent(this,MainFamilyActivity::class.java))
        }
        else{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
}