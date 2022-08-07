package com.mcamelo.msgApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {
    @Autowired
    private Environment env;

    private static final String[] PUBLIC = { "/actuator/**", "/h2-console/**", "/swagger-ui/**","/swagger-ui.html", "/api-docs"};
    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/webjars/**",
            "/actuator/**"
    };

    private static final String [] USER_OR_ADMIN = {"/actuator/**","/post/**"};

    private static final String [] ADMIN = {"/users/**"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // enable database  h2
//        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
//            http.headers().frameOptions().disable();
//        }
        http.cors().and()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
//                .antMatchers(HttpMethod.GET, "/user/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }





}