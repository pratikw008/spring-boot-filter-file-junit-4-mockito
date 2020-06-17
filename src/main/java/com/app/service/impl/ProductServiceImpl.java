package com.app.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.custom.exception.NoProductDataFoundException;
import com.app.model.Product;
import com.app.repository.ProductRepository;
import com.app.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {
	
	private ProductRepository productRepo;

	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public String filterFile(String fileLoc)  {
		if(fileLoc != null) {
			
			//Read File
			List<Product> filteredProducts = null;
			try {
				filteredProducts = Files.lines(Paths.get(fileLoc))
					 .map(this::splitLineToProduct)
					 .filter(product -> product.getPrice() >= 5000)
					 .collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			this.saveAll(filteredProducts);
			
			//Convert List<Product> to comma-separated List<String> or lines
			List<String> lines = this.convertListOfProductToCommaSepLines(filteredProducts);
						
			
			//Write To File
			Path path = null;
			try {
				path = Files.write(Paths.get("D:\\files\\product-2.txt"), lines, StandardOpenOption.CREATE);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return path.toUri().toString();
		}
		else {
			return "Invalid File Location";
		}
	}
	
	private Product splitLineToProduct(String str){
		String[] splitedString = str.split(",");
		return Product.builder().id(Integer.parseInt(splitedString[0]))
						 .name(splitedString[1])
						 .price(Double.parseDouble(splitedString[2]))
						 .build();
	}
	
	private List<String> convertListOfProductToCommaSepLines(List<Product> filteredProducts) {
		return filteredProducts.stream()
				.map(product -> String.join(",", Arrays.asList(String.valueOf(product.getId()),product.getName(),String.valueOf(product.getPrice()))))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Product> saveAll(List<Product> products) {
		if(!products.isEmpty()) {
			return productRepo.saveAll(products);
		}
		else {
			throw new NoProductDataFoundException("Data is Not Found");
		}
	}
}
