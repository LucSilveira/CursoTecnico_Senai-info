package br.senai.sp.info.pweb.jucacontrol.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;
import br.senai.sp.info.pweb.jucacontrol.utils.EmailUtils;
import br.senai.sp.info.pweb.jucacontrol.utils.ProjetoStorage;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ProjetoStorage storage;
	
	@GetMapping(value = {"/", ""})
	public String abrirLogin(Model model) {
				
		return "index";
	}
	
	@GetMapping("/app/adm/usuario/editar")
	public String abrirEdicao(Model model, @RequestParam(name = "id", required = true) Long id, 
			HttpServletResponse response) throws IOException {
		
		model.addAttribute("usuario", usuarioDAO.buscar(id));
		
		return "usuario/form";
	}
	
	@GetMapping("/app/adm/usuario")
	public String abrirLista(Model model) {
		model.addAttribute("usuarios", usuarioDAO.buscarTodos());
		return "usuario/lista";
	}
	
	@GetMapping("/app/adm/usuario/novo")
	public String abrirFormNovoUsuario(Model model) {
		
		//Passando o objeto que ser� enviado pra tela
		model.addAttribute("usuario", new Usuario());
		
		return "usuario/form";
	}
	
	@GetMapping("/app/perfil")
	public String abrirFormEditarUsuarioLogado(Model model) {
		
		return "usuario/form";
	}
	
	@GetMapping("/app/alterarSenha")
	public String abrirFormAlterarSenha(Model model) {
		
		return "usuario/alterarSenha";
	}
	
	@GetMapping("/app/adm/usuario/deletar")
	public String deletar(@RequestParam(required = true) Long id, HttpServletResponse response) throws IOException {
		
		Usuario usuarioADeletar = usuarioDAO.buscar(id);
		usuarioDAO.deletar(usuarioADeletar);
		
		return "redirect:/app/adm/usuario";
	}
	
	/*
	 * @VAlid <parametro> e colocar BindingResult em seguida
	 */
	@PostMapping( value = {"/app/adm/usuario/salvar"})
	public String salvar(@Valid  Usuario usuario,  BindingResult brUsuario,
						@RequestParam(name = "confirmacaoSenha", required = false) String confirmaSenha,
						@RequestParam(name = "isAdministrador", required = false) Boolean ehAdministrador,
						@RequestPart(name = "foto", required = false) MultipartFile foto) {
		
		
		//Verificando se � CADASTRO
		if(usuario.getId() == null) {
			//Checando se a senha n�o � igual a confirma��o de senha (CASO SEJA UM CADASTRO)
			if(!confirmaSenha.equals(usuario.getSenha())) {
				brUsuario.addError(new FieldError("usuario", "senha", "As senhas n�o coincidem"));
			}
			
			//Chegando se e-mail j� est� sendo utilizado
			if(usuarioDAO.buscarPorEmail(usuario.getEmail()) != null) {
				brUsuario.addError(new FieldError("usuario", "email", "O e-mail selecionado j� esta em uso"));
			}
			
			//Se houverem erros no usu�rio, reabre o formul�rio
			if(brUsuario.hasErrors()) {
				return "usuario/form";
			}
		}else {
			//Valida��es de ALTERA��O
			if(brUsuario.hasFieldErrors("nome") || brUsuario.hasFieldErrors("sobrenome")) {
				return "usuario/form";
			}
		}
		
		//Verificando se o checkbox foi marcado atrav�s da checagem de valor nulo
		System.out.println("� administrador: " + ehAdministrador);
		if(ehAdministrador != null) {
			usuario.setTipo(TiposUsuario.ADMINISTRADOR);
		}else {
			usuario.setTipo(TiposUsuario.COMUM);
		}
		
		
		//CASO CADASTRO...
		if(usuario.getId() == null) {
			//Salvando usu�rio hasheando a senha
			usuario.hashearSenha();
			usuarioDAO.persistir(usuario);
			
			//Enviando email
			String titulo = "Bem-Vindo ao Jucacontrol";
			String corpo = "Ol�, " + usuario.getNome() + "! Seja bem-vindo ao Jucacontrol. " +
			"Acesse o link: localhost:8080/jc/ para realizar o login.";
			
			try {
				EmailUtils.enviarEmail(titulo, corpo, usuario.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			
		}
		//CASO ALTERA��O
		else {
			//PEga o usu�rio do HIBERNATE atrav�s do id do form para poder alter�-lo
			Usuario usuarioBanco = usuarioDAO.buscar(usuario.getId());
			usuarioBanco.setNome(usuario.getNome());
			usuarioBanco.setSobrenome(usuario.getSobrenome());
			usuarioBanco.setTipo(usuario.getTipo());
			
			usuarioDAO.alterar(usuarioBanco);
		}
		
		
		//Armazenando a foto de perfil
		try {
			storage.armazenarFotoDePerfil("foto_" + usuario.getId(), foto.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/app/adm/usuario";
	}
	
	@PostMapping({"/usuario/autenticar"})
	//@Valid - DEtermina que o Spring deve validar o objeto
	//BindingResult - Armazena os poss�veis erros de valida��o que ocorreram no objeto
	public String autenticar(@Valid Usuario usuario, BindingResult brUsuario, HttpSession session) {

		//Verificando se usu�rio existe
		//Caso a senha do usu�rio do sistema seja hasheada n�o esque�ais
		//de hashear para compara-lo, Felipenses 132:1
		usuario.hashearSenha();
		Usuario usuarioBuscado = usuarioDAO.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		if(usuarioBuscado == null) {
			brUsuario.addError(new FieldError("usuario", "email", "O e-mail ou senha incorretos"));
		}
		
		//Verifica se h� erros no BindingResult
		if(brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
			System.out.println("Deu erro");
			System.out.println(brUsuario);
			return "index";
		}
		
		//Aplicando o usu�rio na sess�o
		session.setAttribute("usuarioAutenticado", usuarioBuscado);
		
		return "redirect:/app/";
	}
	
	@GetMapping({"/sair"})
	public String logout() {
		
		return "redirect:/";
	}

}
