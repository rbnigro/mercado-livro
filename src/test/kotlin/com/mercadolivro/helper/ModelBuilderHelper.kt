package com.mercadolivro.helper

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Role
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.model.PurchaseModel
import java.math.BigDecimal
import java.util.*


fun buildCustomer(
    id: Int? = null,
    name: String = "Customer name",
    email: String = "${UUID.randomUUID()}@email.com", // unique
    password: String = "password"
) = CustomerModel(
    id = id,
    name = name,
    email = email,
    status = CustomerStatus.ATIVO,
    password = password,
    roles = setOf(Role.CUSTOMER)
)

fun buildPurchase(
    id: Int? = null,
    customer: CustomerModel = buildCustomer(),
    books: MutableList<BookModel> = mutableListOf(),
    nfe: String = UUID.randomUUID().toString(),
    price: BigDecimal = BigDecimal.TEN
) = PurchaseModel(
    id = id,
    customerModel = customer,
    books = books,
    nfe = nfe,
    price = price
)

// TODO criar buildBooks