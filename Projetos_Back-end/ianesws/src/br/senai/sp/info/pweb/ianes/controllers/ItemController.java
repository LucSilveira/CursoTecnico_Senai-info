package br.senai.sp.info.pweb.ianes.controllers;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.senai.sp.info.pweb.ianes.dao.AmbienteDao;
import br.senai.sp.info.pweb.ianes.dao.ItemDao;
import br.senai.sp.info.pweb.ianes.dao.PatrimonioDao;
import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.models.Item;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Controller
@RequestMapping("/app")
public class ItemController {

	@Autowired
	private ItemDao itemDao;

	@Autowired
	private AmbienteDao ambienteDao;

	@Autowired
	private PatrimonioDao patrimonioDao;

	@GetMapping({"/item"})
	public String abrirMenuItem(Model model, @RequestParam(required = true) Long id) {
		System.out.println("================================");
		model.addAttribute("patrimonio", patrimonioDao.buscar(id));
		System.out.println(id);
		
		model.addAttribute("itens", itemDao.buscarTodosPeloPatrimonio(id));
		System.out.println(itemDao.buscarTodosPeloPatrimonio(id));
		
		// Enviando os ambientes para a página
		model.addAttribute("ambientes", ambienteDao.buscarTodos());
		// Enviando o modelo pro modelAttribute
		model.addAttribute("item", new Item());
		return "item/form-list";
	}
	
	@GetMapping("/adm/item/deletar")
	public String deletar(@RequestParam( required = true) Long id) {
		Long idPatri = itemDao.buscar(id).getAssociado().getId();
		itemDao.deletar(itemDao.buscar(id));
		return "redirect:/app/item?id=" + idPatri;
	}
	
	@PostMapping("/item/salvar")
	public String salvar(@Valid Item item, Long idPatrimonio, BindingResult brItem, Model model, HttpSession session) {

		if (brItem.hasErrors()) {
			System.out.println(brItem);
			model.addAttribute("patrimonio", patrimonioDao.buscar(idPatrimonio));
			return "item/menu";
		}

		if (brItem.hasErrors()) {
			System.out.println(brItem);
			model.addAttribute("ambientes", ambienteDao.buscarTodos());
			return "item/form-list";
		}

		if (item.getIdentificacao() == null) {

			// Realizando validações
			if (brItem.hasErrors()) {
				System.out.println(brItem);
				model.addAttribute("item", item);
				return "item/form-list";
			}
		}

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		Patrimonio patrimonioItem = patrimonioDao.buscar(idPatrimonio);

		if (item.getIdentificacao() == null) {
			item.setCadastro(autenticado);
			item.setLocalizacao(item.getLocalizacao());
			itemDao.persistir(item);
		}

		return "redirect:/app/item?id=" + item.getAssociado().getId();
	}
}