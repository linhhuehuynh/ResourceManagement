package com.example.demoresourcemanagement.security;

import com.example.demoresourcemanagement.security.filters.JwtRequestFilter;
import com.example.demoresourcemanagement.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserCredentialService userCredentialService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userCredentialService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests().antMatchers("/","/register","/login").permitAll().
                anyRequest().authenticated()

//                .and().formLogin().loginPage("/login").defaultSuccessUrl("/welcome")
//                .permitAll()


//                .and().logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .and().formLogin().loginPage("/login")

                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

}

//.logout().permitAll()
//.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout", "POST")).and()
////redirect to the login page
//.formLogin().loginPage("/users/login").

//                .permitAll().and().logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
//                .and().formLogin().loginPage("/login").and().
//                exceptionHandling().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);