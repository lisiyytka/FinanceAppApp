package com.example.financeapp

var drawableMap = HashMap<String,Int>()


fun getDrawableId(){
    drawableMap = HashMap()
    drawableMap["Продукты"] = R.drawable.food
    drawableMap["Машина"] = R.drawable.car
    drawableMap["Одежда"] = R.drawable.clothes
    drawableMap["Образование"] = R.drawable.education
    drawableMap["Хобби"] = R.drawable.hobby
    drawableMap["Дом"] = R.drawable.house
    drawableMap["Персональный Уход"] = R.drawable.personal_care
    drawableMap["Медицина"] = R.drawable.medicine
    drawableMap["Животные"] = R.drawable.pets
    drawableMap["Ресторан"] = R.drawable.restaurant
    drawableMap["Спорт"] = R.drawable.sport
    drawableMap["Транспорт"] = R.drawable.transport
    drawableMap["Другое"] = R.drawable.no_category
}
