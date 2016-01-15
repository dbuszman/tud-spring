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
	public void addNewProduct(Magazyn magazyn) {
		magazyn.setId(null);
		sessionFactory.getCurrentSession().persist(magazyn);
	}
	@Override
	public Magazyn findProductById(Long id){
		return (Magazyn) sessionFactory.getCurrentSession().get(Magazyn.class, id);
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Magazyn> getAllProducts() {
		return sessionFactory.getCurrentSession().getNamedQuery("magazyn.all").list();
	}
	
	
}
