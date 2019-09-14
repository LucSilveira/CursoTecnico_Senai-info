package br.senai.sp.info.pweb.somativa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.somativa.dao.jdbc.JogoDao;
import br.senai.sp.info.pweb.somativa.models.Jogo;
import br.senai.sp.info.pweb.somativa.utils.SessionUtils;

@Controller
@RequestMapping("/app/jogo")
public class JogoController {

	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@GetMapping("/deletar")
	public String deletar(@RequestParam(name="id", required = true) Long id) {
		Jogo c = new Jogo();
		c.setId(id);
		
		jogoDao.deletar(c);
		return "redirect:/app";
	}
	
	@RequestMapping("/jogo/novo")
	public String abreForm() {
		return "cadastro/form";
	}
	
	@PostMapping("/salvar")
	public String salvar(Jogo jogo) {
		List<String> errosCad = new ArrayList<>(100);
		
		if(jogo.getNome() == null){
			errosCad.add("O campo nome é obrigatório!");
		}else if(jogo.getNome().length() < 2 || jogo.getNome().length() > 60){
			errosCad.add("O nome deve estar entre 1 e 40 caracteres!");
		}
		if(jogo.getCategorias() == null) {
			errosCad.add("O campo e-mail é obrigatorio");
		}
		
		try {
			jogo.setUsuario(sessionUtils.getUsuarioLogado());
			
			if(jogo.getId() == null) {
				jogo.setDataCadastro(new Date());
				jogoDao.persistir(jogo);
		}else {
			jogoDao.alterar(jogo);
		}
			
	}catch(Exception e) {
		throw new RuntimeException(e);
	}
		return "redirect:/app";
	}
}