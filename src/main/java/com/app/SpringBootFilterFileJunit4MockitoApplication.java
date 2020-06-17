package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.service.IProductService;

@SpringBootApplication
public class SpringBootFilterFileJunit4MockitoApplication implements CommandLineRunner{

	private IProductService productService;
	
	public SpringBootFilterFileJunit4MockitoApplication(IProductService productService) {
		this.productService = productService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFilterFileJunit4MockitoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String filterFile = productService.filterFile("D:\\files\\product.txt");
		System.out.println(filterFile);
	}
}
