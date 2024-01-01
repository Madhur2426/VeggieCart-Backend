package com.Veggie.Cart.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@RedisHash("Chat")
public class RedisChat extends HelperForRedis implements Serializable{

	@Id
	String Sno;
	@Column
	int id;
	@Column
	String messages;


	 public void generateKey() {
	        this.Sno = (UUID.randomUUID().toString());
	        HelperForRedis.agentUid=this.Sno;
	    }
	public RedisChat() {
		super();
		generateKey();
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
	public String getSno() {
		return Sno;
	}
	
	
	
	
}