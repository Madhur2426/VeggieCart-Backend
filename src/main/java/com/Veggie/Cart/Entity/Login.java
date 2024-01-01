package com.Veggie.Cart.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class Login {
	
	String email;
	String password;

	public Login() {
		super();
	}

	@Override
	public String toString() {
		return "Login [email=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Login(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

}
