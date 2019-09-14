package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.ItemDao;
import br.senai.sp.info.pweb.ianes.models.Item;

@Repository
@Transactional
public class ItemJPA implements ItemDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(Item obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Item obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Item obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public Item buscar(Long identificacao) {
		String hql = "FROM Item i WHERE i.identificacao = :identificacao";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("identificacao", identificacao);
		List<Item> resultados = query.list();

		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Item> buscarTodos() {
		String hql = "FROM Item";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<Item> buscarTodosPeloPatrimonio(Long patId) {
		String hql = "FROM Item i WHERE i.associado.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", patId);
		List<Item> resultados = query.list();
		return query.list();
	}
}