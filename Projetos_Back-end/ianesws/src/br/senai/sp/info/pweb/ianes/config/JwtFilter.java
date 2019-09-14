package br.senai.sp.info.pweb.ianes.config;

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

import br.senai.sp.info.pweb.ianes.models.Usuario;
import br.senai.sp.info.pweb.ianes.utils.JwtUtils;

/**
 * Método que faz a verificacao do token
 * buscado o usuairo setado nele
 * 
 * @author Lucas Silveira Portal
 */
@Component
public class JwtFilter extends GenericFilterBean {

	/**
	 * Método onde executa os processos de validacao do token
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		//Convertendo para HttpServletRequest
		HttpServletRequest httpReq = (HttpServletRequest) req;
		HttpServletResponse httpRes = (HttpServletResponse) res;
		
		//Pegando o header do token
		String header = httpReq.getHeader("Authorization");
		
		//Verificando se o Authorization foi enviado
		if(header != null) {			
			//O Authorization esta no formato Bearer <token>
			if(header.matches("(Bearer )(\\w|\\.|\\-)+")) {
				
				//Extrair apenas o token do header
				String token = header.split(" ")[1];
				System.out.println("Meu token " + token);
				
				try {
					JwtUtils.validarToken(token);
					//Pegando o usuário do token
					Usuario usuarioDoToken = JwtUtils.obterUsuarioToken(token);
					SecurityContextHolder.getContext().setAuthentication(usuarioDoToken);
				
				} catch (Exception e) {
					System.out.println("Passou um token inválido");
				}
			}else {
				System.out.println("Token não está no formato BEARER");
			}
		}else {
			System.out.println("Authorization não enviado");
		}
		chain.doFilter(req, res);
	}
}