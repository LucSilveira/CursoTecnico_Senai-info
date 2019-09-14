package br.senai.sp.info.pweb.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.biblioteca.dao.jdbc.CategoriaDao;
import br.senai.sp.info.pweb.biblioteca.models.Categoria;
import br.senai.sp.info.pweb.biblioteca.utils.SessionUtils;

@Controller
@RequestMapping("/app/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaDao categoriaDao;
	/*Criara sempre uma nova categoriaDao, sempre que precisara restarta*/
	
	@Autowired
	private SessionUtils sessionUtils;
	/*Criara sempre uma bova sessao sempre que preciso*/
	
	@GetMapping("/deletar")
	public String deletar(@RequestParam(name="id", required = true) Long id) {
		//Criar um objeto categoria, passar o id e deletar
		Categoria c = new Categoria();
		c.setId(id);
		
		categoriaDao.deletar(c);
		
		return "redirect:/app";
	}
	
	/**
	 * Salva uma categoria no banco de dados
	 * @param categoria
	 */
	@PostMapping("/salvar")
	public String salvar(Categoria categoria) {
		try {
			categoria.setUsuario(sessionUtils.getUsuarioLogado());
			if(categoria.getId() == null) {
				categoriaDao.persistir(categoria);
			}else {
				categoriaDao.alterar(categoria);
			}
		}catch(Exception e ) {
			throw new RuntimeException(e);
		}
		return "redirect:/app";
	}
	
	
}
