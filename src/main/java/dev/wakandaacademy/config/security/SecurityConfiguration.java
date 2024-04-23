package dev.wakandaacademy.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import dev.wakandaacademy.config.security.service.AutenticacaoSecurityService;
import dev.wakandaacademy.config.security.service.TokenService;
import dev.wakandaacademy.credencial.application.service.CredencialService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@SecurityScheme(
		  name = "Bearer Authentication",
		  type = SecuritySchemeType.HTTP,
		  bearerFormat = "JWT",
		  scheme = "bearer"
		)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final TokenService tokenService;
	private final CredencialService credencialService;
	private final AutenticacaoSecurityService autenticacaoSecurityService;

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoSecurityService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authority -> authority
	            .antMatchers("/public/**").permitAll()
	            .antMatchers("/v1/usuario/**").permitAll()
	            .antMatchers("/v1/usuario/public/cadastro").permitAll()
	            .antMatchers("/v1/conteudo/**").permitAll()
	            .antMatchers("/v1/postagem/**").permitAll()
//	            .antMatchers("/v1/comentario/**").permitAll()

				.anyRequest()
				.authenticated()
		)		
		.addFilterBefore(new FiltroToken(tokenService, credencialService),
			UsernamePasswordAuthenticationFilter.class);
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v3/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-ui/**",
				"/swagger-ui.html");
	}

}
