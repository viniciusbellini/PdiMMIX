package com.bellini.pdimmixx.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(val userDetailsService: UserDetailsService) : WebSecurityConfigurerAdapter()
{
    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http.headers().frameOptions().disable()
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .antMatchers("/users/**").permitAll()
//                .antMatchers("/h2_console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager()))
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

//    override fun configure(http: HttpSecurity) {
//        http.headers().frameOptions().disable()
//        http.cors().and().csrf().disable()
//        http.authorizeRequests()
//                .antMatchers("/users/**").permitAll()
//                .antMatchers("/h2_console/**").permitAll()
//                .anyRequest().authenticated()
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//    }


}