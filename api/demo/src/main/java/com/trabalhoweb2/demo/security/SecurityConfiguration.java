package com.trabalhoweb2.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.trabalhoweb2.demo.model.Usuario;
import com.trabalhoweb2.demo.repository.UsuarioRepository;

@Configuration
public class SecurityConfiguration {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("teste");
        http
                .csrf().disable()
                .cors().and()
                .authorizeRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/user/cadastrar").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().authenticationProvider(authProvider());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Usuario user = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException(email));
                return user;
            }
        });
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
