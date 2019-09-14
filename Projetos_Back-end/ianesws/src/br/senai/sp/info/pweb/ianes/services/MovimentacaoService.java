package br.senai.sp.info.pweb.ianes.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.senai.sp.info.pweb.ianes.dao.AmbienteDao;
import br.senai.sp.info.pweb.ianes.dao.ItemDao;
import br.senai.sp.info.pweb.ianes.dao.MovimentacaoDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.ianes.models.Item;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;
import br.senai.sp.info.pweb.ianes.models.Usuario;

/**
 * Classe que passa os paremetros para o controller
 * configurando as acões que serão realizadas
 * 
 * @author Lucas Silveira Portal
 */
@Service
public class MovimentacaoService {

	@Autowired
	private MovimentacaoDao movimentacaoDao;

	@Autowired
	private ItemDao itemDao;
	
	@Autowired
	private AmbienteDao ambienteDao;

	/**
	 * Método que busca as movimentacoes através do item expecifico
	 * 
	 * @param itemId - o item que precisa ser identificado
	 * @return - movimentacoes do item
	 * @throws EntidadeNaoEncontradaException
	 */
	public List<Movimentacao> buscarPeloItem(Long itemId) throws EntidadeNaoEncontradaException {

		if (itemDao.buscar(itemId) == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return movimentacaoDao.buscarPeloItem(itemId);
	}

	/**
	 * Método que faz a movimentacao de um item expecificado
	 * 
	 * @param movimentacao - objeto que seta as movimentacoes
	 * @param brMovi - objeto que gera erros
	 * @param idItem - objeto que precisa ser definido
	 * @return - retorna a movimentacao do item
	 * @throws ValidacaoException - caso haja erros de cadastro
	 * @throws EntidadeNaoEncontradaException - caso nao haja objeto
	 */
	public Movimentacao movimentarItem(Movimentacao movimentacao, BindingResult brMovi, Long idItem)
			throws ValidacaoException, EntidadeNaoEncontradaException {

		if (brMovi.hasErrors()) {
			throw new ValidacaoException();
		}

		if (itemDao.buscar(idItem) == null) {
			brMovi.addError(new FieldError("item", "id", "Item não encontrado"));
			throw new EntidadeNaoEncontradaException();
		}
		
		if(movimentacao.getDestino() == null) {
			brMovi.addError(new FieldError("movimentacao", "destino", "Destino nao informado"));
			throw new ValidacaoException();
		}
		
		if(ambienteDao.buscar(movimentacao.getDestino().getId()) == null) {
			brMovi.addError(new FieldError("movimentacao", "destino", "Destino nao informado"));
			throw new EntidadeNaoEncontradaException();
		}

		Item itemMovimentado = itemDao.buscar(idItem);
		Movimentacao movimentacaoOrigem = movimentacaoDao.buscaUltimaMovimentacao(idItem);
		Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		movimentacao.setMovimentou(user);

		movimentacao.setIdentificacao(itemMovimentado);

		movimentacao.setData_movimentacao(new Date());
		movimentacao.getIdentificacao().setData_movimentou(new Date());

		if (movimentacaoOrigem == null) {
			movimentacao.setDestino(itemMovimentado.getLocalizacao());

		} else {
			movimentacao.setOrigem(movimentacaoOrigem.getDestino());
		}

		if (movimentacao.getOrigem().getId() == movimentacao.getDestino().getId()) {
			brMovi.addError(new FieldError("movimentacao", "destino", "Não pode movimentar para sua origem"));
			throw new ValidacaoException();

		} else {
			movimentacaoDao.persistir(movimentacao);
			itemMovimentado.setLocalizacao(movimentacao.getDestino());
			itemDao.alterar(itemMovimentado);
		}

		return movimentacao;
	}

}