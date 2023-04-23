package com.android.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.android.api.entity.Role;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    UserService accountService;

    @Autowired
    MyBasicAuthenticationEntryPoint myBasicAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests().requestMatchers("/**").permitAll();

        // http.httpBasic(basic -> basic.authenticationEntryPoint(myBasicAuthenticationEntryPoint)).sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authorizeHttpRequests()
        // .requestMatchers("/account", "/", "").hasRole("ADMIN").and()
        //         .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // http.httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeHttpRequests().requestMatchers("/account", "/", "").hasRole("ADMIN").and()
        //         .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


         http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/auth/login/**").permitAll()
                                .requestMatchers("/auth/signup/**").permitAll()
                                .requestMatchers("/customer/**").permitAll()
                                .requestMatchers("/cart-item/**", "/cart-item/add-to-cart").permitAll()
                                .requestMatchers("/admin").hasRole(Role.ROLE_ADMIN.name())
                                .requestMatchers("/", "/search/**").permitAll()
                                .anyRequest().authenticated().and().addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)

                );

            return http.build();
    }
}
