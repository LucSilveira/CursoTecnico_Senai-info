package br.senai.sp.info.pweb.somativa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	/*para acessar o login acrescente /*/
	@RequestMapping("/")
	public String abrirIndex() {
		return "index";
	}

}
