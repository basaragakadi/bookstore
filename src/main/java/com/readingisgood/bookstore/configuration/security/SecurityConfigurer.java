package com.readingisgood.bookstore.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.readingisgood.bookstore.configuration.CustomUserDetailsService;

import lombok.AllArgsConstructor;

/**
 * @author basaragakadi
 * 
 * Class for security configuration.
 * To make endpoint(s) available for unauthenticated users configuration will be handled here.
 * 
 */
@SuppressWarnings("deprecation")
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

	private final CustomUserDetailsService userDetailsService;
	private final JwtRequestFilterConfigurer jwtRequestFilterConfigurer;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/authenticate").permitAll()
			.antMatchers("/user/register").permitAll()
			.antMatchers("/v2/api-docs",
                    "/configuration/ui",
                    "/swagger-resources/**",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**").permitAll()
			.anyRequest()
			.authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilterConfigurer, UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * Enabled h2-console for unauthenticated users
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**");
	}
	
	/*
	 * Warning suppressed because in this project there is no need for a password encoding
	 * that is because it is a study project
	 * TODO Use an encoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
