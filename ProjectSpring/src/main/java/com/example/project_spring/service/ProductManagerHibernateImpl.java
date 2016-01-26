package com.example.project_spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.project_spring.domain.Magazyn;

@Component
@Transactional
public class ProductManagerHibernateImpl implements ProductManager {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addNewPosition(Magazyn magazyn) {
		magazyn.setId(null);
		sessionFactory.getCurrentSession().persist(magazyn);
	}
	
	@Override
	public void editPosition(Magazyn magazyn) {
		sessionFactory.getCurrentSession().update(magazyn);
	}
	
	@Override
	public Magazyn findPositionByObject(Magazyn magazyn){
		return (Magazyn) sessionFactory.getCurrentSession().get(Magazyn.class, magazyn.getId());
	}
	
	@Override
	public boolean isPositionWithId(Long id){
		if (sessionFactory.getCurrentSession().get(Magazyn.class, id) != null){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public void removePosition(Magazyn magazyn){
		sessionFactory.getCurrentSession().delete(magazyn);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Magazyn> getAllPositions() {
		return sessionFactory.getCurrentSession().getNamedQuery("magazyn.all").list();
	}
	
	
}
