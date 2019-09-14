package br.senai.sp.info.pweb.biblioteca.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.senai.sp.info.pweb.biblioteca.models.Usuario;

@Component
public class SessionUtils {
	
	//Chave para identificar o usuario logado
	public static final String USUARIO_CHAVE = "usuarioLogado";
	
	@Autowired
	private HttpSession session;
	/**
	 * Armazena um obj usuario na sessão para realizar
	 * controle de acesso
	 */
	
	public Usuario getUsuarioLogado() {
		return (Usuario) session.getAttribute(USUARIO_CHAVE);
	}
	/**
	 * armazena um objeto usuario na sessao para realizar controle de acesso
	 * @param usuario
	 */
	
	public void setUsuarioLogado(Usuario usuario) {
		session.setAttribute(USUARIO_CHAVE, usuario);
	}
	
	/**
	 * Verifica se o usuario est na sessão 
	 * @return
	 */

	public boolean isUsuarioLogado() {
		return session.getAttribute(USUARIO_CHAVE) != null;
	}
	
	public void removerUsuarioLogado() {
		session.invalidate();
		/*Mata a sessao, remove o arquivo de sessao do cliente no servior
		 * fazendo-a invalidar*/
	}

}
