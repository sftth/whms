package com.summit.whms.config.auth;

import com.summit.whms.main.domain.users.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/","/main/**","/css/**","/images/**","/js/**","h2-console/**","/sample/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/main")
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
