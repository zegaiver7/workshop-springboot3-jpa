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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity                      //Anotation que fala pra converter a entidade para tabela
@Table(name = "tb_product")     //Anotation que fala pra mudar o nome da tabela para tb_user
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id                      //Anotation que fala que o campo abaixo é chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)  //Anotation que fala que a chave é autoinc
	private Long id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	@ManyToMany  //Para implementar muitos para muitos com tabela de associação
	@JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"),inverseJoinColumns = @JoinColumn(name = "category_id")) //Indica qual vai ser o nome da tabela e quais as chaves para associar produto e categoria
	//e agora na classe category vamos colocar uma referencia para o mapeamento que acabamos de fazer
	private Set<Category> categories = new HashSet<>(); //Já instanciando aqui, não sera incluida no construtor com argumentos

	//Anotation para mapear que um pedido(Order) terá vários items (OrderItem) ...
	//mapeado por (OrderItemPK id) de OrderItem que tem a chave order_id e product_id da classe OrderItemPK...e neste caso usamos id.order que busca order_id
	@OneToMany(mappedBy = "id.product")
	private Set<OrderItem> items = new HashSet<>();  //Atributo que será usado para trazer os ítems do pedido (Order)
	
	public Product() {
	}

	public Product(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>(); //aqui um set vazio que será alimentado no laço
		//e vamos percorrer cada objeto do tipo "OrderItem" x contido na minha lista de "items" que foram associadas ao meu produto
        for (OrderItem x : items) {
        	set.add(x.getOrder());   //Adiciono o objeto Order de x, pela função getOrder
        }
		return set;                 //E retorno como resultado esta coleção
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}	
}
