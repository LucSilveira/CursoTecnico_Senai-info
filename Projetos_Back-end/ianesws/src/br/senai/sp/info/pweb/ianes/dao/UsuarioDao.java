package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.Usuario;

public interface UsuarioDao extends Dao<Usuario>{
	
	public Usuario buscaPorEmail(String email);
	public Usuario buscarPorEmailESenha(String email, String senha);
	public String buscarPorSenha(Long id);

}
