package com.bellini.pdimmixx.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter()
{

    override fun configure(http: HttpSecurity) {
        http.headers().frameOptions().disable()
        http.cors().and().csrf().disable()
        http.authorizeRequests()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/h2_console/**").permitAll()
                .anyRequest().authenticated()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }


}