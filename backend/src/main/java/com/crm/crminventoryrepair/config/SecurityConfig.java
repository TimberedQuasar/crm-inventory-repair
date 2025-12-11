package com.crm.crminventoryrepair.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Konfiguracja zabezpieczeń dla aplikacji.
 *
 * W trybie deweloperskim wyłączamy CSRF i zezwalamy na wszystkie żądania
 * bez uwierzytelniania, aby ułatwić testowanie API.
 */
@Configuration
public class SecurityConfig {

    /**
     * Definiuje łańcuch filtrów bezpieczeństwa.
     * - csrf: wyłączone (API stateless)
     * - authorize: wszystkie żądania dozwolone (permitAll)
     * - httpBasic: pozostawione domyślnie (można wyłączyć lub skonfigurować w przyszłości)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )
            .httpBasic(basic -> {});
        return http.build();
    }
}
