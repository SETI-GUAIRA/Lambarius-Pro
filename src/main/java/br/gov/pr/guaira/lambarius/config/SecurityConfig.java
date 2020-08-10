package br.gov.pr.guaira.lambarius.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetails;
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/layout/**")
		.antMatchers("/images/**")
		.antMatchers("/svgs/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/tela-login/**")
		.antMatchers("/fragments/**");
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/pescadores/**").hasRole("ADMIN")
				.antMatchers("/usuarios/**").hasRole("ADMIN")
				.antMatchers("/ilhas/**").hasRole("ADMIN")
				.anyRequest().denyAll()//pra funcionar o denyAll deve retirar o authenticated
				//anyRequest().denyAll()
				.and()
				
			.formLogin()
				.loginPage("/login").defaultSuccessUrl("/pescadores/novo", true)
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.sessionManagement()
				.invalidSessionUrl("/login")
				.maximumSessions(1)
				.expiredUrl("/Logado");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
