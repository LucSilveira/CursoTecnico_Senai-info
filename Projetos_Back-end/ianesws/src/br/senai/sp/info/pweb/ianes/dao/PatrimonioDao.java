package br.senai.sp.info.pweb.ianes.dao;

import br.senai.sp.info.pweb.ianes.models.Patrimonio;

public interface PatrimonioDao extends Dao<Patrimonio> {
	
	public Patrimonio buscarPorNome(String nome);

}
