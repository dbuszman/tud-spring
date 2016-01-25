package com.example.project_spring.service;

import java.util.List;

import com.example.project_spring.domain.Magazyn;

public interface ProductManager {
	
	void addNewPosition(Magazyn magazyn);
	Magazyn findPositionById(Long id);
	boolean isPositionWithId(Long id);
	void removePosition(Magazyn magazyn);
	List<Magazyn> getAllPositions();
}
