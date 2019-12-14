package kakaopay.security;

import kakaopay.security.filter.JwtAuthenticationFilter;
import kakaopay.security.filter.JwtRefreshFilter;
import kakaopay.security.provider.JwtAuthenticationProvider;
import kakaopay.security.provider.JwtRefreshProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final JwtRefreshProvider jwtRefreshProvider;

    public SecurityConfig(JwtAuthenticationProvider jwtAuthenticationProvider, JwtRefreshProvider jwtRefreshProvider) {
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
        this.jwtRefreshProvider = jwtRefreshProvider;
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationMatcher jwtAuthenticationMatcher = new JwtAuthenticationMatcher(Arrays.asList("/api/login", "/api/signup"));
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtAuthenticationMatcher);
        jwtAuthenticationFilter.setAuthenticationManager(super.authenticationManagerBean());

        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtRefreshFilter jwtRefreshFilter() throws Exception {
        JwtRefreshFilter jwtRefreshFilter = new JwtRefreshFilter("/api/refresh");
        jwtRefreshFilter.setAuthenticationManager(super.authenticationManagerBean());

        return jwtRefreshFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(jwtAuthenticationProvider)
                .authenticationProvider(jwtRefreshProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/api/signup", "/api/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtRefreshFilter(), JwtAuthenticationFilter.class);
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
