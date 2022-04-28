package com.spring.boot.micro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productsService")
public class ProductsService {
	@Autowired
	private ProductsRepository productsRepository;
	
	public Products getItem(int id) {
		return this.productsRepository.getOne(id);
	}

	public List<Products> getAll() {
		return this.productsRepository.findAll();
	}

	public void updateItem(int id, Products item) {
	   item.setId(id);
	   this.productsRepository.save(item);
	}

	public void createItem(Products item) {
		this.productsRepository.save(item);
	}

	public void deleteItem(int id) {
		this.productsRepository.deleteById(id);
	}
}
