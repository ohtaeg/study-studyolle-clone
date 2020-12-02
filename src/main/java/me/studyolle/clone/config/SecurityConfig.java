package me.studyolle.clone.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // SpringSecurityFilterChain이 자동으로 포함, 시큐리티 설정을 직접하겠다
public class SecurityConfig extends WebSecurityConfigurerAdapter { // 설정을 좀 더 손쉽게

    @Override
    protected void configure(final HttpSecurity http) throws Exception { // 내가 원하는 요청들은 인증 체크를 하지 않도록 오버라이딩
        http.authorizeRequests()
            .mvcMatchers("/", "/login", "/sign-up", "/check-email", "/check-email-token"
            , "/email-login", "/check-email-login", "/login-link")
            .permitAll()
            .mvcMatchers(HttpMethod.GET, "/profile/*")
            .permitAll()
            .anyRequest()
            .authenticated()
        ;
    }

    @Override
    public void configure(final WebSecurity web) {
        // 정적 리소스들은 인증을 하지 않도록 설정
        web.ignoring()
           .requestMatchers(PathRequest.toStaticResources()
                                       .atCommonLocations())
        ;
    }
}
