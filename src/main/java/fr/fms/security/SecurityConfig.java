package fr.fms.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/** Security configuration
 * @author Claire
 * */
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * data source = data base
     * */
    @Autowired
    DataSource dataSource;

    /** configuration
     * @author Claire
     * @param auth spring auth service
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username as principal,  password as credentials, active from user where username=?")
                .authoritiesByUsernameQuery("select users_username as principal, roles_name as role from user_roles where users_username=?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder());

    }
    /** password encoder
     * @author Claire
     * */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** configuration
     * @author Claire
     * @param http spring http service
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .and()
                .authorizeRequests()
                .antMatchers( "/index").hasRole("ADMIN")
                .antMatchers( "/index").hasRole("USER")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403")
                .and()
                .logout()
                .logoutUrl("/login")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();
    }

}



/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.activation.DataSource;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity


public class SecurityConfig {


    @Autowired
    DataSource dataSource;

    @Bean
    protected InMemoryUserDetailsManager configureAuthentication() {

        List<UserDetails> userDetails = new ArrayList<>();

        List<GrantedAuthority> adminRoles = new ArrayList<>();
        adminRoles.add(new SimpleGrantedAuthority("ADMIN"));
        userDetails.add(new User("admin", "$2a$12$5pD.3pkXkYkSdKia1kgMjOVXqMr3pIVSqHUEj16XPce.9eoKBaEP2", adminRoles));

        List<GrantedAuthority> userRoles = new ArrayList<>();
        userRoles.add(new SimpleGrantedAuthority("USER"));
        userDetails.add(new User("user", "$2a$12$43Zqz24bViJw4YE2V0oOBu3bj9B7eu2NAEDGVsuVn/dGrY9olGw3y", userRoles));

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login");
        http.authorizeRequests().antMatchers("/index").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/index").hasAnyAuthority("USER" , "ADMIN");

        http.exceptionHandling().accessDeniedPage("/403");
        return http.build();

    }

}*/
