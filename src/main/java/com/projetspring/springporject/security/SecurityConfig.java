package com.projetspring.springporject.security;

import com.projetspring.springporject.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin(form -> form.successHandler((request, response, authentication) -> {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().println("success");
        }).failureHandler((request, response, exception) -> {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().println("error dff");
        }).permitAll());
        httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers("/admin/**").hasAuthority("ADMIN"));
        httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers("/user/**").hasAuthority("USER"));
        httpSecurity.authorizeHttpRequests(authorize -> authorize.requestMatchers("/photos/**").permitAll());
        httpSecurity.exceptionHandling(exception -> exception.accessDeniedPage("/errorPage"));
        httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()).authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated());
        httpSecurity.userDetailsService(userDetailsService);

        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // Allow all origins
        config.addAllowedMethod("*"); // Allow all HTTP methods
        config.addAllowedHeader("*"); // Allow all headers
        config.setAllowCredentials(true); // Allow credentials (e.g., cookies)
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
