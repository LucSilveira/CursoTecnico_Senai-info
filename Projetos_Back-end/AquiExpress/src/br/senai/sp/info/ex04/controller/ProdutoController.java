package br.senai.sp.info.ex04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.ex04.dao.jdbc.ProdutoDao;
import br.senai.sp.info.ex04.models.Produto;
import br.senai.sp.info.ex04.utils.SessionUtils;

@Controller
@RequestMapping("/app")
public class ProdutoController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	/**
	 * Deleta uma categoria do banco
	 * @return
	 */
	@GetMapping("/deletar")
	public String deletar(@RequestParam(name = "id", required = true)Long id) {
		
		try {
			//Cria um obj produto, passa o id e deleta
			Produto pd  = new Produto();
			pd.setId(id);
			
			produtoDao.deletar(pd);
			
			return "redirect:/app";
		}catch (Exception e) {
			
		}
		return null;
	}
	
	/**
	 * Salva  um produto mo banco de dados
	 * @param produto - obj a ser add no banco
	 * @return redireciona a pagina /app
	 */
	@PostMapping("/salvar")
	public String salvar(Produto produto) {
		
		try {
			if(produto.getId() == null) {
				produto.setUsuario(sessionUtils.pegarUsuario());
				
				produtoDao.persistir(produto);
			}else {
				produtoDao.alterar(produto);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
		return "redirect:/app";
	}

}
