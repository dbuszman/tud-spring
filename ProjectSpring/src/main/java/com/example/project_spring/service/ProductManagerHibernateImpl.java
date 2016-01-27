package com.example.project_spring.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.project_spring.domain.Magazyn;
import com.example.project_spring.domain.ToOrder;

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
	public void removePosition(Magazyn magazyn){
		sessionFactory.getCurrentSession().delete(magazyn);
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
	@SuppressWarnings("unchecked")
	public List<Magazyn> findPositionByName(String name) {
		return sessionFactory.getCurrentSession().getNamedQuery("magazyn.byName").setString("name", name).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Magazyn> getAllPositions() {
		return sessionFactory.getCurrentSession().getNamedQuery("magazyn.all").list();
	}
	
	
	@Override
	public void addNewOrder(ToOrder order) {
		order.setId(null);
		sessionFactory.getCurrentSession().persist(order);
	}
	
	@Override
	public void editOrder(ToOrder order) {
		sessionFactory.getCurrentSession().update(order);
	}
	
	@Override
	public void removeOrder(ToOrder order) {
		sessionFactory.getCurrentSession().delete(order);
	}
	
	@Override
	public ToOrder findOrderByObject(ToOrder order){
		return (ToOrder) sessionFactory.getCurrentSession().get(ToOrder.class, order.getId());
	}
	
	@Override
	public boolean isOrderWithId(Long id){
		if (sessionFactory.getCurrentSession().get(ToOrder.class, id) != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public void connectOrderWithPosition(ToOrder order, Magazyn magazyn) {
		Magazyn position = (Magazyn) sessionFactory.getCurrentSession().get(Magazyn.class, magazyn.getId());
		position.getOrders().add(order);
	}
	
	@Override
	public List<ToOrder> getPositionOrders(Magazyn magazyn) {
		Magazyn position = (Magazyn) sessionFactory.getCurrentSession().get(Magazyn.class, magazyn.getId());
		return position.getOrders();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ToOrder> getAllOrders() {
		return sessionFactory.getCurrentSession().getNamedQuery("toorder.all").list();
	}
	
}
