package org.greenwin.VLzuul.security;


import org.greenwin.VLzuul.jwt.JwtAuthenticationConfig;
import org.greenwin.VLzuul.jwt.JwtTokenAuthenticationFilter;
import org.greenwin.VLzuul.passphrase.PassphraseConfiguration;
import org.greenwin.VLzuul.passphrase.PassphraseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    @Autowired
    private JwtAuthenticationConfig config;

    @Autowired
    private PassphraseConfiguration configuration;

    @Bean
    public JwtAuthenticationConfig jwtConfig() {
        return new JwtAuthenticationConfig();
    }

    /**
     * orchestrs le passage de la requête
     * @param httpSecurity
     * @throws Exception
     */
    @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {

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
                    //filtre pour ajouer un mot de passe interne à se passer entre ms
                    //.addFilter(new PassphraseFilter(configuration))
                    .addFilterAfter(new JwtTokenAuthenticationFilter(config),
                            UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(config.getUrl()).permitAll()
                    .antMatchers("/backend/admin").hasRole("ADMIN")
                    .antMatchers("/backend/user").hasRole("USER")
                    .antMatchers("/backend/guest").permitAll()
                    .antMatchers("/ms-topics/**").authenticated();
                    //.antMatchers("/ms-campaign/**").authenticated();


        }


}

