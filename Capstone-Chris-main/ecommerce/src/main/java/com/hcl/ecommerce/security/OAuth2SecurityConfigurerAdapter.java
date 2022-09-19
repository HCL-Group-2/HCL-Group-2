//package com.hcl.ecommerce.security;
//
//import com.hcl.ecommerce.controller.CustomAccessDeniedHandler;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//class OAuth2SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//    private final CustomAccessDeniedHandler customAccessDeniedHandler;
//
//    OAuth2SecurityConfigurerAdapter(CustomAccessDeniedHandler customAccessDeniedHandler) {
//        this.customAccessDeniedHandler = customAccessDeniedHandler;
//    }
//
//    protected void configure(final HttpSecurity http) throws Exception {  
//    	// Entry points
//        http.authorizeRequests()  
//            .antMatchers("/users/signin").permitAll()
//            .antMatchers("/users/signup").permitAll()
//            // Disallow everything else..
//            .anyRequest().authenticated()  
//            .and().oauth2Login();
//    }
//
////    protected void configure(final HttpSecurity http) throws Exception {  
////    	// Entry points
////        http.authorizeRequests()  
////            .antMatchers("/packages/**").permitAll()
////            .antMatchers("/tours/**").permitAll()
////            .antMatchers("/ratings/**").permitAll()
////            .antMatchers("/users/signin").permitAll() 
////            // Disallow everything else..
////            .anyRequest().authenticated()  
////            .and().oauth2Login();
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////            .exceptionHandling()
////                .accessDeniedHandler(customAccessDeniedHandler)
////            .and()
////                .authorizeRequests()
////                .antMatchers("/", "/login", "/images/**", "/favicon.ico")
////                .permitAll()
////                .anyRequest()
////                .authenticated()
////            .and()
////                .logout()
////                .logoutSuccessUrl("/");
////    }
//}