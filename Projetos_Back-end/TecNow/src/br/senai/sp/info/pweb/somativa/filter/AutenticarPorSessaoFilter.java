package br.senai.sp.info.pweb.somativa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.pweb.somativa.utils.SessionUtils;

@Component

public class AutenticarPorSessaoFilter extends HttpFilter{
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@Override
	public void destroy() {
		
	}
	
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws
	IOException, ServletException{
		
		System.out.println("Filtrou");
		
		boolean necessitaAutenticacao = req.getRequestURI().contains("/app");
		
		if(necessitaAutenticacao && ! sessionUtils.isUsuarioLogado()) {
			res.setStatus(403);
		}else {
			chain.doFilter(req, res);
		}
	}
}