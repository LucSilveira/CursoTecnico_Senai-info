package br.senai.sp.info.pweb.ianes.controllers;

import java.util.Date;

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
import br.senai.sp.info.pweb.ianes.dao.MovimentacaoDao;
import br.senai.sp.info.pweb.ianes.models.Item;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Controller
@RequestMapping("/app")
public class MovimentacaoController {
	
	/* Modelos dos Daos, que seram associados */
	@Autowired
	private MovimentacaoDao movimentacaoDao;
	
	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private AmbienteDao ambienteDao;
	
	
	@GetMapping({"/movimentacao"})
	public String abrirMenuMovimentacao(Model model, @RequestParam(required = true) Long id) {
	
		model.addAttribute("item", itemDao.buscar(id));
		model.addAttribute("movimentacoes", movimentacaoDao.buscarPeloItem(id));
		model.addAttribute("ambientes", ambienteDao.buscarTodos());
		model.addAttribute("movimentacao", new Movimentacao());
		
		return "movimentacao/form-lista";
		
	}
	

	@PostMapping("/movimentacao/salvar")
	public String salvar(@Valid Movimentacao movimentacao, Long idItem, BindingResult brMovimentacao, Model model, HttpSession session) {

		System.out.println("Lucas <3 : " + movimentacao);
		
		if (brMovimentacao.hasErrors()) {
			System.out.println(brMovimentacao);
			model.addAttribute("item", itemDao.buscar(idItem));
			return "movimentacao/form-lista";
		}

		if (brMovimentacao.hasErrors()) {
			System.out.println(brMovimentacao);
			model.addAttribute("ambientes", ambienteDao.buscarTodos());
			return "movimentacao/form-lista";
		}

		if (movimentacao.getId() == null) {

			// Realizando validações
			if (brMovimentacao.hasErrors()) {
				System.out.println(brMovimentacao);
				model.addAttribute("movimentacao", movimentacao);
				return "movimentacao/menu";
			}
		}

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		/*Item itemMovimentacao = itemDao.buscar(idItem);*/
		Movimentacao moviOrigem = movimentacaoDao.buscaUltimaMovimentacao(movimentacao.getIdentificacao().getIdentificacao());
		Item item = itemDao.buscar(movimentacao.getIdentificacao().getIdentificacao());
		
		if (movimentacao.getId() == null) {
			movimentacao.setData_movimentacao(new Date());
			movimentacao.setMovimentou(autenticado);
			if(moviOrigem == null) {
				//movimentacao.setOrigem(itemDao.buscar(movimentacao.getIdentificacao().getIdentificacao()).getLocalizacao());
				movimentacao.setOrigem(item.getLocalizacao());
				//
				movimentacao.getIdentificacao().setLocalizacao(movimentacao.getDestino());;
			}else {
				movimentacao.setOrigem(moviOrigem.getDestino());
			}
			movimentacaoDao.persistir(movimentacao);
			item.setLocalizacao(movimentacao.getOrigem());
			itemDao.alterar(item);
		}

		return "redirect:/app/movimentacao?id=" + movimentacao.getIdentificacao().getIdentificacao();
	}

}