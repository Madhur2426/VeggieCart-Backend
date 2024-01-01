package com.Veggie.Cart.Entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class Agent {
	@Id
	int id;
	@Column
	String name;
	boolean status;
    String password;
	boolean isChatting;
	public Agent(int id, String name, boolean status, String password, boolean isChatting) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.password = password;
		this.isChatting = isChatting;
	}
	public Agent() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isChatting() {
		return isChatting;
	}
	public void setChatting(boolean isChatting) {
		this.isChatting = isChatting;
	}
	

	
}
