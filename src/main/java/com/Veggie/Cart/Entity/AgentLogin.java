package com.Veggie.Cart.Entity;

public class AgentLogin {
	int id;
	String password;

	public AgentLogin() {
		super();
	}

	@Override
	public String toString() {
		return "AgentLogin [id=" + id + ", password=" + password + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
