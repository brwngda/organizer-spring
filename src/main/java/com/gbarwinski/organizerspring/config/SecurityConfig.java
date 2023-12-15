package com.gbarwinski.organizerspring.config;


import com.gbarwinski.organizerspring.service.LoggingUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.gbarwinski.organizerspring.utility.UrlPaths.*;

@Configuration
@ComponentScan("com.gbarwinski.organizerspring")
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LoggingUserService loggingUserService;
    private final EncoderConfig encoderConfig;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(encoderConfig.authProvider());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        auth -> auth.requestMatchers("/resources/**", "/webjars/**", "/img/**", "/", CSS_ALLOW_ALL, JS_ALLOW_ALL, REGISTER, LOGIN_ALLOW_ALL, LOGIN_ERROR, LOGOUT, HOME, TASK_INFORMATION).permitAll()
                                .anyRequest().authenticated())
                .formLogin(login -> login.loginPage(LOGIN)
                        .loginProcessingUrl(LOGIN)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl(PROJECTS, true)
                        .failureUrl(LOGIN_ERROR)
                        .successHandler(successHandler())
                        .permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT))
                        .addLogoutHandler(logoutHandler())
                        .logoutSuccessUrl(LOGOUT_SUCCESS)
                        .permitAll())
                .sessionManagement(s -> s.maximumSessions(1));

        return http.build();
    }

    @Bean
    AuthenticationSuccessHandler successHandler() {
        return new CustomAuthSuccessHandler(loggingUserService);
    }

    @Bean
    LogoutHandler logoutHandler() {
        return new CustomLogoutHandler();
    }

    @Bean
    public SessionManagementFilter sessionManagementFilter() {
        SessionManagementFilter sessionManagementFilter = new SessionManagementFilter(httpSessionSecurityContextRepository());
        sessionManagementFilter.setInvalidSessionStrategy(simpleRedirectInvalidSessionStrategy());
        return sessionManagementFilter;
    }

    @Bean
    public SimpleRedirectInvalidSessionStrategy simpleRedirectInvalidSessionStrategy() {
        return new SimpleRedirectInvalidSessionStrategy("/expired");
    }

    @Bean
    public HttpSessionSecurityContextRepository httpSessionSecurityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }
}