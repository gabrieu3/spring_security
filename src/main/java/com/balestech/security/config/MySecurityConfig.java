package com.balestech.security.config;

import com.balestech.security.security.CustomAuthenticationProvider;
import com.balestech.security.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    protected void configure(HttpSecurity http) throws Exception {
       /* http.authorizeRequests()
                .antMatchers("/user/home").permitAll()
                .antMatchers("/user/dashboard").authenticated()
                .and()
                .httpBasic();*/
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/register", "/login").permitAll()
                //.antMatchers("/dashboard").hasRole("SUPERADMIN")//hasAuthority("READ")
                //.antMatchers("/home").hasRole("USER")//hasAuthority("READ")
                //.antMatchers("/profile").hasAnyRole("ADMIN")//hasAuthority("WRITE")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    /**
     * AUthenticationManager
     *
     * @param auth
     * @throws Exception
     */
    /*protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("gabrieu3").password("123").authorities("admin")
                .and()
                .withUser("malu").password("123").authorities("user")
                .and()
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }*/

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("gabrieu3").password("123").authorities("admin").build();
        UserDetails user2 = User.withUsername("malu").password("123").authorities("user").build();
        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);
        auth.userDetailsService(userDetailsService );
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}