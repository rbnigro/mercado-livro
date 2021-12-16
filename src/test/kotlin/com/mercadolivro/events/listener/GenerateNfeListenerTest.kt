package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.helper.buildPurchase
import com.mercadolivro.service.PurchaseService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class GenerateNfeListenerTest{

    @MockK
    private lateinit var purchaseService: PurchaseService

    @InjectMockKs
    private lateinit var generateNfeListener: GenerateNfeListener

    @Test
    fun `should geneterate nfe`() {
        val purchase = buildPurchase(nfe = null)
        val fakeNFE = UUID.randomUUID()
        val purchaseExpected = purchase.copy(nfe = fakeNFE.toString())

        mockkStatic(UUID::class) // mockar retorno

        every { UUID.randomUUID() } returns fakeNFE
        every { purchaseService.update(purchaseExpected) } just runs

        generateNfeListener.listen(PurchaseEvent(this, purchase))

        verify(exactly = 1) { purchaseService.update(purchaseExpected)  }
    }
}