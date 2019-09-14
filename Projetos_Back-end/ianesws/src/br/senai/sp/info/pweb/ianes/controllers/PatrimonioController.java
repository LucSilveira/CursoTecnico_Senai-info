package br.senai.sp.info.pweb.ianes.controllers;

import java.io.IOException;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import br.senai.sp.info.pweb.ianes.dao.CategoriaDao;
import br.senai.sp.info.pweb.ianes.dao.PatrimonioDao;
import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
//import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;
import br.senai.sp.info.pweb.ianes.models.Tipo_usuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;
import br.senai.sp.info.pweb.ianes.utils.ProjetoStorage;


@Controller
@RequestMapping("/app")
public class PatrimonioController {

	@Autowired
	private CategoriaDao categoriaDao;
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Autowired
	private PatrimonioDao patrimonioDao;
	
	@Autowired
	private ProjetoStorage storage;
	
	@GetMapping({ "/patrimonio" })
	public String abrirListaPatrimonio(Model model) {

		model.addAttribute("patrimonios", patrimonioDao.buscarTodos());
		return "patrimonio/lista";
	}
	

	@GetMapping({ "/adm/patrimonio/novo" })
	public String abrirFormPatrimonio(Model model, HttpSession session) {
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");

		System.out.println(autenticado);
		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "patrimonio/lista";
		} else {
			// Enviando as categorias para a p�gina
			model.addAttribute("categorias", categoriaDao.buscarTodos());

			// Enviando o modelo pro modelAttribute
			model.addAttribute("patrimonio", new Patrimonio());
			model.addAttribute("usuarios", usuarioDao.buscarTodos());
			return "patrimonio/form";
		}	
	}
	
	@GetMapping("/adm/patrimonio/deletar")
	public String deletar(@RequestParam(required = true) Long id, HttpServletResponse response) throws IOException {
		
		patrimonioDao.deletar(patrimonioDao.buscar(id));
		return "redirect:/app/patrimonio";
	}
	
	@GetMapping("/adm/patrimonio/editar")
	public String abrirEditarPatrimonio(@RequestParam(required = true) Long id, Model model, HttpSession session) {
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");

		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "patrimonio/lista";
		} else {
			model.addAttribute("patrimonio", patrimonioDao.buscar(id));
			model.addAttribute("categorias", categoriaDao.buscarTodos());
			return "patrimonio/form";
		}
	}

	@PostMapping("/adm/patrimonio/salvar")
	public String salvar(@Valid Patrimonio patrimonio, BindingResult brPatrimonio, Model model, HttpSession session,
						@RequestPart(name = "fotoPAT", required = false) MultipartFile fotoPt) {
		
		Usuario autenticado = (Usuario) session.getAttribute("usuarioAutenticado");
		
		System.out.println("L� vai");
		if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
			return "patrimonio/lista";
		}
		
		// Aplicando valida��es
		if (brPatrimonio.hasErrors()) {
			model.addAttribute("categorias", categoriaDao.buscarTodos());
			return "patrimonio/form";
		}

		// Verifica��o se � CADASTRO
		if (patrimonio.getId() == null) {
			
			//Chegando se nome j� est� sendo utilizado
			if(patrimonioDao.buscarPorNome(patrimonio.getNome()) != null) {
				brPatrimonio.addError(new FieldError("patrimonio", "nome", "O patrim�nio selecionado j� est� em uso."));
			}

			// Se houverem erros, reabre o form
			if (brPatrimonio.hasErrors()) {
				model.addAttribute("categorias", categoriaDao.buscarTodos());
				System.out.println(brPatrimonio);
				model.addAttribute("patrimonio", patrimonio);
				return "patrimonio/form";
			}
		}
		// CASO CADASTRO...
		if (patrimonio.getId() == null)  {
	
			patrimonio.setData_cadastro(new Date());
			patrimonio.setCadastrador(autenticado);

			patrimonioDao.persistir(patrimonio);
		}
		// CASO ALTERA��O...
		else {
			
			Patrimonio patrimonioAlterado = patrimonioDao.buscar(patrimonio.getId());

			//Chegando se nome j� est� sendo utilizado
			if(patrimonioDao.buscarPorNome(patrimonio.getNome()) != null) {
				brPatrimonio.addError(new FieldError("patrimonio", "nome", "O patrim�nio selecionado j� est� em uso."));
			}
			
			// SEGURAN�A: Verificar se o usu�rio logado � o dono da ocorr�ncia...
			if (autenticado.getTipo() != Tipo_usuario.ADMINISTRADOR) {
				throw new RuntimeException("Voc� n�o pode modificar esse patrim�nio!");
			}
			
			// Se houverem erros, volta o form
			if (brPatrimonio.hasErrors()) {
				model.addAttribute("categorias", categoriaDao.buscarTodos());
				System.out.println(brPatrimonio);
				model.addAttribute("patrimonio", patrimonio);
				return "patrimonio/form";
			}
		
			patrimonioAlterado.setNome(patrimonio.getNome());
			patrimonioAlterado.setObtem(patrimonio.getObtem());

			patrimonioDao.alterar(patrimonioAlterado);
			
		}
		
		System.out.println("passou uhuhuhuhuhuhuhuhuhuh " + fotoPt);
		try {
			System.out.println(storage.toString() + " test");
			//System.out.println(fotoPt.getBytes().toString() + " test2");
			storage.armazenarFotoDePerfil("foto__" + patrimonio.getId(), fotoPt.getBytes());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/app/patrimonio";
	}
}