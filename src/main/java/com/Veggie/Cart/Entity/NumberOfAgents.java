package com.Veggie.Cart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class NumberOfAgents {

	@Id
	String email;
	@Column
	int NumberOfAgents;

	public NumberOfAgents() {
		super();
	}

	public NumberOfAgents(String email, int numberOfAgents) {
		super();
		this.email = email;
		NumberOfAgents = numberOfAgents;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNumberOfAgents() {
		return NumberOfAgents;
	}

	public void setNumberOfAgents(int numberOfAgents) {
		NumberOfAgents = numberOfAgents;

	}
}