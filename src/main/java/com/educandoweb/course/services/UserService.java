package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

// ... @Component  //Anotation que registra a classe como um componente do spring, e ele poderá ser injetado automaticamente como dependência
// ... @Repository //Anotation que registra um repositório do spring...
@Service           //Anotation que registra um serviço do spring...
public class UserService {

	@Autowired //Anotation para que o spring faça a injeção de dependência abaixo, de forma transparente ao programador
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		//return obj.get();  ...antes do tratamento de excessões
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //Tente fazer o get, senão trate com a excessão personalizada
	}
	
	//Aqui no serviçõ, vai ficar o metodo análogo ao insert que fariamos no banco de dados
	public User insert(User obj) {
		return repository.save(obj); //Este metodo já retorna o objeto salvo (neste caso a variavel "obj" do tipo User que foi passada como parâmetro do metodo
	}
	
	public void delete(Long id) {
		try{
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		//User entity = repository.getOne(id); //Não existe mais agora é getReferenceById.....
		User entity = repository.getReferenceById(id); //Vai instanciar um objato usuário monitorado pelo JPA para trabalhar com ele, mas ainda não vai no banco de dados
		updateData(entity, obj);  //chamada de função
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {		
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());		
	}
	
}
