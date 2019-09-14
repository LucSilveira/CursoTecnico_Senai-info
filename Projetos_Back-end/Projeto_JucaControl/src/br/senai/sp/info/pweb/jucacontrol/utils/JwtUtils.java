package br.senai.sp.info.pweb.jucacontrol.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

public class JwtUtils {
	
	public static final String TOKEN_AUTH_CHAVE_PRIVADA = "M4RV3L3M3LH0RQU347C";
	
	public static String gerarToken(Usuario usuario) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		
		return JWT.create()
			//headers
			.withIssuer("Senai").withIssuedAt(new Date()).withSubject("Authentication")
			
			//payload
			.withClaim("id", usuario.getId())
			.withClaim("nome", usuario.getNome())
			.withClaim("tipo", usuario.getTipo().toString())
			//signature
			.sign(Algorithm.HMAC512(TOKEN_AUTH_CHAVE_PRIVADA));
			
	}
	
	public static Usuario obterUsuarioToken(String token) {
		
		
		Usuario usuario = new Usuario();
		DecodedJWT decodedJwt = JWT.decode(token);		
		
		usuario.setId(decodedJwt.getClaim("id").asLong());
		usuario.setNome(decodedJwt.getClaim("nome").asString());
		usuario.setTipo(TiposUsuario.valueOf(decodedJwt.getClaim("tipo").asString()));
		
		//retorna o proprio usuario
		return usuario;
	}
	
	public static void validarToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		
		//validando o token
		JWT
			.require(Algorithm.HMAC512(TOKEN_AUTH_CHAVE_PRIVADA))
				.build()
				.verify(token);
		
	}
}