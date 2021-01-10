package com.example.financeapp

class User {
    var login: String = ""
    var name: String = ""
    var surname: String = ""
    var password: String = ""
    var phone: String = ""
    var balance: String = ""
    var pin: String = ""

    constructor(){}

    constructor(login: String, name: String, surname: String, password: String,
                phone: String, balance: String, PIN: String)
    {
        this.login = login
        this.name = name
        this.surname = surname
        this.password = password
        this.phone = phone
        this.balance = balance
        this.pin = PIN
    }
}