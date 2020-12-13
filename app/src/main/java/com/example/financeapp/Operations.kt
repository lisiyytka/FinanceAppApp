package com.example.financeapp

class Operation {
    var Operation_balance: String? = null
    var Comment_text: String? = null
    var Operation_operation: String? = null
    var IsExpenses: Boolean? = null
    var Category: String? = null

    constructor(operation_balance: String, comment_text: String,
                operation_operation: String, isExpenses: Boolean, category: String){
        this.Operation_balance = operation_balance
        this.Comment_text = comment_text
        this.Operation_operation = operation_operation
        this.IsExpenses = isExpenses
        this.Category = category
    }
    constructor() {

    }


}