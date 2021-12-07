package com.mercadolivro.enums

enum class Errors(val code: String, val Message: String) {
    // Book
    ML101("ML-101","Book %s not exists!"),

    // Customer
    ML201("ML-201","Customer %s not exists!")
    }