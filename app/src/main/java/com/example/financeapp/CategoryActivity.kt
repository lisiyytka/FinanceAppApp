package com.example.financeapp

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.ArrayList
lateinit var img:ImageView
class CategoryActivity : AppCompatActivity() {
//    val Local_db_helper = LocalDataBaseHandler(this)
//    val LocalUser: User? = Local_db_helper.getUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
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
        val qrCodeBtn= findViewById<ImageView>(R.id.qr)
        val incomeOrLoss = findViewById<EditText>(R.id.operation_sum)
        val listImage = listOf<ImageView>(food, car, clothes, education, hobby, house,
                personalCare, medicine, pets, restaurant, sport, transport, another)
        for (i in listImage)
        {
            i.setOnClickListener {
                for (j in listImage)
                    j.isSelected = false
                i.isSelected = !i.isSelected }
        }
        setOnClick(listImage)
        qrCodeBtn.setOnClickListener {
            startActivity(Intent(this,QrActivity::class.java))
        }
        getValueFromQr(incomeOrLoss)
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
            val imgAndCategory = setCategoryName(listImage)
            if(value.text.toString() == "")
            {
                makeToast(this,"Заполните сумму")
            }
                else {
                    if (isFromMainFamily)
                    {
                        val familyOperation = FamilyOperation(user.balance, comment.text.toString(),
                                value.text.toString(), isExpenses, imgAndCategory.second, date, imgAndCategory.first, user.login, user.name + " " + user.surname)
                        REF_DATABASE_ROOT.child("FamilyOperations").child(user.accessCodeToFamily).child(date).setValue(familyOperation)
                        isFromLogActivity = true
                        OperationListFamily = getOperationsFamily(user)
                        getBalanceFamily(user,familyOperation)

                    }
                    else{
                        val operation = Operation(user.balance, comment.text.toString(),
                                value.text.toString(), isExpenses, imgAndCategory.second, date, imgAndCategory.first)
                        REF_DATABASE_ROOT.child("Operations").child(user.login).child(date).setValue(operation)
                        if (isExpenses)
                            user.balance = (user.balance.toDouble() + operation.Operation_operation.toDouble()).toString()
                        else
                            user.balance = (user.balance.toDouble() - operation.Operation_operation.toDouble()).toString()
                        REF_DATABASE_ROOT.child("Users").child(user.login).setValue(user)
                        localBd.deleteData()
                        localBd.insertUser(user)
                        OperationList = getOperations(user)
                    }
                startActivity(Intent(this, LoadScreen::class.java))
            }

        }
    }

    fun setCategoryName(listOfImageView: List<ImageView>): Pair<String, String> {
        var map: HashMap<ImageView, String> = HashMap()
        map[listOfImageView[0]] = "Продукты"
        map[listOfImageView[1]] = "Машина"
        map[listOfImageView[2]] = "Одежда"
        map[listOfImageView[3]] = "Образование"
        map[listOfImageView[4]] = "Хобби"
        map[listOfImageView[5]] = "Дом"
        map[listOfImageView[6]] = "Персональный Уход"
        map[listOfImageView[7]] = "Медицина"
        map[listOfImageView[8]] = "Животные"
        map[listOfImageView[9]] = "Ресторан"
        map[listOfImageView[10]] = "Спорт"
        map[listOfImageView[11]] = "Транспорт"
        map[listOfImageView[12]] = "Другое"

        for (key in map.keys)
            if (key.isSelected)
                return Pair(drawableMap[map[key]].toString(), map[key]!!)
        return Pair(drawableMap["Другое"].toString(), "Другое")
    }

    fun getValueFromQr(incomeOrLoss:EditText){
        val barcode = intent.getStringExtra("code")
        if(barcode == ""){
            Toast.makeText(this@CategoryActivity,Constants.BAR_CODE_NOT_FOUND, Toast.LENGTH_LONG).show()
        }else{
            if(parserStr(barcode) != "0")
                incomeOrLoss.setText(parserStr(barcode),TextView.BufferType.EDITABLE)
        }
    }

    fun parserStr(str: String?): String {
        if(str == null){
            return "0"
        }
        val a = str.indexOf("s=")
        var b = a.plus(2)
        var result = ""
        var char = str[b]
        val v: Array<Char> = arrayOf('1','2','3','4','5','6','7','8','9','0','.')
        while (char in v){
            result += char
            b = b+1
            char = str[b]
        }
        return result
    }
}