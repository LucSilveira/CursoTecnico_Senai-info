package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.ianes.dao.CategoriaDao;
import br.senai.sp.info.pweb.ianes.models.Categoria;

@Controller
@RequestMapping("/app/adm")
public class CategoriaController {
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	@GetMapping("/categoria")
	public String abrirListaCategorias(@RequestParam(name = "id", required = false) Long id,
										Model model) {
		
		model.addAttribute("categorias", categoriaDao.buscarTodos());
		return "categoria/lista";
	}
	
	@GetMapping("/categoria/editar")
	public String abrirEdicao(Model model, @RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response) throws IOException {
		
		model.addAttribute("categoria", categoriaDao.buscar(id));
		return "categoria/form";
	}

	@GetMapping("/categoria/deletar")
	public String deletar(@RequestParam(name = "id", required = true) Long id) {
		categoriaDao.deletar(categoriaDao.buscar(id));
		return "redirect:/app/adm/categoria";
	}
	
	
	@PostMapping("/categoria/salvar")
	public String salvar(@Valid Categoria categoria, BindingResult brCategoria,
						Model model) {	
		
		if(categoria.getId() == null) {
		
			if(categoriaDao.buscarPorNome(categoria.getNome()) != null) {
				brCategoria.addError(new FieldError("categoria", "nome", "O nome selecionado já está salvo."));
			}
			
			//Se houverem erros, reabre o form
			if(brCategoria.hasErrors()) {
				System.out.println("Ocorreu erros!");
				System.out.println(brCategoria);
				model.addAttribute("categoriaOcorrencia", categoria);
				return "categoria/form";
			}
		
		}else {
			if(brCategoria.hasFieldErrors("nome")) {
				model.addAttribute("categoria", categoria);
				model.addAttribute("categorias", categoriaDao.buscarTodos());
				return "categoria/form";
			}
		}
		
		if(categoria.getId() == null) {
			categoriaDao.persistir(categoria);
			return "redirect:/app/adm/categoria";
		}else {
			Categoria categoriaBanco = categoriaDao.buscar(categoria.getId());
			BeanUtils.copyProperties(categoria, categoriaBanco, "id");
			categoriaBanco.setNome(categoria.getNome());
			categoriaDao.alterar(categoriaBanco);
		}
		//Redireciona para pagina de categorias
		return "redirect:/app/adm/categoria";
	}
	
	@GetMapping("/categoria/nova")
	public String abrirFormNovaCategoria(Model model) {
		
		//Passando o objeto que será enviado para a tela
		model.addAttribute("categoria", new Categoria());
		
		return "categoria/form";
	}
}