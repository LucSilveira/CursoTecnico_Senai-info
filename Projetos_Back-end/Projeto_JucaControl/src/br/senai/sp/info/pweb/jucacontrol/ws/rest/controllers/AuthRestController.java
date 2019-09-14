package br.senai.sp.info.pweb.jucacontrol.ws.rest.controllers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;

import br.senai.sp.info.pweb.jucacontrol.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.jucacontrol.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;
import br.senai.sp.info.pweb.jucacontrol.services.UsuarioService;
import br.senai.sp.info.pweb.jucacontrol.utils.JwtUtils;
import br.senai.sp.info.pweb.jucacontrol.utils.MapUtils;

@RestController
@RequestMapping("/rest/auth")
public class AuthRestController {

	@Autowired
	private UsuarioService usuarioService;
	
	//enviando email e senha no corpo do token
	@PostMapping("/jwt")
	public ResponseEntity<Object> gerarTokenJwt(@Valid @RequestBody Usuario usuario, BindingResult bindingResult)
			throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
		
		try {
			Usuario usuarioBuscado = usuarioService.buscarPorEmailESenha(usuario, bindingResult);
			Map<String, String> mapaToken = new HashMap<>();
			mapaToken.put("token", JwtUtils.gerarToken(usuarioBuscado));
			return ResponseEntity.ok(mapaToken);
			
		}catch (ValidacaoException e){
			
			return ResponseEntity.unprocessableEntity().body(MapUtils.mapaDe(bindingResult));
			
		}catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
}