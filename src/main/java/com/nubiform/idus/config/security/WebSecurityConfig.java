package com.nubiform.idus.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new IdusAuthenticationEntryPoint()) // 401 status
                .accessDeniedHandler(new IdusAccessDeniedHandler()) // 403 status
                .and()
                .authorizeRequests()
                .antMatchers("/api/**/auth/**").permitAll()
                .antMatchers("/api/**/member/members").hasRole("ADMIN")
                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .antMatchers("/swagger-ui/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }
}
