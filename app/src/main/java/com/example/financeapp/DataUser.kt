package com.example.financeapp

import java.io.Serializable

data class DataUser(
    val id:String = "",
    var login: String = "",
    var name: String = "",
    var surname: String = "",
    var password: String = "",
    var phone: String = "",
    var balance: String = "",
    var pin: String = "")

