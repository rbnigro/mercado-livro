package com.mercadolivro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync

@EnableJpaRepositories(basePackages= arrayOf("com.mercadolivro"))
@EnableAsync
@SpringBootApplication
class MercadoLivroApplication

fun main(args: Array<String>) {
	runApplication<MercadoLivroApplication>(*args)

	// TODO Rever aula 74
}
