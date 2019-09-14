package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.Categoria;

public interface CategoriaDao extends Dao<Categoria> {
	
	public Categoria buscarPorNome(String nome);

}
