package com.rcore.restapi.security.config;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.restapi.routes.BaseAuthRoutes;
import com.rcore.restapi.routes.BaseRoutes;
import com.rcore.restapi.security.authentication.RestAuthenticationEntryPoint;
import com.rcore.restapi.security.filter.TokenAuthenticationFilter;
import com.rcore.security.infrastructure.AuthTokenGenerator;
import com.rcore.security.infrastructure.jwt.converter.impl.JWTByAccessTokenGenerator;
import com.rcore.security.infrastructure.jwt.converter.impl.JWTByRefreshTokenGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Order(1)
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    //Эндпоинты БЕЗ аутентификации пользователя
                    .antMatchers(BaseAuthRoutes.AUTH + "/**", BaseRoutes.EXTERNAL_API + "/**").permitAll()
                .and()
                    .authorizeRequests()
                    //Эедпоинты C аутентификацией
                    .antMatchers(BaseRoutes.API + "/**").authenticated()
                .and()
                    .addFilterBefore(abstractAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .cors()
                .and()
                    .csrf().disable()
                    .formLogin().disable()
                    .httpBasic().disable()
                    .logout().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/v2/api-docs")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/**")
                .antMatchers("/public");
    }

    @Bean
    public AbstractAuthenticationProcessingFilter abstractAuthenticationProcessingFilter() {
        return new TokenAuthenticationFilter(authenticationManager, authenticationFailureHandler);
    }
}
