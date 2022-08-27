package com.reclebooks.recle.config;

import com.reclebooks.recle.jwt.JwtAccessDeniedHandler;
import com.reclebooks.recle.jwt.JwtAuthenticationEntryPoint;
import com.reclebooks.recle.jwt.JwtSecurityConfig;
import com.reclebooks.recle.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //h2 console 섲렁
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                //session  사용하지 않음 session 설정 stateless
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // 로그인 api, 회원가입 api는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitall
                .and()
                .authorizeRequests()
                .antMatchers("/api/user").permitAll()
                .antMatchers("/api/auth").permitAll()
                .antMatchers("/api/user/sales-list").permitAll()
                .antMatchers("/api/categorys").permitAll()
                .antMatchers("/api/categorys/{categoryId}").permitAll()
                .antMatchers("/api/posts").permitAll()
                .antMatchers("/api/post/{postId}/sales").permitAll()
                .antMatchers("/api/post").permitAll()
                .antMatchers("/api/post/{postId}").permitAll()
                .antMatchers("/api/wish-list").permitAll()
                .antMatchers("/api/wish-list/{postId}").permitAll()
                .antMatchers("/api/book").permitAll()
                .anyRequest().authenticated()

                //JwtFilter를 addFilterBefore로 등로했던 JwtSecurityConfig클래스로 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/*", "/favicon.ico", "/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**", "/swagger/**");

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}

