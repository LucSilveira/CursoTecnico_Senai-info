package br.senai.sp.info.ex04.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.senai.sp.info.ex04.models.Usuario;


@Component // Essa anota��o faz com que o spring crie uma semente dessa classe mesmo sem declarar em nenhuma
public class SessionUtils {

	//Chave para identificar usu�rio logado
	public static final String USUARIO_CHAVE = "usuarioLogado";
	
	@Autowired
	private HttpSession session;
	
	/**
	 * Armazena um objeto usu�rio na sess�o para realizar controle de acesso
	 * 
	 * usuario - objeto usuario a ser colocado na sess�o
	 * session - sess�o onde os dados ser�o colocados
	 */
	public void setUsuarioLogado(Usuario usuario) {
		
		session.setAttribute(USUARIO_CHAVE, usuario);
	}
	
	/**
	 * Veririfica se h� um usu�rio na sess�o
	 * 
	 * @return Retorna true(h� usu�rio) ou false(n�o h� usu�rio)
	 */
	public boolean isUsuarioLogado() {
		
		return session.getAttribute(USUARIO_CHAVE) != null;
		
	}
	
	/**
	 * Desfaz a sess�o
	 */
	public void UnsetUsuarioLogado() {
		
		//Mata a sess�o, removendo o arquivo da sess�o do cliente no servidor, faznedo-a invalidar
		
		session.invalidate();
		
	}
	
	/**
	 * Retorna o objeto Usu�rio que est� logado na sess�o
	 * @return - usuarioLogado
	 */
	public Usuario pegarUsuario() {
		
		return (Usuario) session.getAttribute(USUARIO_CHAVE);
		
	}
	
	
}
