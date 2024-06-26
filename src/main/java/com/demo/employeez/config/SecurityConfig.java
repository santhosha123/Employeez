package com.demo.employeez.config;

import com.demo.employeez.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@SuppressWarnings("removal")
public class SecurityConfig {
    private  JwtAuthenticationFilter jwtAuthFilter;
    private UserDetailsService userDetailsService;
    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.cors();
        http.authorizeHttpRequests(authorize ->
                {
                    try {
                        authorize
                                .requestMatchers("/login").permitAll()
                                .requestMatchers(HttpMethod.GET,"/employees","/employees/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/employees/leave/approve/**").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers(HttpMethod.POST,"/employees").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/employees/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/managers").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/employees").hasAnyRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.PUT,"/employees/edit").hasAnyRole("ADMIN","MANAGER")
                                .requestMatchers(HttpMethod.POST,"/employees/apply-leave").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                                .requestMatchers(HttpMethod.POST,"/employees/search","/employees/all").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                                .requestMatchers(HttpMethod.GET,"/dashboard","/department/**","/teams/**","/profileDto").hasAnyRole("EMPLOYEE","MANAGER","ADMIN")
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .authenticationProvider(authenticationProvider())
                                .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
                                .logout().disable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

        );
        return http.build();
    }
}
