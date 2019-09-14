package br.senai.sp.info.pweb.ianes.services;

import org.springframework.beans.factory.annotation.Autowired;

import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.ianes.models.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Classe que passa os paremetros para o controller
 * configurando as acões que serão realizadas
 * 
 * @author Lucas Silveira Portal
 */
@Service
public class UsuarioService {

	@Autowired
	private UsuarioDao usuarioDao;

	/**
	 * Método que busca o email e senha do usuario logado
	 * 
	 * @param usuario - o usuario logado
	 * @param bindResult - objeto que gera erros
	 * @return - o usuario logado
	 * @throws ValidacaoException - Caso haja erro
	 * @throws EntidadeNaoEncontradaException - Caso não encontre o objeto
	 */
	public Usuario buscarPorEmailESenha(Usuario usuario, BindingResult bindResult)
			throws ValidacaoException, EntidadeNaoEncontradaException {

		// para um erro expecifico
		if (bindResult.hasFieldErrors("email") || bindResult.hasFieldErrors("senha")) {
			throw new ValidacaoException();
		}
		
		if(usuario.getEmail() == null) {
			bindResult.addError(new FieldError("usuario", "email", "Deve informar o email!"));
			throw new ValidacaoException();
		}
		
		if(usuario.getSenha() == null) {
			bindResult.addError(new FieldError("usuario", "senha", "Deve informar a senha!"));
			throw new ValidacaoException();
		}

		// buscando usuario
		usuario.hashearSenha();
		Usuario usuarioBuscado = usuarioDao.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());

		//verificando se o usuairo foi achado
		if (usuarioBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}

		return usuarioBuscado;
	}

}
