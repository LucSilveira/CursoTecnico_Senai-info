package br.senai.sp.info.ex04.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.ex04.models.Usuario;


@Component // Essa anotação faz com que o spring crie uma semente dessa classe mesmo sem declarar em nenhuma
public class SessionUtils {

	//Chave para identificar usuário logado
	public static final String USUARIO_CHAVE = "usuarioLogado";
	
	@Autowired
	private HttpSession session;
	
	/**
	 * Armazena um objeto usuário na sessão para realizar controle de acesso
	 * 
	 * usuario - objeto usuario a ser colocado na sessão
	 * session - sessão onde os dados serão colocados
	 */
	public void setUsuarioLogado(Usuario usuario) {
		
		session.setAttribute(USUARIO_CHAVE, usuario);
	}
	
	/**
	 * Veririfica se há um usuário na sessão
	 * 
	 * @return Retorna true(há usuário) ou false(não há usuário)
	 */
	public boolean isUsuarioLogado() {
		
		return session.getAttribute(USUARIO_CHAVE) != null;
		
	}
	
	/**
	 * Desfaz a sessão
	 */
	public void UnsetUsuarioLogado() {
		
		//Mata a sessão, removendo o arquivo da sessão do cliente no servidor, faznedo-a invalidar
		
		session.invalidate();
		
	}
	
	/**
	 * Retorna o objeto Usuário que está logado na sessão
	 * @return - usuarioLogado
	 */
	public Usuario pegarUsuario() {
		
		return (Usuario) session.getAttribute(USUARIO_CHAVE);
		
	}
	
	
}
