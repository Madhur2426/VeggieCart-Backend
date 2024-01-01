package com.Veggie.Cart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Dao.MySqlCustomerRepo;
import com.Veggie.Cart.Dao.MySqlRepo;
import com.Veggie.Cart.Entity.AgentChat;
import com.Veggie.Cart.Entity.Customer;
import com.Veggie.Cart.Entity.CustomerChat;
import com.Veggie.Cart.Entity.HelperForRedis;
import com.Veggie.Cart.Entity.RedisChat;
import com.Veggie.Cart.ServiceInt.MySqlChatInterface;
import com.Veggie.Cart.ServiceInt.AgentRedisChatInterface;
import com.Veggie.Cart.ServiceInt.CustomerRedisChatInterface;
@Service
public class MySqlChatService  implements MySqlChatInterface {
	@Autowired
	AgentRedisChatInterface redischat;
	@Autowired
	MySqlRepo mysqlchat;
	@Autowired
	MySqlCustomerRepo customerchatrepo;
	@Autowired
	CustomerRedisChatInterface customerChat;
	@Autowired
	private RedisTemplate template;
	

	@Override
	public ResponseEntity<String> syncAgentRedisWithDatabase() {
		try {
			List<RedisChat> redisChat = this.redischat.findAll();
			for (RedisChat redisChat1 : redisChat) {
				AgentChat sqlChat = new AgentChat();
				sqlChat.setId(redisChat1.getId());
				sqlChat.setSno(redisChat1.getSno());
				sqlChat.setMessages(redisChat1.getMessages());
				this.mysqlchat.save(sqlChat);
				sqlChat.setId(0);
				sqlChat.setSno(null);
				sqlChat.setMessages(null);
			}
			for (RedisChat redisDelete : redisChat) {
				this.redischat.deleteChat(redisDelete.getSno());
			}
			return ResponseEntity.status(200).body("Data cleared from Redis and saved into MySql Database");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

	@Override
	public ResponseEntity<String> syncCustomerRedisWithDatabase() {
		try {
			List<CustomerChat> customer = this.customerChat.findAll();
			for (CustomerChat customerChat : customer) {
                Customer sqlChat = new Customer();
				sqlChat.setEmail(customerChat.getEmail());
				sqlChat.setSno(customerChat.getSno());
				sqlChat.setMessage(customerChat.getMessages());
				this.customerchatrepo.save(sqlChat);
				sqlChat.setEmail(null);
				sqlChat.setSno(null);
				sqlChat.setMessage(null);
			}
			for (CustomerChat redisDelete : customer) {
				this.customerChat.deleteChat(redisDelete.getSno());
			}
			return ResponseEntity.status(200).body("Data cleared from Redis and saved into MySql Database");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

	@Override
	public ResponseEntity<String> getAgentLatestMessage() {
		try {
		    RedisChat agent = this.redischat.findBySno(AgentRedisChatService.agentLatestUid);
            return ResponseEntity.status(200).body(agent.getMessages());
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

	@Override
	public ResponseEntity<String> getCustomerLatestMessage() {
		try {
			CustomerChat customerUID=new CustomerChat();
			CustomerChat customer = this.customerChat.findBySno(CustomerRedisChatService.customerLatestUid);
            return ResponseEntity.status(200).body(customer.getMessages());
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
}
