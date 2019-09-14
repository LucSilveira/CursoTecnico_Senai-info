package br.senai.sp.info.pweb.jucacontrol.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.pweb.jucacontrol.dao.UsuarioDAO;
import br.senai.sp.info.pweb.jucacontrol.models.TiposUsuario;
import br.senai.sp.info.pweb.jucacontrol.models.Usuario;

//ApplicationLIstener - Interface para eventos que acontecem na aplica��o web (iniciada/parada, etc.)
//ContextRefreshedEvent - Especifica que o evento que ser� observado � de 'atualiza��o de contexto'
//ou seja, quando o spring for iniciado
@Component
public class CriarAdministradorJob implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		System.out.println("CADASTRANDO USU�RIO ADMINISTRADOR PADR�O...");
		
		Usuario usuario = new Usuario();
		usuario.setEmail("admin@email.com");
		usuario.setSenha("admin");
		usuario.setNome("Administrador");
		usuario.setSobrenome("do Sistema");
		usuario.setTipo(TiposUsuario.ADMINISTRADOR);
		usuario.hashearSenha();
		
		//Inserir no banco de dados
		System.out.println("Verificando se ADMINISTRADOR EXISTE...");
		
		if(usuarioDAO.buscarPorEmail(usuario.getEmail()) == null) {
			System.out.println("Cadastrando usu�rio administrador em 3, 2, 1...");
			usuarioDAO.persistir(usuario);
		}else {
			System.out.println("Usu�rio j� existe, prosseguindo com a aplica��o normalmente");
		}
		
	}

}
