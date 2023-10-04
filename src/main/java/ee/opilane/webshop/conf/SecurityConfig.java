package ee.opilane.webshop.conf;


import ee.opilane.webshop.auth.TokenParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.logging.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final TokenParser tokenParser;

    public SecurityConfig(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .cors().and().headers().xssProtection().disable().and()
                .authorizeHttpRequests()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET,"/product/**").permitAll()
                .antMatchers(HttpMethod.GET,"/carouselPicture/**").permitAll()
                .antMatchers(HttpMethod.GET,"/category/**").permitAll()
                .antMatchers("/getshops").permitAll()
                .antMatchers("/getshop/**").permitAll()
                .antMatchers("/parcelmachines/**").permitAll()

                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(tokenParser, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

}