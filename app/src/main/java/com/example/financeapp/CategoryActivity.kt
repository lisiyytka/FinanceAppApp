package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.LocalTime


class CategoryActivity : AppCompatActivity() {
//    val Local_db_helper = LocalDataBaseHandler(this)
//    val LocalUser: User? = Local_db_helper.getUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val carCategory = findViewById<ImageView>(R.id.car)
        val comment = findViewById<EditText>(R.id.comment_text)
        initFirebase()
        val food = findViewById<ImageView>(R.id.food)
        val car = findViewById<ImageView>(R.id.car)
        val clothes = findViewById<ImageView>(R.id.clothes)
        val education = findViewById<ImageView>(R.id.education)
        val hobby = findViewById<ImageView>(R.id.hobby)
        val house = findViewById<ImageView>(R.id.house)
        val personalCare = findViewById<ImageView>(R.id.personal_care)
        val medicine = findViewById<ImageView>(R.id.medicine)
        val pets = findViewById<ImageView>(R.id.pets)
        val restaurant = findViewById<ImageView>(R.id.restaurant)
        val sport = findViewById<ImageView>(R.id.sport)
        val transport = findViewById<ImageView>(R.id.transport)
        val another = findViewById<ImageView>(R.id.no_category)
        var listImage = listOf<ImageView>(food, car, clothes, education, hobby, house,
                personalCare, medicine, pets, restaurant, sport, transport, another)
        for (i in listImage)
        {
            i.setOnClickListener { i.isSelected = !i.isSelected }
        }
        setOnClick(listImage)
    }

    fun setOnClick(listImage: List<ImageView>) {

        val back = findViewById<ImageView>(R.id.back_arrow)
        back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val changeOperation = findViewById<TextView>(R.id.change_operation)
        changeOperation.text = "Прибыль"
        changeOperation.setOnClickListener {
            if (changeOperation.text == "Прибыль")
                changeOperation.text = "Затраты"
            else changeOperation.text = "Прибыль"
        }

        val accept = findViewById<TextView>(R.id.okey)
        accept.setOnClickListener {
            val value = findViewById<EditText>(R.id.operation_sum)
            val comment = findViewById<EditText>(R.id.comment_text)
            val isExpenses = changeOperation.text == "Прибыль"
            val localBd = LocalDataBaseHandler(this)
            val user = localBd.getUser()
            var date = ""
            date = DateHelper.getDate()
            var category = isItCheck(listImage)
            val operation = Operation(user.balance, comment.text.toString(),
                    value.text.toString(), isExpenses, category.toString(), date)
            REF_DATABASE_ROOT.child("Operations").child(user.login).child(date).setValue(operation)
            if (isExpenses)
                user.balance = (user.balance.toInt() + operation.Operation_operation.toInt()).toString()
            else
                user.balance = (user.balance.toInt() - operation.Operation_operation.toInt()).toString()
            REF_DATABASE_ROOT.child("Users").child(user.login).setValue(user)
            localBd.deleteData()
            localBd.insertUser(user)
            OperationList = getOperations(user)
            startActivity(Intent(this, LoadScreen::class.java))
        }
    }

    fun setCategoryPicture(listImage: List<ImageView>): ImageView {

        var result = listImage[12]
        for (i in listImage) {
            if (i == listImage[12])
            else {
                result = i
            }
        }
        return result


    }

    fun setCategoryName(i: ImageView): String {
        val food = "Продукты"
        val car = "Машина"
        val clothes = "Одежда"
        val education = "Образование"
        val hobby = "Хобби"
        val house = "Дом"
        val personalCare = "Персональный Уход"
        val medicine = "Медицина"
        val pets = "Животные"
        val restaurant = "Ресторан"
        val sport = "Спорт"
        val transport = "Транспорт"
        val another = "Другое"
        var listNames = listOf(food, car, clothes, education, hobby, house,
                personalCare, medicine, pets, restaurant, sport, transport, another)
        var result = another
        for (i in listNames) {
            if (i == result)
            else {
                result = i
            }
        }
        return result
    }

    fun isItCheck(listImage: List<ImageView>){
        for (i in listImage){
            if(i.isSelected)
            {
                setCategoryName(i)
            }
        }
    }
}