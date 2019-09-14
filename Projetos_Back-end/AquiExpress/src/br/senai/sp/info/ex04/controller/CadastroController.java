package br.senai.sp.info.ex04.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.ex04.dao.jdbc.ProdutoDao;
import br.senai.sp.info.ex04.models.Produto;
import br.senai.sp.info.ex04.models.Usuario;	

@Controller
//Ao colocar esta annotacion na classe, todo o mapping dela
//tera prefixo definido no value, que, no caso é '/app/cadastro'
@RequestMapping(value = "/app")
public class CadastroController {

	@Autowired
	private ProdutoDao produtoDao;
	
	/**
	 * @param model - Objeto que envia dados para a view
	 * @return
	 */
	@GetMapping(value= {"/",""})
	public String abrePaginaCadastros(Model model, HttpSession session) {
		//Busca o usuario logado da sessão
		Usuario logado = (Usuario)session.getAttribute("usuarioLogado");
		
		//Passa o usuario logado para buscar suas categorias
		model.addAttribute("produtos", produtoDao.buscarTodos(logado));
		return "cadastro/lista";
	}
	
	@GetMapping(value = "/cadastro")
	public String abrirForm(@RequestParam(name= "id", required = false) Long id, Model model) {
		//Se o id for informado, é uma alteração. Logo, buscaremos no banco de dados
		//para envia-lo para tela
		if(id != null) {
			Produto prod = produtoDao.buscar(id);
			model.addAttribute("produto", prod);
		}
		return "cadastro/form";
	}
}
