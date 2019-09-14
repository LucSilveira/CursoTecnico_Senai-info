package br.senai.sp.info.pweb.jucacontrol.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.jucacontrol.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO usuarioDao;
	
	public Usuario buscarPorEmailESenha(Usuario usuario, BindingResult bindResult) throws ValidacaoException, EntidadeNaoEncontradaException {
		
						//para um erro expecifico
		if(bindResult.hasFieldErrors("email") || bindResult.hasFieldErrors("senha")) {
			throw new ValidacaoException();
		}
		
		//buscando usuario
		usuario.hashearSenha();
		Usuario usuarioBuscado = usuarioDao.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		
		if(usuarioBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return usuarioBuscado;
	}

}
