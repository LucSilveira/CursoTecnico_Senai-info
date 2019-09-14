package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.CategoriaDao;
import br.senai.sp.info.pweb.ianes.models.Categoria;


@Repository
@Transactional
public class CategoriaJPA implements CategoriaDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Categoria obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Categoria obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Categoria obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public Categoria buscar(Long id) {
		String hql = "FROM Categoria c WHERE c.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Categoria> resultados = query.list();
		
		if(!resultados.isEmpty()) {
			return resultados.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Categoria> buscarTodos() {
		String hql = "FROM Categoria";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Categoria buscarPorNome(String nome) {
		String hql = "FROM Categoria c WHERE c.nome = :nome";
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		query.setParameter("nome", nome);
		List<Categoria> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
	}

}