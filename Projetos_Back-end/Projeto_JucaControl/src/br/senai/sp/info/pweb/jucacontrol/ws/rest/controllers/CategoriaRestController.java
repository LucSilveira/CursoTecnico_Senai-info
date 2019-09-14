package br.senai.sp.info.pweb.jucacontrol.ws.rest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.jucacontrol.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.jucacontrol.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;
import br.senai.sp.info.pweb.jucacontrol.services.CategoriaService;
import br.senai.sp.info.pweb.jucacontrol.utils.MapUtils;

@RestController
@RequestMapping("/rest/categorias")
public class CategoriaRestController {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Object> buscarTodos(){
		
		try {
			return ResponseEntity.ok(categoriaService.buscarTodos());
		}catch (Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.status(500).build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id, CategoriaOcorrencia categoria){
		
		try{
			return ResponseEntity.ok(categoriaService.deletar(id));
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.ok(categoriaService.buscar(id));
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).build();
		}
		
	}
	
	/**
	 * Cadastra um categoria
	 * @param categoria - 
	 * @param brCategoria - 
	 * @return - 
	 * <ul>
	 * 	<li>200 - Cadastro realizado</li>
	 * 	<li>422 - Erro de validacao nos campos ou dados duplicado</li>
	 * 	<li>500 - Erros no servidor</li>
	 * </ul>
	 */
	
	@PostMapping
	public ResponseEntity<Object> cadastrar(@RequestBody @Valid CategoriaOcorrencia categoria, BindingResult brCategoria){
		
		try {
			//Chama o service para realizar o cadastro
			CategoriaOcorrencia c = categoriaService.cadastrar(categoria, brCategoria);
			
			//Retorna a resposta com o objeto cadastrado
			return ResponseEntity.ok(c);
			
		}catch (ValidacaoException e){
			
			return ResponseEntity.unprocessableEntity().body(MapUtils.mapaDe(brCategoria));
		}catch(Exception e){
			//Caso ocorra um erro nao verificado
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
