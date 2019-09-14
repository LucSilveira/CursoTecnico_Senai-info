package br.senai.sp.info.pweb.jucacontrol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//abilita o modulo de seguranca web do spring
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//Injetando
	@Autowired
	private JwtFilter jwtFilter;
	
	//coracao do sistema
	//como funciona a seguranca da sua aplicacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//desablitar as sessoes (pois nao usa a sessao para efetuar o login)
		http
			.sessionManagement()
			//estrategia de autenticacao por sessao
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
					//libera os endpoints
					.authorizeRequests()
					//qual o endpoint esta "bloqueando" e qual precisa de token para acessar
					.antMatchers("/rest/auth/jwt").permitAll()
						//qualquer requisicao, precisa estar autenticado
						.anyRequest().authenticated()
				//conteudo extra
				.and()
					//ataque "pescaria - fishing"
					.csrf().disable()
					//quem pode acessar
					.cors();
		
		//adicionando os filtros
		/*usando para ver se o usuario vai ter acesso ou nao*/
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
					//filtro do Spring	//autentica user por email e senha automaticamente
	}

}
