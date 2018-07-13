package com.Configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



/**
 * Application Configuration, Contains the Password Encoder Bean And HTTP CORS Configuration.
 * @author - Qays
 */
@Configuration
@EnableWebSecurity
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {




    /**
     * Authenticate the password Encoder
     * @param auth AuthenticationManagerBuilder
     * @throws Exception if cannot authenticate the encoder
     *
     */
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder());
    }

    /**
     *  Creating new BCryptPasswordEncoder instance
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }



    /**
     * Disabling CORS Configurations
     * @param http HttpSecurity
     * @throws Exception if cannot configure security
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().and();
    }

}