package com.mercadolivro.config

import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val customerRepository: CustomerRepository
): WebSecurityConfigurerAdapter() {
    private val PUBLIC_MATCHES = arrayListOf<String>()
    private val PUBLIC_POST_MATCHES = arrayOf(
        "/customer/"
    )

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable() // permite autenticação servidor local
        http.authorizeHttpRequests()
            .antMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHES).permitAll()    // endpoint nao autenticado
            .anyRequest().authenticated() // requer endpoint autenticado
        http.addFilter(AuthenticationFilter(authenticationManager(), customerRepository))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // requisições independentes
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()
}