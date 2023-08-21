package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity                      //Anotation que fala pra converter a entidade para tabela
@Table(name = "tb_order")     //Anotation que fala pra mudar o nome da tabela para tb_order
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id                      //Anotation que fala que o campo abaixo é chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Anotation que fala que a chave é autoinc
	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //Anotation para garantir que a coluna seja mostrada no formato ISO 8601
	private Instant moment;
	
	//private OrderStatus orderStatus; //Estava desta maneira antes do tratamento no enumerador OrderStatus
	private Integer orderStatus;
	
	//@JsonIgnore pode colocar aqui...e tirar do user, se quiser traser clientes e todos seus pedidos
	@ManyToOne
	@JoinColumn(name = "client_id")
	private User client;  //Aqui dentro do pedido(Order) nos temos o usuário(client)

	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		//this.orderStatus = orderStatus;  estava assim antes do tratamento no enum OrderStatus
		setOrderStatus(orderStatus);   //e ficou assim
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	
	public OrderStatus getOrderStatus() {
		//return orderStatus;   estava assim antes do tratamento no enum OrderStatus
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		//this.orderStatus = orderStatus;   estava assim antes do tratamento no enum OrderStatus
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}	
}
