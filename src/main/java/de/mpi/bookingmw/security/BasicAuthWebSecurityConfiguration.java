package de.mpi.bookingmw.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class BasicAuthWebSecurityConfiguration {

    private final Environment env;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable();
        http
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll())
                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET, "/events").permitAll())
                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET, "/events/*").permitAll())
                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.POST, "/bookings").permitAll())
                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.GET, "/bookings/*").permitAll())
                .authorizeHttpRequests((auth) -> auth.requestMatchers(HttpMethod.DELETE, "/bookings/*").permitAll())
                .authorizeHttpRequests((auth) -> auth.anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        String username = env.getProperty("spring.defaultUser.username");
        String password = env.getProperty("spring.defaultUser.password");
        String role = env.getProperty("spring.defaultUser.role");
        UserDetails user = User
                .withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles(role)
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}

