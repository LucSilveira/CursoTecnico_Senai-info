package br.senai.sp.info.pweb.ianes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.pweb.ianes.dao.ItemDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.models.Item;

/**
 * Classe que passa os paremetros para o controller
 * configurando as ac�es que ser�o realizadas
 * 
 * @author Lucas Silveira Portal
 */
@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	/**
	 * M�todo que busca um item especifico
	 * atav�s de sua identificacao
	 * 
	 * @param id - identificacao do item
	 * @return - O objeto que est� inserido no banco
	 * @throws EntidadeNaoEncontradaException - Caso nao h� nenhum objeto
	 */
	public Item buscar(Long id) throws EntidadeNaoEncontradaException {
		/* Busca o item expecifico */
		
		Item itemBuscado = itemDao.buscar(id); // Buscando id do item
		
		if (itemDao.buscar(id) == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		if (itemBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return itemBuscado;
	}

	/**
	 * M�todo que busca todos os itens do sistema
	 * 
	 * @return - os objetos buscados
	 * @throws EntidadeNaoEncontradaException - Caso n�o haja objetos no banco
	 */
	public List<Item> buscarTodos() throws EntidadeNaoEncontradaException {
		
		if (itemDao.buscarTodos() == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return itemDao.buscarTodos();
	}
}