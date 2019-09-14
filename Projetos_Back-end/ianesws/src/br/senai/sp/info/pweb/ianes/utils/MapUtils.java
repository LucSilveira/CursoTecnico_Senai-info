package br.senai.sp.info.pweb.ianes.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Método que mapeia o BindResult
 * 
 * @author Lucas Silveira Portal
 */
public class MapUtils {

	/**
	 * Converte um {@link BindingResult} em um {@link Map}
	 * @param bindingResult
	 * @return
	 */
	public static Map<String, String> mapaDe(BindingResult bindingResult) {

		Map<String, String> mapaErros = new HashMap<>();

		// Pega erro do binding result e aplica-o no map
		for (FieldError erro : bindingResult.getFieldErrors()) {
			mapaErros.put(erro.getField(), erro.getDefaultMessage());
		}

		return mapaErros;
	}
}