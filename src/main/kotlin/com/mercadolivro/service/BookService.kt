package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) =
        bookRepository.save(book)

    fun findAll(pageble: Pageable): Page<BookModel> =
       bookRepository.findAll(pageble)

    fun findActives(pageble: Pageable): Page<BookModel> =
        bookRepository.findByStatus(BookStatus.ATIVO, pageble)

    fun findById(id: Int): BookModel =
        bookRepository.findById(id).orElseThrow{
            NotFoundException(Errors.ML101.Message.format(id), Errors.ML101.code)
        }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        update(book)
    }

    fun update(bookModel: BookModel) =
        bookRepository.save(bookModel)

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }

    fun findAllByIds(bookIds: Set<Int>): List<BookModel> =
        bookRepository.findAllById(bookIds).toList()

    fun purchase(books: MutableList<BookModel>) {
        books.map {
            it.status = BookStatus.VENDIDO
        }
        bookRepository.saveAll(books)
    }
}
