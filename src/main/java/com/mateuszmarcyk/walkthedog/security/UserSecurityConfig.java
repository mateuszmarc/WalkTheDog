package com.mateuszmarcyk.walkthedog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(withDefaults())
                .csrf(configurer -> configurer.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(
                                "/register/**",
                                "/login/**",
                                "/about/**",
                                "/contact/**",
                                "/main/**",
                                "/css/**",
                                "/images/**"
                        ).permitAll()
                        .requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users/dashboard", true)
                        .permitAll());
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login")
//                        .permitAll()
//                )
//                .sessionManagement(session -> session
//                        .invalidSessionUrl("/login")
//                );

        return httpSecurity.build();
    }

}
