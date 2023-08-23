package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Product;
import com.educandoweb.course.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired //Anotation para que o spring faça a injeção de dependência abaixo, de forma transparente ao programador
	private ProductService service;
	
	//Implementação de busca de todos os usuários
	@GetMapping  //Anotation indica que será uma requisição do tipo get
	public ResponseEntity<List<Product>> findAll(){
		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//Implementação de busca de usuário por id
	@GetMapping(value = "/{id}") //Indica que a requisição do tipo get terã um parâmetro
	public ResponseEntity<Product> findById(@PathVariable Long id){ //Anotation(@PathVariable) serve para receber o parâmetro "/{id}" dentro do endpoit do controlador rest, e que o spring aceite este id
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
