package br.senai.sp.info.pweb.ianes.dao;

import java.util.List;

import br.senai.sp.info.pweb.ianes.models.Item;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;

public interface MovimentacaoDao extends Dao<Movimentacao>{
	
	public List<Movimentacao> buscarPeloItem(Long itemId);
	
	public Movimentacao buscaUltimaMovimentacao(Long id);

	public Item buscarItem(Long itemId);

}
