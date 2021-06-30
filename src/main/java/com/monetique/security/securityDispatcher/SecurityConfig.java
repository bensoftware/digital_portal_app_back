package com.monetique.security.securityDispatcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.monetique.security.service.AppUserData;
import com.monetique.security.service.CustomAuthenticationProvider;
import com.monetique.service.LdapService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	AppUserData appUserData;
	@Autowired
	LdapService ldapService;
	

	// le type de l'algo de hackage
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		/*auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);*/
		auth.authenticationProvider(new CustomAuthenticationProvider(appUserData,ldapService));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// desactivé le CSRF
		http.csrf().disable();
		
		
		//http.httpBasic().disable();
		
		// desactiver le formulaire d'authentification
		//http.formLogin();
		// desactiver la session.
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// authorisation des URL sans authentification
		http.authorizeRequests().antMatchers("/login/**","/TELEPIN/**","/generationPdf/**","/**").permitAll();
		http.authorizeRequests().antMatchers("/getAllGroupe/**","/addGroupe/**","/updateGroupe/**").permitAll();
		
	//	http.authorizeRequests().anyRequest().permitAll();

		// toute les URL /tastks/** de type POST sont autorisé que par l'admin 
		//http.authorizeRequests().antMatchers("/getUser**").hasAuthority("AMB");
		
		// tout autre requete doit etre authentifier
		http.authorizeRequests().anyRequest().authenticated();
		
		// ajout des filters par ordres
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(),appUserData));
		http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	}
	

}
