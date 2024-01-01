package com.Veggie.Cart.Entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
@RedisHash("Customer")
public class CustomerChat extends HelperForRedis implements Serializable{
	@Id
	String Sno;
	@Column
	String email;
	@Column
	String messages;

	public void generateKey() {
		this.Sno = (UUID.randomUUID().toString());
		HelperForRedis.customerUid=this.Sno;
	}


	public CustomerChat() {
		super();
		generateKey();
	}

	public CustomerChat(String sno, String email, String messages) {
		super();
		Sno = sno;
		this.email = email;
		this.messages = messages;
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

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

}
