package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity                       //Anotation que fala pra converter a entidade para tabela
@Table(name = "tb_category")  //Anotation que fala pra mudar o nome da tabela para tb_category
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id                      //Anotation que fala que o campo abaixo é chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Anotation que fala que a chave é autoinc
	private Long id;
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")  //referencia para o mapeamento da associação muitos para muitos em produto
	private Set<Product> products = new HashSet<>();
	
	public Category() {
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Product> getProducts() {
		return products;
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
		Category other = (Category) obj;
		return Objects.equals(id, other.id);
	}
}
