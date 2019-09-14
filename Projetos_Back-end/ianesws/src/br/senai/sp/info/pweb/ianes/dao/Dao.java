package br.senai.sp.info.pweb.ianes.dao;

import java.util.List;

public interface Dao<T> {
	
	public void persistir(T obj);
	public void alterar(T obj);
	public void deletar(T obj);
	public T buscar(Long id);
	public List<T> buscarTodos();

}
