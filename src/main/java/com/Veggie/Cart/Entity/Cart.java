package com.Veggie.Cart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column
	String name;
	String email;
	int price;
	int quantity;
	public Cart() {
		super();
	}
	 @Override
		public String toString() {
			return "Cart [id=" + id + ", name=" + name + ", email=" + email + ", price=" + price + ", quantity=" + quantity
					+ "]";
		}
	public Cart(Integer id, String name, String email, int price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.price = price;
		this.quantity = quantity;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}
