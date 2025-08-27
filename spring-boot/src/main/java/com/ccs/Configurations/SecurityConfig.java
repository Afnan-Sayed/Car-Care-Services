package com.ccs.Configurations;


import com.ccs.Services.UserAndPubAuthService.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/signup/**").permitAll()
                .requestMatchers("/auth/logout").authenticated()
                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                .requestMatchers("/provider/**").hasRole("PROVIDER")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/admins/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("CUSTOMER", "PROVIDER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/services").permitAll()
                .requestMatchers(HttpMethod.POST, "/services").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/services").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/services").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/admin/customers/{id}/enable").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/admin/customers/{id}/disable").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoderConfig().passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoderConfig passwordEncoderConfig() {
        return new PasswordEncoderConfig();
    }
}