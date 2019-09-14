package br.senai.sp.info.pweb.ianes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.senai.sp.info.pweb.ianes.dao.PatrimonioDao;
import br.senai.sp.info.pweb.ianes.models.Usuario;


@Controller
public class IndexController {

	@Autowired
	PatrimonioDao patrimonioDao;

	@GetMapping(value = { "/app" })
	public String abrirPrincipal(Model model) {
		model.addAttribute("patrimonios", patrimonioDao.buscarTodos());

		return "patrimonio/lista";
	}

	@GetMapping(value = { "/", "" })
	public String abrirLogin(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "index";
	}

	@GetMapping({ "/sair" })
	public String logout() {

		return "redirect:/";
	}

}
