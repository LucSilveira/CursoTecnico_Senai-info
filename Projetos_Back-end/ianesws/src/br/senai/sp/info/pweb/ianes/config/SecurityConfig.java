package br.senai.sp.info.pweb.ianes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Classe que configura a seguran�a de toda a aplica��o atrav�s do Jwt
 * 
 * @author Lucas Silveira Portal
 */
@Configuration
@EnableWebSecurity
// habilitar o m�dulo de seguran�a web do spring
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// Injetando o JwtFilter
	@Autowired
	private JwtFilter jwtFilter;

	/**
	 * M�todo que faz as configura��es expecificas do sistema
	 */
	@Override // como funciona a seguran�a da sua aplicacao
	protected void configure(HttpSecurity http) throws Exception {
		// desabilitar as sessoes - nao usa no ws
		http.sessionManagement()
				// estrategia de autenticacao por sessao
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// liberar os endpoints
				.authorizeRequests()
				// qual endpoint est� "bloqueado" e qual precisa de token para acessar
				// preciso especificar que apenas um endpoint est� liberado o acesso
				.antMatchers("/rest/auth/jwt").permitAll()
				// qualquer requisicao, precisa estar autenticado
				.antMatchers("/rest/**").authenticated().anyRequest().permitAll().and().csrf().disable().cors();

		// eu estou utilizando esse "cara - Oliveira, Felipe", como filtro de seguran�a
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		// filtro do spring security - autenticacao por usuario e senha automaticamente
	}

}
