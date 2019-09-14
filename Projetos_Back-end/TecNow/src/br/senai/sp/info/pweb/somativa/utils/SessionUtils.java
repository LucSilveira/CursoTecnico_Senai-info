package br.senai.sp.info.pweb.somativa.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.pweb.somativa.models.Usuario;

@Component
public class SessionUtils {
	
	//Chave para ifdentificar o usuario logado
	public static final String USUARIO_CHAVE = "usuarioLogado";
	
	@Autowired
	private HttpSession session;
	/*Armazena um obj usuario na sessao para realizar controle de acesso*/
	
	public Usuario getUsuarioLogado() {
		return(Usuario)session.getAttribute(USUARIO_CHAVE);
	}
	
	/**Armazena um obj usuario na sessao para realizar controle de acesso
	 * @param usuario*/
	public void setUsuarioLogado(Usuario usuario) {
		session.setAttribute(USUARIO_CHAVE, usuario);
	}
	
	/**Verifica se o usuario esta na sessao
	 * @return*/
	public boolean isUsuarioLogado() {
		return session.getAttribute(USUARIO_CHAVE) != null;
	}
	
	public void removerUsuarioLogado() {
		session.invalidate();
		/*Mata a sessao, remove o arquivo de acesso do cliente no servidor
		 * fazendo-o invalidar*/
	}
}