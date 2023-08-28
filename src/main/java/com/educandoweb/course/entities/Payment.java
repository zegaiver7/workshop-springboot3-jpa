package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity                      //Anotation que fala pra converter a entidade para tabela
@Table(name = "tb_payment")     //Anotation que fala pra mudar o nome da tabela para tb_payment
public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id                      //Anotation que fala que o campo abaixo é chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Anotation que fala que a chave é autoinc
	private Long id;
	private Instant moment;
	
	//Na ssociação com Order, um para um, payment é a classe dependente
	//Neste caso, a classe dependente para fazer a associação por mapeamento aqui, faz-se da seguinte forma...
	@OneToOne
	@MapsId
	@JsonIgnore  //Seguindo a logica, para não dar laço infinito com o Order
	private Order order;

	public Payment() {
	}

	public Payment(Long id, Instant moment, Order order) {
		super();
		this.id = id;
		this.moment = moment;
		this.order = order;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

	
	
}
