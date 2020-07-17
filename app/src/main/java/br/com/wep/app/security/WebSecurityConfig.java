package br.com.wep.app.security;

import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .anyRequest().authenticated()
                .and();

        //TODO

        //filtrar requisições de login

        //TODO

        //filtra outras requisições para verificar a presença do JWT no header
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //conta default
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("password");
    }

}
