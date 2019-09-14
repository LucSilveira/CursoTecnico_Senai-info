package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.PatrimonioDao;
import br.senai.sp.info.pweb.ianes.models.Patrimonio;


@Repository
@Transactional
public class PatrimonioJPA implements PatrimonioDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void persistir(Patrimonio obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Patrimonio obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Patrimonio obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public Patrimonio buscar(Long id) {
		String hql = "FROM Patrimonio p WHERE p.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Patrimonio> resultados = query.list();

		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Patrimonio> buscarTodos() {
		String hql = "FROM Patrimonio";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Patrimonio buscarPorNome(String nome) {
		// HQL - Hibernate Query Language
		// Linguagem que mistura elementos orientado a objetos e SQL
		String hql = "FROM Patrimonio p WHERE p.nome = :nome";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		// Trocando os valores dos coringas
		query.setParameter("nome", nome);

		// Disparada a busca e guarda o resultado em lista
		List<Patrimonio> resultado = query.list();

		// Já que queremos apenas um objeto, devemos verificar se a lista NÃO é vazia
		if (!resultado.isEmpty()) {
			return resultado.get(0);
		} else {
			return null;
		}
	}
}