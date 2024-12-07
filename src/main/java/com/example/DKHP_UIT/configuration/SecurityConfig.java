package com.example.DKHP_UIT.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // @Autowired
        // private UtilsHandleJwtToken utilsHandleJwtToken;

        private final String[] GetPublicEnpoints = {

        };

        private final String[] PostPublicEnpoints = {
                        "/test/test",
                        "/user/SignUp",
                        "/user/Complete",
                        "/user/SendCodeUpdatePassword",
                        "/user/UpdatePassword",
                        "/user/Login",
                        "/staff/createStudentAccount",
                        "/subject/createSubject",
                        "/student/createStudent",
                        "/student/editStudent"
        };

        private final String[] PostAdmin = {
                        "/test/testAuthorization"
        };

        private final String[] postStaff = {

        };

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

                httpSecurity.authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.GET).permitAll()
                                .requestMatchers("/static/**").permitAll() // Allow access to static resources
                                .requestMatchers(HttpMethod.POST).permitAll()
                                .anyRequest().authenticated());
                httpSecurity.cors((cors) -> cors
                                .configurationSource(apiConfigurationSource()));

                // Disable CSRF protection
                httpSecurity.csrf(csrf -> csrf.disable());
                httpSecurity
                                .headers()
                                .contentSecurityPolicy(
                                                "script-src 'self' *; object-src *; report-uri /csp-report-endpoint/");

                return httpSecurity.build();
        }

        @Bean
        public CorsConfigurationSource apiConfigurationSource() {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOriginPatterns(
                                Arrays.asList("https://www.microsoft.com/edge", "https://coccoc.com",
                                                "https://www.google.com")); // Thêm
                // các
                // origin
                // cần
                // thiết
                configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("*")); // Nếu cần
                configuration.setAllowCredentials(true); // Nếu bạn cần hỗ trợ cookie
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                return source;
        }
}