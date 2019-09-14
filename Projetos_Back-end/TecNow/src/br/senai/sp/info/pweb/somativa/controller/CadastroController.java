package br.senai.sp.info.pweb.somativa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.somativa.dao.jdbc.JogoDao;
import br.senai.sp.info.pweb.somativa.models.Jogo;
import br.senai.sp.info.pweb.somativa.models.Usuario;

@Controller
@RequestMapping(value="/app")
public class CadastroController {

	@Autowired
	private JogoDao jogoDao;
	
	@GetMapping(value = {"", "/"})
	public String abrePaginaCadastro(Model model, HttpSession session) {
		Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
		
		model.addAttribute("jogos", jogoDao.buscarTodos(logado));
		
		return "cadastro/lista";
	}
	@GetMapping(value = {"/cadastro"})
	public String abrirForm( @RequestParam(name = "id", required = false)Long id, Model model) {
		
		if(id != null) {
			Jogo jg = jogoDao.buscar(id);
			model.addAttribute("jogo", jg);
		}
		return "cadastro/form";
		
	}
}
