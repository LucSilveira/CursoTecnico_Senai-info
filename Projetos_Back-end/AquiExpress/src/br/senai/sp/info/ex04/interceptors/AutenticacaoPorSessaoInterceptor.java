package br.senai.sp.info.ex04.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.senai.sp.info.ex04.utils.SessionUtils;


public class AutenticacaoPorSessaoInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	private SessionUtils sessionUtils;
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		
		boolean necessitaAutenticacao = req.getRequestURI().contains("/app");

		if (necessitaAutenticacao && ! sessionUtils.isUsuarioLogado()) {
			res.setStatus(403);
			return false;
		} else {
			return true;
		}
	}
	
}
