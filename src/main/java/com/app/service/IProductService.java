package com.app.service;

import java.util.List;

import com.app.model.Product;

public interface IProductService {
	
	public String filterFile(String fileLoc);
	
	List<Product> saveAll(List<Product> products);
}
