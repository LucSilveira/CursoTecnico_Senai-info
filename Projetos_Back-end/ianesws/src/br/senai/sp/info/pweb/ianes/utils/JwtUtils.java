package br.senai.sp.info.pweb.ianes.utils;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import br.senai.sp.info.pweb.ianes.models.Tipo_usuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;

/**
 * Classe que gera o token a partir do usuario
 * 
 * @author Lucas Silveira Portal
 */
public class JwtUtils {
	
	//CHAVE SECRETA
	public static final String TOKEN_AUTH_CHAVE_PRIVADA = "I4N35";
	
	/**
	 * Método que pega os dados do usuario autenticado e gera o token
	 * o configurando a aplicando a sua segurança
	 * 
	 * @param usuario - usuario logado
	 * @return - token gerado
	 * @throws IllegalArgumentException
	 * @throws JWTCreationException
	 * @throws UnsupportedEncodingException
	 */
	public static String gerarToken(Usuario usuario) throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
		//Gerando o token
		/*obs:  nao passar a senha*/
		
		return JWT.create()
				
				//Header do token
				.withIssuer("Senai")
				.withIssuedAt(new Date()) //Date que gerou
				.withSubject("Authentication")
		
				//Payload do token
				.withClaim("id", usuario.getId())
				.withClaim("nome", usuario.getNome())
				.withClaim("tipo", usuario.getTipo().toString())
				
				//signature do token
				.sign(Algorithm.HMAC512(TOKEN_AUTH_CHAVE_PRIVADA));
	}
	
	/**
	 * Método que pega os dados do usuario
	 * que estão injetados no token dele
	 * 
	 * @param token - token gerado do usuario
	 * @return - retorna o usuario do token
	 */
	public static Usuario obterUsuarioToken(String token) {
		/*Pegando o token e o decodificando*/
		
		Usuario usuario = new Usuario();
		DecodedJWT decoded = JWT.decode(token);
		
		//pegando os dados do payload e passa ao decoded
		usuario.setId(decoded.getClaim("id").asLong());
		usuario.setNome(decoded.getClaim("nome").asString());
		usuario.setTipo(Tipo_usuario.valueOf(decoded.getClaim("tipo").asString()));
		
		//retornando este mesmo usuario
		return usuario;
	}
	
	/**
	 * Método que faz a verificacao do token do usuario
	 * 
	 * @param token - token geraso do usuario
	 * @throws JWTVerificationException
	 * @throws IllegalArgumentException
	 * @throws UnsupportedEncodingException
	 */
	public static void validarToken(String token) throws JWTVerificationException, IllegalArgumentException, UnsupportedEncodingException {
		
		//validando o token
		JWT
			.require(Algorithm.HMAC512(TOKEN_AUTH_CHAVE_PRIVADA))
			.build()
			.verify(token);
	}

}
