package com.rcore.rest.api.spring.security;

import com.rcore.domain.security.port.AccessChecker;
import com.rcore.rest.api.commons.routes.BaseRoutes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EnableWebSecurity
@Configuration
public class WebSpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public WebSpringSecurityConfig(
            AuthenticationManager authenticationManager,
            AuthenticationFailureHandler authenticationFailureHandler,
            AccessChecker accessChecker,
            @Value("${spring.application.name}") String serviceName
    ) {
        this.tokenAuthenticationFilter = new TokenAuthenticationFilter(authenticationManager, authenticationFailureHandler, accessChecker, serviceName);
    }

    protected Collection<String> notSecureUrls() {
        return List.of(
                BaseRoutes.NOT_SECURE + "/**",
                "/actuator/**",

                "/v2/api-docs/**",
                "/v3/api-docs/**",
                "/swagger**",
                "/swagger/**",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/swagger-ui/index.html",
                "/swagger-ui/**",
                "/springwolf/**",
                "/configuration/**",
                "/webjars/**",
                "/public"
        );
    }

    protected RequestMatcher getSecuredUrlMatcher(){
        return new NegatedRequestMatcher(
                new OrRequestMatcher(notSecureUrls()
                        .stream()
                        .map(AntPathRequestMatcher::new)
                        .collect(Collectors.toList())));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RequestMatcher secureMatcher = getSecuredUrlMatcher();

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .requestMatcher(secureMatcher)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatcher(secureMatcher)
                .addFilterBefore(tokenAuthenticationFilter, BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new TokenAuthenticationEntryPoint());
    }

    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }

}
