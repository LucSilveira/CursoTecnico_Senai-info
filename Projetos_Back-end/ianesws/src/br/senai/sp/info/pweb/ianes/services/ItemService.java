package br.senai.sp.info.pweb.ianes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senai.sp.info.pweb.ianes.dao.ItemDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.models.Item;

/**
 * Classe que passa os paremetros para o controller
 * configurando as acões que serão realizadas
 * 
 * @author Lucas Silveira Portal
 */
@Service
public class ItemService {

	@Autowired
	private ItemDao itemDao;

	/**
	 * Método que busca um item especifico
	 * atavés de sua identificacao
	 * 
	 * @param id - identificacao do item
	 * @return - O objeto que está inserido no banco
	 * @throws EntidadeNaoEncontradaException - Caso nao há nenhum objeto
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
	 * Método que busca todos os itens do sistema
	 * 
	 * @return - os objetos buscados
	 * @throws EntidadeNaoEncontradaException - Caso não haja objetos no banco
	 */
	public List<Item> buscarTodos() throws EntidadeNaoEncontradaException {
		
		if (itemDao.buscarTodos() == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return itemDao.buscarTodos();
	}
}