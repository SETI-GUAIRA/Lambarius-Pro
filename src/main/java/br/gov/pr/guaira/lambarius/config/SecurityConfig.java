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
				.antMatchers("/pescadores/**").hasRole("CAD_PESCADOR")
				.antMatchers("/produtores/**").hasRole("CAD_PRODUTOR")
				.antMatchers("/portos/**").hasRole("CAD_PORTO")
				.antMatchers("/relatorios/**").hasRole("CAD_PORTO")
				.antMatchers("/associacoes/**").hasRole("CAD_ASSOCIACAO")
				.antMatchers("/ilhas/**").hasRole("CAD_ILHA")
				.antMatchers("/usuarios/**").hasRole("CAD_USUARIO")
				.antMatchers("/tipoProdutos/**").hasRole("CAD_TIPO_PRODUTO")
				.antMatchers("/principal/**").hasRole("CAD_TIPO_PRODUTO")
				.antMatchers("/produtos/**").hasRole("CAD_TIPO_PRODUTO")
				.anyRequest().denyAll()//pra funcionar o denyAll deve retirar o authenticated
				//anyRequest().denyAll()
				.and()
				
			.formLogin()
				.loginPage("/login").defaultSuccessUrl("/principal", true)
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
