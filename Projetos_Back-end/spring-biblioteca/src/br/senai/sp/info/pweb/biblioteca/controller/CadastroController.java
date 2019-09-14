package br.senai.sp.info.pweb.biblioteca.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.biblioteca.dao.jdbc.CategoriaDao;
import br.senai.sp.info.pweb.biblioteca.models.Categoria;
import br.senai.sp.info.pweb.biblioteca.models.Usuario;

@Controller
/*Ao colocar esta annotation na classe, todo mapping dela tera um
 * prefixo definido no value, que, no caso é '/app/cadastro'*/
@RequestMapping(value="/app")
//o request envia para app/cadastro
public class CadastroController {
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	/**
	 * @param model - objeto que envia dados para a view
	 * @return
	 */
	@GetMapping(value = {"","/"})
	public String abrePaginaCadastro(Model model, HttpSession session) {
		
		//Busca o usuario logado da sessao
		Usuario logado = (Usuario) session.getAttribute("usuarioLogado");
		//passo o usuario logado para buscar suas categorias
		model.addAttribute("categorias", categoriaDao.buscarTodos(logado));
		
		return "cadastro/lista";
	}
	
	/*mapear as urls*/
	@GetMapping(value = {"/cadastro"})
	public String abrirForm( @RequestParam(name = "id", required = false)Long id, Model model) {
		//Se o id for informado, é uma alteração. Logo, buscaremos no banco de dados
		//para envia-los para tela
		if(id != null) {
			Categoria cat = categoriaDao.buscar(id);
			model.addAttribute("categoria", cat);
		}
		
		return "cadastro/form";
	}
}