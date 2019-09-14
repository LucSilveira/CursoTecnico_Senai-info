package br.senai.sp.info.pweb.jucacontrol.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import br.senai.sp.info.pweb.jucacontrol.models.Usuario;
import br.senai.sp.info.pweb.jucacontrol.utils.JwtUtils;

/**
 * Filtro de liberacao de acesso do token JWT
 * @author 46923597811
 *
 */
@Component
public class JwtFilter extends GenericFilterBean{

	//expressao regular - 
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//converter e extrair os headers
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//extrair o header
		String authorization = req.getHeader("Authorization");
		
		//sempre o cliente ira enviar o header???????????
		
		//caso nao envie, façamos a tratativa de erro
		if(authorization != null) {
			//se o header nao estiver null
			
			//Verifica se o Authorization é do tipo Bearer <token>
			//fazendo a expressao regular
			if(authorization.matches("(Bearer )(\\w|\\.|\\-)+")) {
				
				//Bearer token -> extrair somente o token do header Authorization
				String token = authorization.split(" ")[1];		// o portador
				//este espaco separa um vetor, entao pega o Bearer no vetor [0]
				//o espaco no vetor vai cortar a frase e passar o token no vetor [1]
				System.out.println("Meu token " + token);
				try {
					JwtUtils.validarToken(token);
					
					Usuario usuarioDoToken = JwtUtils.obterUsuarioToken(token); 
					//Usuario do token, é o usuario que esta logado
					SecurityContextHolder.getContext().setAuthentication(usuarioDoToken);
				}catch(Exception e) {
					System.out.println("Token invalido seu animal");
				}
			}else {
				System.out.println("Authorization nao enviado");
			}
			
			//Devesse extrair somente o token do bearer do authorization
		}
		chain.doFilter(request, response);
	}
}