package com.Veggie.Cart.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {
	@Id
	String Email;
	
	@Column
	int numberOfAccounts;

	public Accounts() {
		super();
	}

	public Accounts(String email, int numberOfAccounts) {
		super();
		Email = email;
		this.numberOfAccounts = numberOfAccounts;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public int getNumberOfAccounts() {
		return numberOfAccounts;
	}

	public void setNumberOfAccounts(int numberOfAccounts) {
		this.numberOfAccounts = numberOfAccounts;
	}

	@Override
	public String toString() {
		return "Accounts [Email=" + Email + ", numberOfAccounts=" + numberOfAccounts + "]";
	}

	

}
