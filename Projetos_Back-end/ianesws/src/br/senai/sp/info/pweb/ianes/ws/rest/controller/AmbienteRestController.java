package br.senai.sp.info.pweb.ianes.ws.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.services.AmbienteService;

/**
 * Método que configura as ações do ambiente
 * tendo em vista o token gerado no sistema
 * 
 * @author Lucas Silveira Portal
 *
 */

@RestController
@RequestMapping("/rest/ambientes")
public class AmbienteRestController {
	
	@Autowired
	private AmbienteService ambienteService;
	
	/**
	 * Busca um abiente expecifico por meio do seu 'id'
	 * 
	 * @param id - as identificacoes do ambiente
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		
		try {
			return ResponseEntity.ok(ambienteService.buscarId(id));
		
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch(Exception e) {
			return ResponseEntity.status(500).build();
		}
	}
	
	/**
	 * Busca todos os ambientes do sistema
	 * 
	 * @return - Os objetos que estão inseridos no banco de dados
	 */
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		
		try {
			return ResponseEntity.ok(ambienteService.buscarTodos());
		
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
	}
}