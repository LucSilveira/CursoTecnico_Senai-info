package br.senai.sp.info.pweb.biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.info.pweb.biblioteca.dao.jdbc.UsuarioDao;
import br.senai.sp.info.pweb.biblioteca.models.Usuario;
import br.senai.sp.info.pweb.biblioteca.utils.SessionUtils;

@Controller
public class UsuarioController {
	
	
	@Autowired
	private UsuarioDao usuarioDao;
	//criara sempre um user dao quando preciso
	@Autowired
	private SessionUtils sessionUtils;
	//criara sempre uma categoria sempre que precisar
	@GetMapping("/entrar")
	public String abreLogin() {
		return "usuario/login";
	}
	//caso esteja na url redireciona para login
	@GetMapping("/sair")
	public String fazerLogout(HttpSession session) {
		sessionUtils.removerUsuarioLogado();
		return "index";
	}
	//caso esteja na url redireciona para sair
	
	//Model - Envia dados para a view
	@PostMapping("/autenticar")
	public String autenticar(Usuario usuario, Model model) {
		
		//Lista de erros
		List<String> erros = new ArrayList<>(6);
		
		//Fazer a validação
		if(usuario.getEmail() == null) {
			erros.add("O campo e-mail é obrigatorio");
		}else if(usuario.getEmail().length() < 1 || usuario.getEmail().length() > 120) {
			erros.add("O campo e-mail deve estar entre 1 e 120 caracteres");
		}
		//verifica se a senha e o email sao ighuais aos dados salvos no banco
		if(usuario.getSenha() == null) {
			erros.add("O campo senha é obrigatório");
		}else if(usuario.getSenha().length() < 1 || usuario.getSenha().length() > 20) {
			erros.add("O campo senha deve estar enter 1 e 20 caracteres");
		}
		
		/* Caso ocorreu algum erro (se a lista de erros não estiver vazia
		 * envia os erros e reabre a página de login*/
		if(! erros.isEmpty()) {
			model.addAttribute("erros", erros);
			return "usuario/login";
		}
		
		usuario.hashearSenha();
		/*codifica a senha*/
		Usuario usuarioAutenticado = usuarioDao.autenticar(usuario);
				
				if(usuarioAutenticado == null) {
					System.out.println("Usuario invalido");
					return "usuario/login";
				}
				//se o usuario nao for autenticado sair da sessao
		sessionUtils.setUsuarioLogado(usuarioAutenticado);
		/*ira aplicar o usuario na sessao*/
		System.out.println(usuarioAutenticado);
		/*imprimi o usuario autenticado*/
		return "redirect:app/";
	}
	
	@RequestMapping("/usuario/novo")
	public String abreForm() {
		return "usuario/form";
	}
	//verifica se na url, e redireciona para o form
		@PostMapping("/usuario/salvar")
		public String salvar(Usuario usuario) {
		
		System.out.println(usuario);
		usuario.hashearSenha();
		
		System.out.println(usuario);
		usuarioDao.persistir(usuario);
		
		return "index";
		/*ve se na url, e entao salva os dados e 'imprimi' na sessao, mandadno ahsear a senha
		 * e mandar as info para a dao*/
	}

}
