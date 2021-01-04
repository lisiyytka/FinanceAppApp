package com.example.financeapp

class User {
    var Login: String? = null
    var Name: String? = null
    var Surname: String? = null
    var Password: String? = null
    var Phone: String? = null
    var Balance: String? = null
    var PIN: String? = null

    constructor(){}

    constructor(login: String?, name: String?, surname: String?, password: String?,
                phone: String?, balance: String?, PIN: String?)
    {
        this.Login = login
        this.Name = name
        this.Surname = surname
        this.Password = password
        this.Phone = phone
        this.Balance = balance
        this.PIN = PIN
    }

}