package com.daklan.thymeleafcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configurationGlobal(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("admin").roles("ADMIN", "USER")
                .and().withUser("user").password("user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests().antMatchers("/", "/index/**", "/product/**", "/checkout", "/docheckout").permitAll()
               .and().authorizeRequests().antMatchers("/login", "/logout").permitAll()
               .and().authorizeRequests().antMatchers("/static/css/**", "/js/**", "/img/**", "/**/favicon.ico").permitAll()
               .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
               .and().logout()
               .deleteCookies("remove")
               .invalidateHttpSession(true)
               .logoutUrl("/logout")
               .logoutSuccessUrl("/logout-success")
               .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}