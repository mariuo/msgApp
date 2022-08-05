package com.mcamelo.msgApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  {
    @Autowired
    private Environment env;

    private static final String[] PUBLIC = { "/oauth/token", "/h2-console/**","/swagger-ui/**"};

    private static final String [] USER_OR_ADMIN = {"/post/**"};

    private static final String [] ADMIN = {"/users/**"};

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    SecurityFilterChain web(HttpSecurity http) throws Exception {
//        http
//                // ...
//                .authorizeHttpRequests(authorize -> authorize
//                        .mvcMatchers("/oauth/**", "/h2-console").permitAll()
//                        .mvcMatchers("/user/**").hasRole("ADMIN")
//                        .anyRequest().denyAll()
//		);
//        return http.build();
//    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        //return (web) -> web.ignoring().antMatchers("/**","/actuator/**", "/js/**", "/webjars/**");
        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/swagger-ui/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // enable database  h2
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, PUBLIC).permitAll()
//                .antMatchers(HttpMethod.GET, "/user/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/user/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .authenticationManager(new CustomAuthenticationManager());
//        return http.build();
//    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        // enable database  h2
//        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
//            http.headers().frameOptions().disable();
//        }
//
//        http.authorizeRequests()
//                .antMatchers(PUBLIC).permitAll()
//                .antMatchers(HttpMethod.GET, USER_OR_ADMIN).permitAll()
//                .antMatchers(USER_OR_ADMIN).hasAnyRole("USER", "ADMIN")
//                .antMatchers(ADMIN).hasRole("ADMIN")
//                .anyRequest().authenticated();
//        return http.build();
//    }




}