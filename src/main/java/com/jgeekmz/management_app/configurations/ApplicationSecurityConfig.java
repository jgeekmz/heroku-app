package com.jgeekmz.management_app.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = { "com.jgeekmz.management_app" })
@EnableWebSecurity

@PropertySource("classpath:application.properties")
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(ApplicationSecurityConfig.class);

    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    DataSource dataSource;

    @Autowired
    public ApplicationSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return bCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        log.debug("Received a request >>> persistentTokenRepository");
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
       // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());

      /*  auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                //.authoritiesByUsernameQuery("select username from user where username=?")
                .passwordEncoder(bCryptPasswordEncoder());*/

        auth.authenticationProvider(authenticationProvider());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Spring Security 2021!");

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/registerPage", "/registerPageAdmin", "/login", "/confirm", "/confirmAdmin", "/resetPassword", "/reset_password", "/assets/**", "/css/**", "/js/**", "/fonts/**", "/img/**").permitAll()
                .antMatchers("/users/**", "/users", "/resources/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/activations").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/index", true)
                    .failureUrl("/login-error").permitAll()
                    .successHandler(myAuthenticationSuccessHandler)
                    .failureHandler(authenticationFailureHandler)
                    .permitAll()

                .and()
                .rememberMe().rememberMeParameter("remember-me").tokenRepository(persistentTokenRepository())

                .and()
                .logout()
                    .logoutSuccessHandler(myLogoutSuccessHandler)
                    .invalidateHttpSession(false)
                    .clearAuthentication(true)
                    .deleteCookies("JSESSIONID")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login");
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                /*httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"error\"}" + "Authentication Error");
                out.flush();
                out.close()*/;
            }
        };
    }

}