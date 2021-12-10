package com.mercadolivro.controller.mapper

import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {
    fun toModel(postPurchaseRequest: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(postPurchaseRequest.customerId)
        val books = bookService.findAllByIds(postPurchaseRequest.bookIds)

        return PurchaseModel(
            customerModel = customer,
            books = books,
            price = books.sumOf { it.price }
        )
    }
}