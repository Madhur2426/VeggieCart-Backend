package com.Veggie.Cart.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class AgentChat {
	@Id
	String Sno;
	@Column
	int id;
	String messages;

	public AgentChat() {
		super();
	}

	public AgentChat(String sno, int id, String messages) {
		super();
		Sno = sno;
		this.id = id;
		this.messages = messages;
	}

	public String getSno() {
		return Sno;
	}

	public void setSno(String sno) {
		Sno = sno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}