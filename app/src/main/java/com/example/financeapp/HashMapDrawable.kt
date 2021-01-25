package com.example.financeapp

var drawableMap = HashMap<String,Int>()
var colorMap = HashMap<String,Int>()

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
    drawableMap["Прибыль"] = R.drawable.income_pic
}

fun getColorId(){
    colorMap = HashMap()
    colorMap["Продукты"] = R.color.color_food_category
    colorMap["Машина"] = R.color.color_car_category
    colorMap["Одежда"] = R.color.color_clothes_category
    colorMap["Образование"] = R.color.color_education_category
    colorMap["Хобби"] = R.color.color_hobby_category
    colorMap["Дом"] = R.color.color_house_category
    colorMap["Персональный Уход"] = R.color.color_personal_care_category
    colorMap["Медицина"] = R.color.color_medicine_category
    colorMap["Животные"] = R.color.color_pets_category
    colorMap["Ресторан"] = R.color.color_restaurant_category
    colorMap["Спорт"] = R.color.color_sport_category
    colorMap["Транспорт"] = R.color.color_transport_category
    colorMap["Другое"] = R.color.color_no_category
}
