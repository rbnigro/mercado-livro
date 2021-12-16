package com.mercadolivro.repository

import com.mercadolivro.helper.buildCustomer
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    // injeta a classe real
    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @Test
    fun `should return name containing`() {
        val nome01 = customerRepository.save(buildCustomer(name = "Nome 01"))
        val nome02 = customerRepository.save(buildCustomer(name = "Nome 02"))
        //val nome03 = customerRepository.save(buildCustomer(name = "Outro 03"))

        val customers = customerRepository.findByNameContaining("me 0")

        assertEquals(listOf(nome01, nome02), customers)
    }

    @Nested
    inner class `existis by email` {
        @Test
        fun `should return true when email exists`() {
            val email = "teste@email.com"
            customerRepository.save(buildCustomer(email = email))

            val exists = customerRepository.existsByEmail(email)

            assertTrue(exists)
        }

        @Test
        fun `should return false when email do not exists`() {
            val email = "donotexists@email.com"

            val exists = customerRepository.existsByEmail(email)

            assertFalse(exists)
        }
    }

    @Nested
    inner class `find by email` {
        @Test
        fun `should return customer when email exists`() {
            val email = "teste@email.com"
            val customer = customerRepository.save(buildCustomer(email = email))

            val result = customerRepository.findByEmail(email)

            assertNotNull(result)
            assertEquals(customer, result)
        }

        @Test
        fun `should return null when email do not exists`() {
            val email = "donotexists@email.com"

            val result = customerRepository.findByEmail(email)

            assertNull(result)
        }
    }
}