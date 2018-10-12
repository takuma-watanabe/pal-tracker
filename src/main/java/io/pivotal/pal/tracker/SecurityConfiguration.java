package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private Boolean httpsDisabled;

    public SecurityConfiguration(@Value("${https.disabled}") Boolean httpsDisabled) {
        this.httpsDisabled = httpsDisabled;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        if (!httpsDisabled) {
//            http.requiresChannel().anyRequest().requiresSecure();
//        }

        http
                .authorizeRequests().antMatchers("/**").hasRole("USER")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        System.out.println("httpsDisabled = " + httpsDisabled);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }
}
