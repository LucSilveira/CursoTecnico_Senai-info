package br.senai.sp.info.pweb.ianes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.senai.sp.info.pweb.ianes.dao.CategoriaDao;
import br.senai.sp.info.pweb.ianes.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.ianes.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.ianes.models.Categoria;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaDao categoriaDao;
	
	/**
	 * Método que busca todas as categorias do sistema
	 * 
	 * @return
	 */
	public List<Categoria> buscarTodos(){
		return categoriaDao.buscarTodos();
	}
	
	/**
	 * Busca uma categoria expecifica
	 * 
	 * @param id - identificacao da categoria
	 * @return
	 * @throws EntidadeNaoEncontradaException
	 */
	private Categoria buscarPorId(Long id) throws EntidadeNaoEncontradaException{
		//Busca pelo id e verifica se existe
		Categoria categoria = categoriaDao.buscar(id);
		if(categoria == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return categoria;
	}
	
	/**
	 * Método onde deleta uma categoria através da sua identificacao
	 * 
	 * @param id - 
	 * @return
	 * @throws EntidadeNaoEncontradaException
	 */
	public Categoria deletar(Long id) throws EntidadeNaoEncontradaException{
		
		Categoria categoriaBuscada = categoriaDao.buscar(id);
		
		if(categoriaBuscada != null) {
			categoriaDao.deletar(categoriaBuscada);
		}else {
			throw new EntidadeNaoEncontradaException();
		}
		return categoriaBuscada;
	}
	
	/**
	 * Busca uma determinada categoria de ocorrencia
	 * @param id - O id da categoria buscada
	 * @return
	 * @throws EntidadeNaoEncontradaException - Caso a categoria nao exista
	 */
	public Categoria buscar(Long id) throws EntidadeNaoEncontradaException{
		return buscarPorId(id);
	}
	
	/**
	 * Cadastro de uma categoria no banco de dados
	 * @param categoria - obj que sera persistido
	 * @param bindResult - Obj com erros gerado pelo Spring
	 * @return - O obj cadastro no banco com o Id
	 * @throws ValidacaoException - Existem erros nos campos da categoria ou quando o nome estiver duplicado
	 */
	
	public Categoria cadastrar(Categoria categoria, BindingResult bindResult) throws ValidacaoException{
		
		//Valida os campos
		if(bindResult.hasErrors()) {
			throw new ValidacaoException();
		}
		
		if(categoriaDao.buscarPorNome(categoria.getNome()) != null) {
			bindResult.addError(new FieldError("categoria", "nome", "Nome já usado"));
			throw new ValidacaoException();
		}
		
		//Cadastrar no banco de dados
		categoriaDao.persistir(categoria);
		
		//Retorna a catogoria pra quem chamou este método
		return categoria;
		
	}

}
