package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired //Anotation para que o spring faça a injeção de dependência abaixo, de forma transparente ao programador
	private UserService service;
	
	//Implementação de busca de todos os usuários
	@GetMapping  //Anotation indica que será uma requisição do tipo get
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	//Implementação de busca de usuário por id
	@GetMapping(value = "/{id}") //Indica que a requisição do tipo get terã um parâmetro
	public ResponseEntity<User> findById(@PathVariable Long id){ //Anotation(@PathVariable) serve para receber o parâmetro "/{id}" dentro do endpoit do controlador rest, e que o spring aceite este id
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	//Quando vai inserir usamos o metodo post
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){ //Anotation "@RequestBody" que informa que o parametro obj vai chegar em modo json e será desserializado para o tipo User do meu java
		obj = service.insert(obj);   //Aqui chamo o serviço de inserção que foi colocado em "UserService"
		//return ResponseEntity.ok().body(obj); //Desta maneira dá o resultado 200 created, e queremos o 201
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
}
