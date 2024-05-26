package com.turkcell.customerservice.core.configuration;


import com.turkcell.core.security.BaseSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final BaseSecurityService baseSecurityService;

    private static final String[] WHITE_LIST = {
            "/api/v1/auth/user/customer/**",
            "/api/v1/auth/token/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/customers/**",
            "/api/v1/accounts/**",
            "/api/v1/contacts/**",
            "/api/v1/addresses/**",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        baseSecurityService.configureCommonSecurityRules(http);
        http
                .authorizeHttpRequests((req)->
                        req
                                .requestMatchers(WHITE_LIST).permitAll()
                                .requestMatchers("/api/v1/auth/user/admin/**").hasAnyAuthority("admin")
                                .anyRequest().authenticated()
                );

        return http.build();
    }
}
