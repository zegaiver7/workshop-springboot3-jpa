package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.repositories.OrderRepository;

// ... @Component  //Anotation que registra a classe como um componente do spring, e ele poderá ser injetado automaticamente como dependência
// ... @Repository //Anotation que registra um repositório do spring...
@Service           //Anotation que registra um serviço do spring...
public class OrderService {

	@Autowired //Anotation para que o spring faça a injeção de dependência abaixo, de forma transparente ao programador
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}
