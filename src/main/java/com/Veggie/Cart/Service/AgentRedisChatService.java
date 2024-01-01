package com.Veggie.Cart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Entity.RedisChat;
import com.Veggie.Cart.ServiceInt.AgentRedisChatInterface;
@Service
public class AgentRedisChatService implements AgentRedisChatInterface{
    public static final String HASH_KEY = "Chat";

    static String agentLatestUid="";
    @Autowired
    private RedisTemplate template;
 
    @Override
    public ResponseEntity<String> save(RedisChat Chat){
    	try {
        template.opsForHash().put(HASH_KEY,Chat.getSno(),Chat);
        this.agentLatestUid=Chat.getSno();
        return ResponseEntity.status(200).body("Chat Saved in Redis");
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(500).body("Internal server error");
    	}
    }

    public List<RedisChat> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public RedisChat findChatById(int id){
        return (RedisChat) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteChat(String sno){
    	try {
         template.opsForHash().delete(HASH_KEY,sno);
        return "Chat removed !!";
    }
    	catch(Exception e) {
    		return "!!!!Error!!!";
    	}
}

	@Override
	public RedisChat findBySno(String sno) {
        return (RedisChat) template.opsForHash().get(HASH_KEY,sno);

	}

	@Override
	public String getAgentLatestMessage() {
	 return agentLatestUid;
	}

}

