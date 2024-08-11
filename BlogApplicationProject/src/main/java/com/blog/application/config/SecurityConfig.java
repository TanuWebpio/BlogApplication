//package com.blog.application.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration{
//	
//	
//@SuppressWarnings("removal")
//protected void configure(HttpSecurity http) throws Exception{
//
//    http
//            .csrf(csrf -> csrf
//                    .disable())
//            .authorizeHttpRequests()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .httpBasic();
//}
//	

	
//}
//import static org.springframework.security.config.Customizer.withDefaults;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfiguration {
//
////	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//        http
//        .csrf(csrf -> csrf
//                .disable())
//        .authorizeHttpRequests(authorize -> authorize
//                .anyRequest().authenticated()
//                
//            )
//            .httpBasic(withDefaults());
//    }
//}
