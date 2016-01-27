package com.example.project_spring.service;

import java.util.List;

import com.example.project_spring.domain.Magazyn;
import com.example.project_spring.domain.ToOrder;

public interface ProductManager {
	
	void addNewPosition(Magazyn magazyn);
	void editPosition(Magazyn magazyn);
	void removePosition(Magazyn magazyn);
	Magazyn findPositionByObject(Magazyn magazyn);
	boolean isPositionWithId(Long id);
	List<Magazyn> getAllPositions();
	List<Magazyn> findPositionByName(String name);
	void addNewOrder(ToOrder order);
	List<ToOrder> getAllOrders();
	void removeOrder(ToOrder order);
}
