package org.greenwin.VLzuul.security;


import org.greenwin.VLzuul.jwt.JwtAuthenticationConfig;
import org.greenwin.VLzuul.jwt.JwtTokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

        //@Autowired
        ////@Qualifier("main")
        //private UserDetailsService userDetailsService;
//
        //@Bean
        //public UserDetailsService userDetailsService() {
        //    return super.userDetailsService();
        //}

        //@Autowired
        //BCryptPasswordEncoder bCryptPasswordEncoder;
//
        //@Override
        //protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
        //    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        //}


    @Autowired
    private JwtAuthenticationConfig config;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }


    @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
            //http.csrf().disable();
            //http.formLogin();
            //http.authorizeRequests().antMatchers("/ms-users/**", "/js/**", "/images/**").permitAll();
            //http.authorizeRequests().antMatchers("/ms-topics/**").permitAll();
            //http.authorizeRequests().antMatchers().permitAll();
            ////http.authorizeRequests().antMatchers("/ms-order/**",  "/images/**").authenticated();
            //http.authorizeRequests().anyRequest().authenticated();
            //http.addFilter(new JwtTokenAuthenticationFilter(authenticationManager()));
            //http.addFilterBefore(new JwtTokenAuthenticationFilter(UsernamePasswordAuthenticationFilter.class));

            httpSecurity
                    .csrf().disable()
                    .logout().disable()
                    .formLogin().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .anonymous()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(
                    (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                    .and()
                    .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                            UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(config.getUrl()).permitAll()
                    .antMatchers("/backend/admin").hasRole("ADMIN")
                    .antMatchers("/backend/user").hasRole("USER")
                    .antMatchers("/backend/guest").permitAll();

        }


}

