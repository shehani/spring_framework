package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{


        http.
                csrf().ignoringRequestMatchers("/postIdea").ignoringRequestMatchers("/public/**").ignoringRequestMatchers("/updateProfile").ignoringRequestMatchers("/admin/**").and().authorizeHttpRequests().
                requestMatchers("/public/**").permitAll().
                requestMatchers("","/","/home").authenticated().
                requestMatchers("/IdeaList").hasRole("ADMIN").
                requestMatchers("/IdeaList/**").hasRole("ADMIN").
                requestMatchers("/CloseIdea/**").hasRole("ADMIN").
                requestMatchers("/profile").authenticated().
                requestMatchers("/updateProfile").authenticated().
                requestMatchers("/admin/displayChurch").authenticated().
                requestMatchers("/admin/saveChurch").authenticated().
                requestMatchers("/admin/displayUsers/**").authenticated().
                requestMatchers("/admin/removeChurch").authenticated().
                requestMatchers("/assets/**").permitAll().
                requestMatchers("/schedule/**").permitAll().
                requestMatchers("/idea").permitAll().
                requestMatchers("/postIdea").permitAll().
                requestMatchers("/login").permitAll().
                requestMatchers("/logout").permitAll().
                and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?error=true")
                .permitAll().and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll().and().httpBasic();

        return http.build();



    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
