package br.senai.sp.info.pweb.jucacontrol.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Component
public class AutenticacaoInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean necessitaAutenticacao = request.getRequestURI().contains("/app");
		Usuario usuarioAutenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		boolean usuarioEstaAutenticado = usuarioAutenticado != null;
		boolean necessitaSerAdministrador = request.getRequestURI().contains("/adm");
		
		//CASO A P�GINA PRECISE DE AUTENTICA��O
		if(necessitaAutenticacao) {
			//CASO O USU�RIO ESTEJA LOGADO
			if(usuarioEstaAutenticado) {	
				//CASO O USU�RIO PRECISE SER ADMINSTRADOR
				if(necessitaSerAdministrador && usuarioAutenticado.getTipo() != TiposUsuario.ADMINISTRADOR) {
					response.sendError(403);
					return false;
				}
				
			}
			//Caso o usu�rio n�o esteja autenticado
			else {
				response.sendError(401);
				return false;
			}
		}
		
		return true;
	}
	
}
