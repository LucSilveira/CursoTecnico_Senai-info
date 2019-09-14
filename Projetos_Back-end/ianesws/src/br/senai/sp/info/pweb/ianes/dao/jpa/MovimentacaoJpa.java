package br.senai.sp.info.pweb.ianes.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.senai.sp.info.pweb.ianes.dao.MovimentacaoDao;
import br.senai.sp.info.pweb.ianes.models.Item;
import br.senai.sp.info.pweb.ianes.models.Movimentacao;

@Repository
@Transactional
public class MovimentacaoJpa implements MovimentacaoDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/*     Metodos  */
	
	@Override
	public void persistir(Movimentacao obj) {
		sessionFactory.getCurrentSession().persist(obj);
	}

	@Override
	public void alterar(Movimentacao obj) {
		sessionFactory.getCurrentSession().update(obj);
	}

	@Override
	public void deletar(Movimentacao obj) {
		sessionFactory.getCurrentSession().delete(obj);
	}
	
	
	@Override
	public Movimentacao buscar(Long id) {
		String hql = "FROM Movimentacao m WHERE m.id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Movimentacao> resultados = query.list();

		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public List<Movimentacao> buscarTodos() {
		String hql = "FROM Movimentacao";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<Movimentacao> buscarPeloItem(Long itemId) {
			String hql = "FROM Movimentacao m WHERE m.identificacao.identificacao = :id";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", itemId);
			List<Movimentacao> resultados = query.list();
			//return query.list();
			return resultados;
	}
	
	@Override
	public Item buscarItem(Long itemId) {
		
			String hql = "FROM Item i WHERE i.movimentacao.id = :id";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter("id", itemId);
			Item result = (Item) query.list();
			//return query.list();
			return result;
	}

	@Override
	public Movimentacao buscaUltimaMovimentacao(Long id) {
		
		String hql = "FROM Movimentacao m WHERE m.identificacao.id = :id order by id desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id", id);
		List<Movimentacao> resultados = query.list();

		if (!resultados.isEmpty()) {
			return resultados.get(0);
		} else {
			return null;
		}
	}
}