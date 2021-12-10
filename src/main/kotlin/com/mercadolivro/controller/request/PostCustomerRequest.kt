package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvaliable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest (

    @field:NotEmpty(message="Nome não deve ser vazio")
    var name: String,

    @field:Email(message="E-mail inválido")
    @EmailAvaliable(message = "Email em uso")
    var email: String,

    @field:NotEmpty(message ="Senha deve ser informada")
    var password: String
)