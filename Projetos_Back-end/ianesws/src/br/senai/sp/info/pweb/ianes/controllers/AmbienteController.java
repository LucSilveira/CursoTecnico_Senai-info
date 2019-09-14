package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;

import javax.mail.MessagingException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.info.pweb.ianes.dao.AmbienteDao;
import br.senai.sp.info.pweb.ianes.models.Ambiente;
import br.senai.sp.info.pweb.ianes.models.Tipo_usuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Controller
@RequestMapping(value = "/app")
public class AmbienteController {
	@Autowired
	private AmbienteDao ambienteDao;

	@GetMapping("/adm/ambiente/editar")
	public String abrirEdicao(Model model, @RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response, HttpSession session) throws IOException {

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");

		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "ambiente/lista";
		} else {
			model.addAttribute("ambiente", ambienteDao.buscar(id));
			return "ambiente/form";
		}
	}

	@GetMapping("/ambiente")
	public String abrirLista(Model model) {

		model.addAttribute("ambientes", ambienteDao.buscarTodos());
		return "ambiente/lista";
	}

	@GetMapping("/adm/ambiente/novo")
	public String abrirFormNovoAmbiente(Model model, HttpSession session) {

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");

		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "ambiente/lista";
		} else {
			// Passando o objeto que será enviado para a tela
			model.addAttribute("ambiente", new Ambiente());
			return "ambiente/form";
		}
	}

	@GetMapping("/adm/ambiente/deletar")
	public String deletar(@RequestParam(required = true) Long id, HttpServletResponse response, HttpSession session)
			throws IOException {

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");

		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "ambiente/lista";
		} else {
			ambienteDao.deletar(ambienteDao.buscar(id));
			return "redirect:/app/ambiente";
		}
	}

	@PostMapping(value = { "/adm/ambiente/salvar" })
	public String salvar(@Valid Ambiente ambiente, BindingResult brAmbiente, Model model, HttpSession session) {

		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");

		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "ambiente/lista";
		} else {
			// Verificação se é CADASTRO
			if (ambiente.getId() == null) {
				// Chegando se o ambiente já está sendo utilizado
				if (ambienteDao.buscarPorNome(ambiente.getNome()) != null) {
					brAmbiente
							.addError(new FieldError("ambiente", "nome", "O ambiente selecionado já está cadastrado."));

				}

				// Se houverem erros, reabre o form
				if (brAmbiente.hasErrors()) {
					System.out.println(brAmbiente);
					model.addAttribute("ambiente", ambiente);
					return "ambiente/form";
				}
			} else {
				// Validações de ALTERAÇÃO
				if (brAmbiente.hasFieldErrors("nome")) {
					model.addAttribute("ambiente", ambiente);
					model.addAttribute("ambientes", ambienteDao.buscarTodos());
					System.out.println(brAmbiente);
					return "ambiente/form";
				}
			}

			// CASO CADASTRO...
			if (ambiente.getId() == null) {
				// Salvando ambiente
				ambienteDao.persistir(ambiente);
				return "redirect:/app/ambiente";
			}

			// CASO ALTERAÇÃO...
			else {
				Ambiente ambienteBanco = ambienteDao.buscar(ambiente.getId());
				BeanUtils.copyProperties(ambiente, ambienteBanco, "id");
				ambienteDao.alterar(ambienteBanco);
			}
			return "redirect:/app/ambiente";
		}
	}
}