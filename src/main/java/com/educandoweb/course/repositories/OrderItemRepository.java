package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;

//@Repository ...poderia colocar, mas não é necessário porque interface "UserRepository" já está herdando do "JpaRepository", o qual já está registrado como componente do spring
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
