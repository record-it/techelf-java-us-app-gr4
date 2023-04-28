package pl.us.spring.gr4app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()
                .realmName("Book App")
                .and()
                .csrf()
                .disable()
                .headers()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/books/**").authenticated()
                .requestMatchers("/api/v1/books/**").hasRole("ADMIN")
                // zabezpiecz ścieżkę /api/v1/books tak, aby każdy uzytkownik autoryzowany mógł wykonać metodę GET
                .requestMatchers(HttpMethod.POST, "/api/v1/public/books/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }


}
