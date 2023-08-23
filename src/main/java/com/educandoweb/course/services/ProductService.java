package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.repositories.ProductRepository;

// ... @Component  //Anotation que registra a classe como um componente do spring, e ele poderá ser injetado automaticamente como dependência
// ... @Repository //Anotation que registra um repositório do spring...
@Service           //Anotation que registra um serviço do spring...
public class ProductService {

	@Autowired //Anotation para que o spring faça a injeção de dependência abaixo, de forma transparente ao programador
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get();
	}
}
