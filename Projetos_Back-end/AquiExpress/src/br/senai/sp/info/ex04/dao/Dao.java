package br.senai.sp.info.ex04.dao;

import java.util.List;

public interface Dao<T>{

	public T buscar(Long id);
	public List<T> buscarTodos();
	public void alterar(T obj);
	public void deletar(T obj);
	public void persistir(T obj);
}
