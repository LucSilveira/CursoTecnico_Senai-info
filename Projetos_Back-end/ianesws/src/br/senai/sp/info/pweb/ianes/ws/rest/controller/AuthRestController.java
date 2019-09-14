package br.senai.sp.info.pweb.ianes.ws.rest.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.ianes.models.Usuario;
import br.senai.sp.info.pweb.ianes.services.UsuarioService;
import br.senai.sp.info.pweb.ianes.utils.JwtUtils;

/**
 * Método onde pega os dados do usuário logado
 * para que possa gerar e mapear o token
 * 
 * @author Lucas Silveira Portal
 *
 */
@RestController
@RequestMapping("/rest/auth")
public class AuthRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * Gera o token a partir dos dados do usuario logado
	 * 
	 * @param usuario - Busca o usuario da seção
	 * @param BindingResult - Trata os erros
	 * @return
	 */
	@PostMapping("/jwt") //inserindo o email e a senha no corpo do token
	public ResponseEntity<Object> gerarTokenJwt(@RequestBody @Valid Usuario usuario, BindingResult bind){
		
		//Buscando o usuario para gerar o token
		try {
			Usuario usuarioBuscado = usuarioService.buscarPorEmailESenha(usuario, bind);
			
			Map<String, String> tokenMap = new HashMap<>();
			tokenMap.put("token", JwtUtils.gerarToken(usuarioBuscado));
			return ResponseEntity.ok(tokenMap);
			
		}catch(ValidacaoException e) {
			return ResponseEntity.unprocessableEntity().build();
			
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
			
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
			
		}
	}
}