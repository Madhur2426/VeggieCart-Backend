package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.CustomerChat;
import com.Veggie.Cart.Entity.RedisChat;

public interface CustomerRedisChatInterface {
		public ResponseEntity<String> save(CustomerChat chat);

		public List<CustomerChat> findAll();
		
		public CustomerChat findBySno(String sno);

		public CustomerChat findChatById(int id);

		public String deleteChat(String id);
		public String getAgentLatestMessage();
}
