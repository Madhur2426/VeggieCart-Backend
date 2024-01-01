package com.Veggie.Cart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Entity.CustomerChat;
import com.Veggie.Cart.ServiceInt.CustomerRedisChatInterface;

@Service
public class CustomerRedisChatService implements CustomerRedisChatInterface {

	static String customerLatestUid="";
	public static final String HASH_KEY = "Customer";
	@Autowired
	private RedisTemplate template;

	@Override
	public ResponseEntity<String> save(CustomerChat chat) {
            try {
	        template.opsForHash().put(HASH_KEY,chat.getSno(),chat);
	        this.customerLatestUid=chat.getSno();
	        return ResponseEntity.status(200).body("Customer chat saved in Redis");
          }
            catch(Exception e) {
            	return ResponseEntity.status(500).body("Internal Server Error");
            }
	}

	@Override
	public List<CustomerChat> findAll() {
        return template.opsForHash().values(HASH_KEY);
	}

	@Override
	public CustomerChat findChatById(int id) {
        return (CustomerChat) template.opsForHash().get(HASH_KEY,id);
	}

	@Override
	public String deleteChat(String sno) {
    	try {
            template.opsForHash().delete(HASH_KEY,sno);
           return "Chat removed !!";
       }
       	catch(Exception e) {
       		return "!!!!Error!!!";
       	}
	}

	@Override
	public CustomerChat findBySno(String sno) {
        return (CustomerChat) template.opsForHash().get(HASH_KEY,sno);
	}

	@Override
	public String getAgentLatestMessage() {
		return customerLatestUid;
	}

}
