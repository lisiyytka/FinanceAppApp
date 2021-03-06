package com.example.financeapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.security.AccessControlContext
val DATABASE_NAME = "MyDB"
val TABLE_NAME = "Users"
val COL_LOGIN = "Login"
val COL_PASSWORD = "Password"
val COL_PIN= "PIN"
val COL_NAME = "Name"
val COL_SURNAME= "Surname"
val COL_PHONE= "Phone"
val COL_BALANCE= "Balance"
val COL_ACCESS= "Access"

class LocalDataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1) {


    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_LOGIN + " VARCHAR(256) PRIMARY KEY, " +
                COL_PASSWORD + " VARCHAR(256), " +
                COL_PIN + " VARCHAR(256), " +
                COL_NAME + " VARCHAR(256), " +
                COL_PHONE + " VARCHAR(256), " +
                COL_BALANCE + " VARCHAR(256), " +
                COL_SURNAME + " VARCHAR(256), " +
                COL_ACCESS + " VARCHAR(256))";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertUser(user: DataUser) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_LOGIN, user.login)
        cv.put(COL_PASSWORD, user.password)
        cv.put(COL_PIN, user.pin)
        cv.put(COL_NAME, user.name)
        cv.put(COL_PHONE, user.phone)
        cv.put(COL_BALANCE, user.balance)
        cv.put(COL_SURNAME, user.surname)
        cv.put(COL_ACCESS, user.accessCodeToFamily)
        db.insert(TABLE_NAME, null, cv)
//        if (result == -1.toLong())
//            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show()
//        else
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun getUser(): DataUser {
        val list: MutableList<User> = ArrayList()
        val user = DataUser()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)
//        if (result.moveToFirst()) {
//            do {
//                var field = Field()
//                field.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
//                field.balance = result.getString(result.getColumnIndex(COL_BAL)).toDouble()
//                field.category = result.getString(result.getColumnIndex(COL_CATEGORY))
//                field.comment = result.getString(result.getColumnIndex(COL_COMMENT))
//                field.date = result.getString(result.getColumnIndex(COL_DATE))
//                field.loss = result.getString(result.getColumnIndex(COL_LOSS)).toDouble()
//                field.income = result.getString(result.getColumnIndex(COL_INCOME)).toDouble()
//                field.password = result.getString(result.getColumnIndex(COL_PAS))
//                list.add(field)
//            } while (result.moveToNext())
//        }
        if (result.moveToFirst()){
            do {
            user.name = result.getString(result.getColumnIndex(COL_NAME)).toString()
            user.balance = result.getString(result.getColumnIndex(COL_BALANCE)).toString()
            user.surname = result.getString(result.getColumnIndex(COL_SURNAME)).toString()
            user.phone = result.getString(result.getColumnIndex(COL_PHONE)).toString()
            user.pin = result.getString(result.getColumnIndex(COL_PIN)).toString()
            user.password = result.getString(result.getColumnIndex(COL_PASSWORD)).toString()
            user.login = result.getString(result.getColumnIndex(COL_LOGIN)).toString()
            user.accessCodeToFamily = result.getString(result.getColumnIndex(COL_ACCESS)).toString()
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return user
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        db.close()
    }
}