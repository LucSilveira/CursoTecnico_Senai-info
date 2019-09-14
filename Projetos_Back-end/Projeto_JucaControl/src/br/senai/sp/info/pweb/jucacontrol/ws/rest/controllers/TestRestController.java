package br.senai.sp.info.pweb.jucacontrol.ws.rest.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@RestController // espera que o metodo retorne apenas dados nao uma view
@RequestMapping("/rest/test/categorias") // aplica a rota para tudo
public class TestRestController {

	// Lista (banco de dados)
	static List<CategoriaOcorrencia> bancoDados = new LinkedList<>();

	static Long ultimoId = 3L;

	public TestRestController() {
		CategoriaOcorrencia cat = new CategoriaOcorrencia();
		cat.setId(1L);
		cat.setNome("Informatica");

		CategoriaOcorrencia cat2 = new CategoriaOcorrencia();
		cat2.setId(2L);
		cat2.setNome("Informatica");

		CategoriaOcorrencia cat3 = new CategoriaOcorrencia();
		cat3.setId(3L);
		cat3.setNome("Informatica");

		// add no banco de dados
		bancoDados.add(cat);
		bancoDados.add(cat2);
		bancoDados.add(cat3);
	}

	// Busca todos
	@GetMapping
	public ResponseEntity<Object> buscarTodos() {
		return ResponseEntity.ok(bancoDados);
	}

	@PostMapping // ↓ speak the Spring que o object vem do corpo da requisção
	public ResponseEntity<Object> cadastrar(@RequestBody CategoriaOcorrencia categoriaOcorrencia) {

		// adiciona a categoria cadastrada no banco
		System.out.println("Nome: " + categoriaOcorrencia.getNome());
		categoriaOcorrencia.setId(++ultimoId);
		bancoDados.add(categoriaOcorrencia);

		return ResponseEntity.ok(categoriaOcorrencia);
	}

	@DeleteMapping("/{id}") // ↓ caminho da variavel
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		/*****************************************
		 * System.out.println("id: " + id); return ResponseEntity.ok(null);
		 ******************************************/
		CategoriaOcorrencia categoriaBuscada = buscaCategoriaPorId(id);

		// verificando se o id exist
		if (categoriaBuscada != null) {
			bancoDados.remove(categoriaBuscada);
			return ResponseEntity.noContent().build(); // 204 - tabela de resposta
		} else {
			return ResponseEntity.notFound().build(); // 404 - Not found
		}

	}

	public CategoriaOcorrencia buscaCategoriaPorId(Long id) {

		// Busca todas as categoria, caso o Id seja igual, retorna a categoria
		for (CategoriaOcorrencia categoriaOcorrencia : bancoDados) {
			if (id.equals(categoriaOcorrencia.getId())) {
				return categoriaOcorrencia;
			}
		}
		return null;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> alterar(@PathVariable Long id, @RequestBody CategoriaOcorrencia categoria) {
		// Busca pelo id
		CategoriaOcorrencia categoriaBuscada = buscaCategoriaPorId(id);

		if (categoriaBuscada != null) {
			categoriaBuscada.setNome(categoria.getNome());
			return ResponseEntity.ok(categoriaBuscada);
		} else {
			return ResponseEntity.notFound().build(); // 404 - Not found
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscaUm(@PathVariable Long id) {

		CategoriaOcorrencia categoriaBuscada = buscaCategoriaPorId(id);

		// verificando se o id exist
		if (categoriaBuscada != null) {
			categoriaBuscada.getId();
			return ResponseEntity.ok(categoriaBuscada);
		} else {
			return ResponseEntity.notFound().build(); // 404 - Not found
		}
	}

}