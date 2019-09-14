package br.senai.sp.info.pweb.ianes.dao;

import java.util.List;

import br.senai.sp.info.pweb.ianes.models.Item;

public interface ItemDao extends Dao<Item>{
	
	public List<Item> buscarTodosPeloPatrimonio(Long patrimonioId);

}
