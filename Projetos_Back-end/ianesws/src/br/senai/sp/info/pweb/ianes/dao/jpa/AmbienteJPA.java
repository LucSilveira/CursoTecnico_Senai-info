package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.AmbienteDao;
import br.senai.sp.info.pweb.ianes.models.Ambiente;


@Repository
@Transactional //Determina que este método precisa ter uma transação aberta antes de ser chamado
public class AmbienteJPA implements AmbienteDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void persistir(Ambiente obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Ambiente obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Ambiente obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}

	@Override
	public Ambiente buscar(Long id) {
		String hql = "FROM Ambiente a WHERE a.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		
		//Pegando o primeiro resultado
		List<Ambiente> resultado = query.list();
		
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Ambiente> buscarTodos() {
		String hql = "FROM Ambiente";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Ambiente buscarPorNome(String nome) {
		//HQL - Hibernate Query Language
		//Linguagem que mistura elementos orientado a objetos e SQL
		String hql = "FROM Ambiente a WHERE a.nome = :nome";
					
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
						
		//Trocando os valores dos coringas
		query.setParameter("nome", nome);
				
		//Disparada a busca e guarda o resultado em lista
		List<Ambiente> resultado = query.list();
		
		//Já que queremos apenas um objeto, devemos verificar se a lista NÃO é vazia
		if(!resultado.isEmpty()) {
			return resultado.get(0);
		}else {
			return null;
		}
	}
}
