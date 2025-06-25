// package com.wihanga.bookstore.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// @Configuration
// public class UserDetailsServiceConfig {
//     @Bean
//     public UserDetailsService userDetailsService() {
//         return new InMemoryUserDetailsManager(
//             User.withUsername("customer@example.com").password("{noop}password").roles("USER").build(),
//             User.withUsername("admin@example.com").password("{noop}adminpass").roles("ADMIN").build()
//         );
//     }
// }
