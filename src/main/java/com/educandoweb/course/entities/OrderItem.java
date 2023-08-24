package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.course.entities.pk.OrderItemPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity                         //Será uma tabela no banco
@Table(name = "tb_order_item")  //nome da tabela     
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;

	@EmbeddedId    //Esta é uma chave primária que não tem autoinc, composta de dois  (product_id e order_id) no banco, e aqui ela é representada por uma classe auxiliar
	private OrderItemPK id;
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}

	public OrderItem(Order order, Product product, Integer quantity, Double price) {  //Este construtor está recebendo extras de "Order order, Product product" pois serão usados para instanciar dentro do construtor, o "id"
		super();
		id.setOrder(order);     //tambem deverão ser gerados gets e sets
		id.setProduct(product); //tambem deverão ser gerados gets e sets
		this.quantity = quantity;
		this.price = price;
	}
	
	public Order getOrder() {                 // Aqui, colocamos manualmente os gets e sets de
		return id.getOrder();                 // Order...
	}                                         //
	                                          //
	public void setOrder(Order order) {       //
		id.setOrder(order);                   // ...e Product
	}                                         //
	                                          //
	public Product getProduct() {             // 
		return id.getProduct();               //
	}                                         //
	                                          // para que sejam chamados na hora do construtor
	public void setProduct(Product product) { //
		id.setProduct(product);               // ou devolvidos como resultado
	}                                         //
                                              
	public Integer getQuantity() {            
		return quantity;                  
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}	
}
