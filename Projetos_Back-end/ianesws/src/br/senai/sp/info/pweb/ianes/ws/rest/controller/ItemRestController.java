package br.senai.sp.info.pweb.ianes.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.services.ItemService;

/**
 * Classe que realiza as ações do Item
 * tendo em vista o token gerado no sistema
 * 
 * @author Lucas Silveira Portal
 *
 */

@RestController
@RequestMapping("/rest/itens")
public class ItemRestController {

	@Autowired
	private ItemService itemService;
	
	/**
	 * Busca um item expecifico do sistema através do seu 'id'
	 * 
	 * @param id - identificacao dos itens
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		
		try {
			return ResponseEntity.ok(itemService.buscar(id));
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch(Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
	
	/**
	 * Busca todos os itens do sistema
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		
		try {
			return ResponseEntity.ok(itemService.buscarTodos());
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}	
}