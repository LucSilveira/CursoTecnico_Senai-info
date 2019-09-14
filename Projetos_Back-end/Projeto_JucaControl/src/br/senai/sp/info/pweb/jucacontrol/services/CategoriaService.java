package br.senai.sp.info.pweb.jucacontrol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import br.senai.sp.info.pweb.jucacontrol.dao.CategoriaOcorrenciaDAO;
import br.senai.sp.info.pweb.jucacontrol.exceptions.EntidadeNaoEncontradaException;
import br.senai.sp.info.pweb.jucacontrol.exceptions.ValidacaoException;
import br.senai.sp.info.pweb.jucacontrol.models.CategoriaOcorrencia;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaOcorrenciaDAO categoriaOcorrenciaDao;
	
	public List<CategoriaOcorrencia> buscarTodos(){
		return categoriaOcorrenciaDao.buscarTodos();
	}
	
	private CategoriaOcorrencia buscarPorId(Long id) throws EntidadeNaoEncontradaException{
		//Busca pelo id e verifica se existe
		CategoriaOcorrencia categoria = categoriaOcorrenciaDao.buscar(id);
		if(categoria == null) {
			throw new EntidadeNaoEncontradaException();
		}
		return categoria;
	}
	
	public CategoriaOcorrencia deletar(Long id) throws EntidadeNaoEncontradaException{
		
		CategoriaOcorrencia categoriaBuscada = categoriaOcorrenciaDao.buscar(id);
		
		if(categoriaBuscada != null) {
			categoriaOcorrenciaDao.deletar(categoriaBuscada);
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
	public CategoriaOcorrencia buscar(Long id) throws EntidadeNaoEncontradaException{
		return buscarPorId(id);
	}
	
	/**
	 * Cadastro de uma categoria no banco de dados
	 * @param categoria - obj que sera persistido
	 * @param bindResult - Obj com erros gerado pelo Spring
	 * @return - O obj cadastro no banco com o Id
	 * @throws ValidacaoException - Existem erros nos campos da categoria ou quando o nome estiver duplicado
	 */
	
	public CategoriaOcorrencia cadastrar(CategoriaOcorrencia categoria, BindingResult bindResult) throws ValidacaoException{
		
		//Valida os campos
		if(bindResult.hasErrors()) {
			throw new ValidacaoException();
		}
		
		if(categoriaOcorrenciaDao.buscarPorNome(categoria.getNome()) != null) {
			bindResult.addError(new FieldError("categoriaOcorrencia", "nome", "Nome já usado"));
			throw new ValidacaoException();
		}
		
		//Cadastrar no banco de dados
		categoriaOcorrenciaDao.persistir(categoria);
		
		//Retorna a catogoria pra quem chamou este método
		return categoria;
		
	}

}