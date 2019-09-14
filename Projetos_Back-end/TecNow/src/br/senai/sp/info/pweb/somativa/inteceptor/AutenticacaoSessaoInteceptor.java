package br.senai.sp.info.pweb.somativa.inteceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.pweb.somativa.utils.SessionUtils;

public class AutenticacaoSessaoInteceptor  extends HandlerInterceptorAdapter{
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)throws Exception{
		
		boolean necessitaAutenticacao = req.getRequestURI().contains("/app");
		
		if(necessitaAutenticacao && ! sessionUtils.isUsuarioLogado()) {
			//Restringe o acesso ao controller
			res.setStatus(403);
			return false;
		}else {
			//Libera o acesso ao Controller
			return true;
		}
	}
}