package com.Veggie.Cart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	String Sno;
	@Column
	String email;
	@Column
	String messages;

	public Customer() {
		super();
	}

	public Customer(String sno, String email, String message) {
		super();
		Sno = sno;
		this.email = email;
		this.messages = message;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return messages;
	}

	public void setMessage(String message) {
		this.messages = message;
	}

}
