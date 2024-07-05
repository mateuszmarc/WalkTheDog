package com.mateuszmarcyk.walk_the_dog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .cors()
//                .and()
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/register")
//                .permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("").hasAnyAuthority("USER", "ADMIN")
//                .and()
//                .formLogin("/users")
//                .and()
//                .build();

//    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                );
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .permitAll()
//                );

        return httpSecurity.build();
    }

}


