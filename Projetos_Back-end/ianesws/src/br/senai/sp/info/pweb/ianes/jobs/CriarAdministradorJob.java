package br.senai.sp.info.pweb.ianes.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.senai.sp.info.pweb.ianes.dao.UsuarioDao;
import br.senai.sp.info.pweb.ianes.models.Tipo_usuario;
import br.senai.sp.info.pweb.ianes.models.Usuario;

@Component
public class CriarAdministradorJob implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		System.out.println("Cadastrando usuario administrador padrao");
		
		Usuario usuario = new Usuario();
		usuario.setEmail("admin@email.com");
		usuario.setSenha("admin");
		usuario.setNome("Administrador");
		usuario.setSobrenome("do Sistema");
		usuario.setTipo(Tipo_usuario.ADMINISTRADOR);
		usuario.hashearSenha();
		
		//Inserir no banco de dados
				System.out.println("Verificando se ADMINISTRADOR EXISTE...");
				
				if(usuarioDao.buscaPorEmail(usuario.getEmail()) == null){
					System.out.println("Cadastrando usuário administrador em 3, 2, 1...");
					usuarioDao.persistir(usuario);
				}else {
					System.out.println("Usuário já existe, prosseguindo com a aplicação normalmente");
				}
	}

}
