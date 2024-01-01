package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.RedisChat;

public interface AgentRedisChatInterface {
	public ResponseEntity<String> save(RedisChat chat);

	public List<RedisChat> findAll();

	public RedisChat findChatById(int id);
	public RedisChat findBySno(String sno);
    public String getAgentLatestMessage();

	public String deleteChat(String id);

}