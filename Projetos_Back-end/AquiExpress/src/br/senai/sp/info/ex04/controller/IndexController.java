package br.senai.sp.info.ex04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String abreIndex(){
		return "index";
	}
}
