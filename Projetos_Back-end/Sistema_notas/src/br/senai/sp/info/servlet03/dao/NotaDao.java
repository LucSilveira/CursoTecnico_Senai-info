package br.senai.sp.info.servlet03.dao;

import java.util.LinkedList;
import java.util.List;

import br.senai.sp.info.pweb.servlet03.models.Nota;

public class NotaDao {
	
	private static List<Nota> notas = new LinkedList<>();
	
	private static Long ultimoId = 0L;
	
	public void add(Nota nota) {
		ultimoId++;
		nota.setId(ultimoId);
		notas.add(nota);
		
	}
	
	public Nota buscar(Long id) {
		//percorrer a lista para encontrar qual nota tem o id informado
		for (Nota nota : notas) {
			if(nota.getId().equals(id)) {
				return nota;
			}
		}
		return null;
	}
	
	public List<Nota> buscaTodos(){
		return notas;
	}
	
	public void deletar(Long id) {
		
		//Remove a nota da lista
		notas.remove(this.buscar(id));
		
	}

}
