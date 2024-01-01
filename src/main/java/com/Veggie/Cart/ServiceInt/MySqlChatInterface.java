package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.RedisChat;

public interface MySqlChatInterface {
	public ResponseEntity<String> syncAgentRedisWithDatabase();
	public ResponseEntity<String> syncCustomerRedisWithDatabase();
	public ResponseEntity<String> getAgentLatestMessage();
	public ResponseEntity<String> getCustomerLatestMessage();
}
