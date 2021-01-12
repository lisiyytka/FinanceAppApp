package com.example.financeapp

data class Operation(
    var Operation_balance: String = "",
    var Comment_text: String = "",
    var Operation_operation: String = "",
    var IsExpenses: Boolean = true,
    var Category: String = "",
    var Date: String = "",
    var IdImage: String = ""
)