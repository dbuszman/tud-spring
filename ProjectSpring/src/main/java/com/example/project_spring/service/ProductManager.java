package com.example.project_spring.service;

import java.util.List;

import com.example.project_spring.domain.Magazyn;

public interface ProductManager {
	
	void addNewPosition(Magazyn magazyn);
	void editPosition(Magazyn magazyn);
	Magazyn findPositionByObject(Magazyn magazyn);
	boolean isPositionWithId(Long id);
	void removePosition(Magazyn magazyn);
	List<Magazyn> getAllPositions();
}
