package br.senai.sp.info.pweb.somativa.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.senai.sp.info.pweb.somativa.dao.jdbc.UsuarioDao;
import br.senai.sp.info.pweb.somativa.models.Usuario;
import br.senai.sp.info.pweb.somativa.utils.SessionUtils;

@Controller
public class UsuarioController {
	
	
	private Date dataAtual = new Date();
	
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
		sessionUtils.removerUsuarioLogado();
		return "index";
	}
	
	@PostMapping("/autenticar")
	public String autenticar(Usuario usuario, Model model) {
		List<String> erros = new ArrayList<>(6);
		
		if(usuario.getEmail() == null) {
			erros.add("O campo e-mail é obrigatorio");
		}else if(usuario.getEmail().length() < 1 || usuario.getEmail().length() > 120) {
			erros.add("O campos e-mail deve estar entre 1 e 32 carateres");
		}
		if(usuario.getEmail().length() == 0){
			erros.add("O campo email é obrigatório!");
		}else if(usuarioDao.emailChecar(usuario.getEmail()) == false) {
			erros.add("Este email não está cadastrado. Por favor, insira outro!");
		}
		
		if(usuario.getSenha() == null){
			erros.add("O campo senha é obrigatorio");
		}else if(usuario.getSenha().length() < 1 || usuario.getSenha().length() > 20) {
			erros.add("O campo senha deve estar entre 8 e 20 caracteres");
		}
		
		if(usuario.getSenha() == null){
			erros.add("O campo senha é obrigatório!");
		}else if(usuario.getSenha().length() < 2 || usuario.getSenha().length() > 20) {
			erros.add("A senha não esta cadastrada");
		}
		
		if(! erros.isEmpty()) {
			model.addAttribute("erros", erros);
			return "usuario/login";
		}
		
		usuario.hashearSenha();
		Usuario usuarioAutenticado = usuarioDao.autenticar(usuario);
		
			if(usuarioAutenticado == null) {
				System.out.println("usuario invalido");
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
	
	@PostMapping("/usuario/salvar")
	public String salvar(Usuario usuario, Model model) {
		
		//Lista de erros
				List<String> errosCadastro = new ArrayList<>(100);
						
				//Fazer validações
				if(usuario.getNome() == null){
					errosCadastro.add("O campo nome é obrigatório!");
				}else if(usuario.getNome().length() < 2 || usuario.getNome().length() > 60){
					errosCadastro.add("O nome deve estar entre 2 e 60 caracteres!");
				}
						
				if(usuario.getEmail().length() == 0){
					errosCadastro.add("O campo email é obrigatório!");
				}else if(usuarioDao.emailChecar(usuario.getEmail()) == true) {
					errosCadastro.add("Este email já está cadastrado. Por favor, insira outro!");
				}
				
				if(usuario.getSenha() == null){
					errosCadastro.add("O campo senha é obrigatório!");
				}else if(usuario.getSenha().length() < 2 || usuario.getSenha().length() > 20) {
					errosCadastro.add("A senha deve estar entre 2 e 20 caracteres!");
				}
				
				if(usuario.getDataNascimento() == null) {
					errosCadastro.add("O campo data é obrigatório!");
				}else if(usuario.getDataNascimento().getTime() > dataAtual.getTime()) {
					errosCadastro.add("Insira uma data que seja antes da atual!");
				}
				
				if(usuario.getSexo() == null){
					errosCadastro.add("O campo sexo é obrigatório!");
				}
						
				/**
				 * Caso ocorreu algum erro (se a lista de erros não estiver vazia)
				 * Envia os erros e reabre a página de login
				 */
				if(! errosCadastro.isEmpty()) {
					model.addAttribute("errosCadastro", errosCadastro);
					return "usuario/form";
				}else {
					System.out.println(usuario);
					usuario.hashearSenha();
					System.out.println(usuario);
					usuarioDao.persistir(usuario);
				}
		return "index";
	}
}