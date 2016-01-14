package com.example.project_spring.service;

import com.example.project_spring.domain.Magazyn;

public interface ProductManager {
	
	void addNewProduct(Magazyn magazyn);
	Magazyn findProductById(Long id);
}
