package com.hcl.ecommerce.security;

import com.hcl.ecommerce.controller.CustomAccessDeniedHandler;
import com.okta.spring.boot.oauth.Okta;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class OAuth2SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

	private final CustomAccessDeniedHandler customAccessDeniedHandler;

	OAuth2SecurityConfigurerAdapter(CustomAccessDeniedHandler customAccessDeniedHandler) {
		this.customAccessDeniedHandler = customAccessDeniedHandler;
	}

	protected void configure(final HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable();
//		http.cors().configurationSource(request -> new CorsConfiguration(corsConfiguratione()));
		// Entry points
		http.authorizeRequests().antMatchers("/login").permitAll()
				// Disallow everything else..
				.anyRequest().authenticated().and().oauth2Login();
		
	    // protect users endpoint 
//        http.authorizeRequests()
//                .antMatchers("/user/**", "/users/**" )
//                .authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
		
	     // add CORS filters
        http.cors();

        // force a non-empty response body for 401's to make the response more friendly
        Okta.configureResourceServer401ResponseBody(http);

        // disable CSRF since we
	}
}

//	@Bean
//	CorsConfiguration corsConfiguratione() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:7777"));
//		configuration.setAllowedMethods(Arrays.asList("GET", "POST","PUT", "OPTIONS", "DELETE"));
//
//		return configuration;
//	}

//    protected void configure(final HttpSecurity http) throws Exception {  
//    	// Entry points
//        http.authorizeRequests()  
//            .antMatchers("/packages/**").permitAll()
//            .antMatchers("/tours/**").permitAll()
//            .antMatchers("/ratings/**").permitAll()
//            .antMatchers("/users/signin").permitAll() 
//            // Disallow everything else..
//            .anyRequest().authenticated()  
//            .and().oauth2Login();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .exceptionHandling()
//                .accessDeniedHandler(customAccessDeniedHandler)
//            .and()
//                .authorizeRequests()
//                .antMatchers("/", "/login", "/images/**", "/favicon.ico")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//            .and()
//                .logout()
//                .logoutSuccessUrl("/");
//    }
//}