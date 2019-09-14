package br.senai.sp.info.ex04.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.info.ex04.dao.jdbc.UsuarioDao;
import br.senai.sp.info.ex04.models.Usuario;
import br.senai.sp.info.ex04.utils.SessionUtils;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private SessionUtils sessionUtils;
	
	@GetMapping("/entrar")
	public String abreLogin() {
		return "usuario/login";
	}
	
	@GetMapping("/sair")
	public String fazerLogout(HttpSession session) {
		sessionUtils.UnsetUsuarioLogado();
		return "index";
	}
	
	@PostMapping("/autenticar")	
	public String autenticar(Usuario usuario, Model model) {
		
		//Lista de erros
		List<String> erros = new ArrayList<>(6);
		
		//Fazer validações
		if(usuario.getEmail() == null){
			erros.add("O campo email é obrigatório!");
		}else if(usuario.getEmail().length() < 1 || usuario.getEmail().length() > 120){
			erros.add("O campo e-mail deve estar entre 1 e 120 caracteres!");
		}
		
		if(usuario.getSenha() == null){
			erros.add("O campo senha é obrigatório!");
		}else if(usuario.getSenha().length() < 1 || usuario.getSenha().length() > 30){
			erros.add("O campo senha deve estar entre 1 e 30 caracteres!");
		}
		
		//Caso ocorreu algum erro (se a lista de erros não estiver vazia)
				//Envia os erros e reabre a página de login
		if(! erros.isEmpty()) {
			model.addAttribute("erros", erros);
			return "usuario/login";
		}
	
		usuario.hashearSenha();
		Usuario usuarioAutenticado = usuarioDao.autenticar(usuario);
		
		if(usuarioAutenticado == null) {
			System.out.println("Usuário inválido!");
			return "usuario/login";
		}
		
		
		sessionUtils.setUsuarioLogado(usuarioAutenticado);
		System.out.println(usuarioAutenticado);
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
