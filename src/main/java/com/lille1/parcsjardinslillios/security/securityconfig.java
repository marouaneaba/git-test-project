package com.lille1.parcsjardinslillios.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class securityconfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetails customUserDetails;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetails);
		// auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// http.formLogin();

		http.formLogin().loginPage("/login");
		http.authorizeRequests()
				.antMatchers("/", "/NouveauCommentaire", "/NouveauCommentaire/delete", "/NouveauCommentaire/confirmer",
						"/AjouterPJ", "/operationPJ", "/operationPJ/delete", "/operationPJ/update")
				.hasRole("ADMIN");

	}
}
