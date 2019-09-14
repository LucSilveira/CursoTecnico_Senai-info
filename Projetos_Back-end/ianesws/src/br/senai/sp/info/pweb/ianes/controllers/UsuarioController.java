package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.models.Tipo_usuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;
import br.senai.sp.info.pweb.ianes.utils.EmailUtils;
import br.senai.sp.info.pweb.ianes.utils.ProjetoStorage;


@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private ProjetoStorage storage;
	
	
	@GetMapping("/app/adm/usuario/editar")
	public String abrirEdicao(Model model, @RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response) throws IOException {
		
		model.addAttribute("usuario",usuarioDao.buscar(id));
		return "usuario/form";
	}

	@GetMapping("/app/adm/usuario")
	public String abrirLista(Model model, HttpSession session) {
		
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		System.out.println(autenticado);
		
		model.addAttribute("usuarios", usuarioDao.buscarTodos());
		return "usuario/lista";
	}

	@GetMapping("/app/adm/usuario/novo")
	public String abrirFormNovoUsuario(Model model) {	
		
		//Passando o objeto que será enviado para a tela
		model.addAttribute("usuario", new Usuario());	
		return "usuario/form";
	}

	@GetMapping("/app/perfil")
	public String abrirFormEditarUsuarioLogado(Model model) {
		
		return "usuario/form";
	}
	

	@GetMapping("/app/alterarSenha")
	public String abrirFormSenha(Model model) {
		return "usuario/alterar";
	}

	@PostMapping("/app/salvarSenha")
	public String registraSenhaNova(String senha, String senhaNova, Model model, HttpSession session) {
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		String senhaDoBanco = usuarioDao.buscarPorSenha(autenticado.getId());
		
		List<String> errosSenha = new ArrayList<>(5);
		
		if (senhaNova.length() < 1 || senha.length() > 20) {
			errosSenha.add("O campo senha deve estar entre 1 e 20 caracteres");
			model.addAttribute("erros", errosSenha);
		}
		
		if(senhaNova.isEmpty()|| senha.isEmpty()) {
			errosSenha.add("O campo senha deve estar entre 1 e 20 caracteres");
			model.addAttribute("erros", errosSenha);
		}
				
		try {
			senha = DigestUtils.md5DigestAsHex(senha.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		if (senhaDoBanco.equals(senha)) {
			try {
				senhaNova = DigestUtils.md5DigestAsHex(senhaNova.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}

			autenticado.setSenha(senhaNova);
			usuarioDao.alterar(autenticado);

		} else {
			model.addAttribute("erros", errosSenha);
			return "usuario/abrirLista";
		}
		return "redirect:/app";
	}

	@GetMapping("/app/adm/usuario/deletar")
	public String deletar(@RequestParam(required = true) Long id, HttpServletResponse response) throws IOException {
		
		usuarioDao.deletar(usuarioDao.buscar(id));
		return "redirect:/app/adm/usuario";
	}

	@PostMapping(value = { "/app/adm/usuario/salvar" })
	public String salvar(@Valid Usuario usuario, BindingResult brUsuario,
			@RequestParam(name = "confirmacaoSenha", required = false) String confirmaSenha,
			@RequestParam(name = "isAdministrador", required = false) Boolean ehAdministrador,
			@RequestPart(name = "foto", required = false) MultipartFile foto) {
		
		//Verificação se é CADASTRO
		if(usuario.getId() == null) {
			
			//Checando se a senha é igual a confirmação de senha
			if(!confirmaSenha.equals(usuario.getSenha())) {
				brUsuario.addError(new FieldError("usuario", "senha", "As senhas não coincidem."));
			}
			
			//Chegando se e-mail já está sendo utilizado
			if(usuarioDao.buscaPorEmail(usuario.getEmail()) != null) {
				brUsuario.addError(new FieldError("usuario", "email", "O email selecionado já está em uso."));
			}
			
			//Se houverem erros, reabre o form
			if(brUsuario.hasErrors()) {
				return "usuario/form";
			}
		}else {
			//Validações de ALTERAÇÃO
			if(brUsuario.hasFieldErrors("nome") || brUsuario.hasFieldErrors("sobrenome")) {
				return "usuario/form";
			}
		}
		
		//Verificando se o checkbox foi marcado
		if(ehAdministrador != null){
			usuario.setTipo(Tipo_usuario.ADMINISTRADOR);
		}else{
			usuario.setTipo(Tipo_usuario.COMUM);
		}
		
		//CASO CADASTRO...
		if(usuario.getId() == null) {
			//Salvando usuário hasheando a senha
			usuario.hashearSenha();
			usuarioDao.persistir(usuario);
			
			//Enviando email
			String titulo = "Bem-vindo ao sistema da Ianes!";
			String corpo = "Olá, " + usuario.getNome() + "! Seja bem-vindo ao sistema da Ianes Patrimônios. " +
			"Acesse o link: localhost:8080/ianes/ para realizar login.";
			try {
				EmailUtils.enviarEmail(titulo, corpo, usuario.getEmail());
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		//CASO ALTERAÇÃO...
		else {
			Usuario usuarioBanco = usuarioDao.buscar(usuario.getId());
			usuarioBanco.setNome(usuario.getNome());
			usuarioBanco.setSobrenome(usuario.getSobrenome());
			usuarioBanco.setTipo(usuario.getTipo());
			usuarioDao.alterar(usuarioBanco);
		}
		
		//Armazenando a foto de perfil
		try {
			storage.armazenarFotoDePerfil("foto_" + usuario.getId(), foto.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/app/adm/usuario";
	}

	@PostMapping({ "/usuario/autenticar" })
	// @Valid - Determina que o Spring deve validar o objeto
	// BindingResult - Armazena os possíveis erros de validação que ocorreram no objeto
	public String autenticar(@Valid Usuario usuario, BindingResult brUsuario, HttpSession session) {
		
		//Verificando se o usuario existe
		//Caso a senha do usuario do sistema seja hasheada não se esqueça
		//de hashear para compara-lo
		usuario.hashearSenha();
		
		System.out.println(usuario.toString());
		
		Usuario usuarioBuscado = usuarioDao.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha());
		System.out.println(usuarioBuscado);
		
		if(usuarioBuscado == null) {
			System.out.println(usuarioBuscado);
			brUsuario.addError(new FieldError("email", "senha", "Email ou senha incorretos!"));
		}
		
		// Verifica se há erros no BindingReuslt
		if (brUsuario.hasFieldErrors("email") || brUsuario.hasFieldErrors("senha")) {
			System.out.println("Deu erro: " + brUsuario);
			return "index";
		}
		
		System.out.println(usuarioBuscado);
		session.setAttribute("usuarioAutenticado", usuarioBuscado);
				
		return "redirect:/app/patrimonio";
	}
}