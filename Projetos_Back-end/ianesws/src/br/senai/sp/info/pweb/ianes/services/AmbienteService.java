package br.senai.sp.info.pweb.ianes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.senai.sp.info.pweb.ianes.dao.AmbienteDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.models.Ambiente;

/**
 * Classe que passa os paremetros para o controller
 * configurando as acões que serão realizadas
 * 
 * @author Lucas Silveira Portal
 */
@Service
public class AmbienteService {

	@Autowired
	private AmbienteDao ambienteDao;

	/**
	 * Método onde busca um ambiente através da sua identificação
	 * 
	 * @param id - identificações do ambiente
	 * @param bind - Objeto com erros gerados
	 * @return - O objeto que está inserido no banco de dados
	 * @throws EntidadeNaoEncontradaException - classe que trata erros
	 */
	public Ambiente buscarId(Long id) throws EntidadeNaoEncontradaException {
		/* Buscando um ambientye expecifico */

		if (ambienteDao.buscar(id) == null) {
			throw new EntidadeNaoEncontradaException();
		}

		// Buscando id do ambiente
		Ambiente ambienteBuscado = ambienteDao.buscar(id);

		// verificando se o ambiente não existe
		if (ambienteBuscado == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return ambienteBuscado;
	}

	/**
	 * Método que busca todos os ambientes do sistema
	 * 
	 * @return - Os objetos buscados
	 * @throws EntidadeNaoEncontradaException  - Caso não haja objetos no banco
	 */
	public List<Ambiente> buscarTodos() throws EntidadeNaoEncontradaException {
		/* Buscando todos os ambientes */
		
		if (ambienteDao.buscarTodos() == null) {
			throw new EntidadeNaoEncontradaException();
		}
		
		return ambienteDao.buscarTodos();
	}

}