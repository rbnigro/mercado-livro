package com.mercadolivro.enums

enum class Errors(val code: String, val Message: String) {
    // Book
    ML101("ML-101","Book [%s] not exists!"),
    ML102("ML-102", "Cannot update book with status [%s]"),
    // Customer
    ML201("ML-201","Customer [%s] not exists!")
    }