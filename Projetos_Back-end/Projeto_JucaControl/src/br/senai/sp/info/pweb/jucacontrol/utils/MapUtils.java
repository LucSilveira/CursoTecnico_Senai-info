package br.senai.sp.info.pweb.jucacontrol.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class MapUtils {
	
	/**
	 * COnverte um {@link BindingResult} em um {@link Map}
	 * @return bindResult
	 */
	
	public static Map<String, String> mapaDe(BindingResult bindingResult){
		
		Map<String, String> mapaErros = new HashMap<>();
		
		//Pega os erros do bindResult e aplica-os no map
		for (FieldError erro : bindingResult.getFieldErrors()) {
			mapaErros.put(erro.getField(), erro.getDefaultMessage());
		}
		return mapaErros;
	}

}
