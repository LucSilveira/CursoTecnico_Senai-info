package br.senai.sp.info.pweb.ianes.ws.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.ianes.dao.ItemDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;
import br.senai.sp.info.pweb.ianes.services.MovimentacaoService;
import br.senai.sp.info.pweb.ianes.utils.MapUtils;

/**
 * Método que realiza as ações de movimentação
 * 
 * @author Lucas Silveira Portal
 *
 */
@RestController
@RequestMapping("/rest/itens")
public class MovimentacaoRestController {

	@Autowired
	private MovimentacaoService moviService;

	@Autowired
	private ItemDao itemDao;

	/**
	 * Realiza uma movimentacao através
	 * do id expecificado do item
	 * 
	 * @param id - identificacao dos itens
	 * @return
	 * @throws EntidadeNaoEncontradaException - classe que trata o erro
	 */
	@GetMapping("/{id}/movimentacoes")
	public ResponseEntity<Object> buscarTodosPeloItem(@PathVariable Long id) throws EntidadeNaoEncontradaException {

		/*if (itemDao.buscar(id) == null) {
			throw new EntidadeNaoEncontradaException();
		}*/
		
		try {
			return ResponseEntity.ok(moviService.buscarPeloItem(id));
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			return ResponseEntity.status(500).build();
		}
	}

	@PostMapping("/{id}/movimentacoes")
	public ResponseEntity<Object> realizarMovimentacao(@PathVariable Long id, @RequestBody @Valid Movimentacao movimentacao,
			BindingResult brMovi) throws ValidacaoException, EntidadeNaoEncontradaException {
		
		/*if(itemDao.buscar(id) == null) {
			throw new EntidadeNaoEncontradaException();
		}*/
	
		try {
			Movimentacao movi = moviService.movimentarItem(movimentacao, brMovi, id);
			return ResponseEntity.ok(movi);

		} catch (ValidacaoException e) {
			return ResponseEntity.unprocessableEntity().body(MapUtils.mapaDe(brMovi));

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}