package com.Veggie.Cart.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Dao.AgentRepo;
import com.Veggie.Cart.Dao.NumberOfAgentsRepo;
import com.Veggie.Cart.Entity.Agent;
import com.Veggie.Cart.Entity.NumberOfAgents;
import com.Veggie.Cart.ServiceInt.AgentInterface;
import com.Veggie.Cart.ServiceInt.NumberOfAgentsInterface;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

@Service
public class AgentService implements AgentInterface {
	@Autowired
	AgentRepo agentRepo;
	@Autowired
	NumberOfAgentsRepo agentCount;
    @Autowired
    NumberOfAgentsInterface agents;

	static HashMap<Integer, Boolean> rejectedByAgent = new HashMap<>();
	int numberOfAgents=0;

	@Override
	public ResponseEntity<String> saveAgentDetails(Agent agent) {
		try {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String encryptedPassword = bcrypt.encode(agent.getPassword());
			agent.setPassword(encryptedPassword);
			this.agentRepo.save(agent);
			BootHashMapOnStartup.mapAgentUsernamePassword.put(agent.getId(), encryptedPassword);
			List<NumberOfAgents> list=agents.fetchNumberOfAgents();
			if(list.size()==0)
			{
				numberOfAgents+=1;
			}
			else {
				numberOfAgents=1+list.get(0).getNumberOfAgents();
			}
			NumberOfAgents totalAgents=new NumberOfAgents("Developer@VeggiewCart.com",numberOfAgents);
			this.agentCount.save(totalAgents);
			return ResponseEntity.status(200).body("Agent Details Saved");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error ocurred while saving Agent data");
		}
	}

	public List<Agent> fetchAgents() {
		return this.agentRepo.findAll();
	}

	@Override
	public ResponseEntity<Agent> isAgentAvailaible() {
		Agent noAgent=new Agent(-9999,"null",false,"null",false);
		try {
			List<Agent> agentData = this.fetchAgents();
			if (agentData != null) {
				for (Agent agent : agentData) {
					if ((agent.getStatus())==true
							&&
						(agent.isChatting())==false) {
						return ResponseEntity.status(200).body(agent);
					}
				}
				return ResponseEntity.status(503)
						.body(noAgent);
			}
			return ResponseEntity.status(503).body(noAgent);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(noAgent);
		}
	}

	@Override
	public ResponseEntity<Agent> chatRejectedByAgent(int id, boolean rejected) {
		rejectedByAgent.put(id, rejected);
		Agent noAgent=new Agent(-9999,"null",false,"null",false);
		try {
			List<Agent> agentData = this.fetchAgents();
			if (agentData != null) {
				for (Agent agent : agentData) {
					if (!rejectedByAgent.containsKey(agent.getId())) {
						if ((agent.getStatus())==true) {
							return ResponseEntity.status(200).body(agent);
						}
					}
				}
				return ResponseEntity.status(503)
						.body(noAgent);
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(noAgent);
		}
		return ResponseEntity.status(500).body(noAgent);
	}

	@Override
	public ResponseEntity<String> updateAgentStatusOnLogin(Agent agent) {
		try {
			Agent loggedInAgent=this.agentRepo.findById(agent.getId()).get();
			agent.setStatus(true);
			agent.setChatting(false);
			agent.setName(loggedInAgent.getName());
			this.agentRepo.save(agent);
			return ResponseEntity.status(200).body("Agent status Updated to True");
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

	@Override
	public ResponseEntity<String> updateAgentStatusOnLogout(int id) {
		try {
			Agent loggedInAgent=this.agentRepo.findById(id).get();
			loggedInAgent.setStatus(false);
			loggedInAgent.setChatting(false);
			loggedInAgent.setName(loggedInAgent.getName());
			this.agentRepo.save(loggedInAgent);
			return ResponseEntity.status(200).body("Agent status Updated to False");
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}

	@Override
	public ResponseEntity<Integer> getLatestAgentId() {
	   List<Agent> agent=agentRepo.findAll();
	   if(agent.size()>0)
		    return ResponseEntity.status(200).body(agent.get(agent.size()-1).getId());
	   return ResponseEntity.status(500).body(-99999);
	}

	@Override
	public ResponseEntity<Agent> getProfile(int id) {
	   Optional<Agent> agentDetails=this.agentRepo.findById(id);
	   Agent profile=agentDetails.get();
	   return ResponseEntity.status(200).body(profile);
	}

	@Override
	public ResponseEntity<Boolean> updateAgentStatusOnChatStart(int id) {
		try {
			Optional<Agent> agent=this.agentRepo.findById(id);
			Agent updateAgent=agent.get();
			updateAgent.setChatting(true);
			this.agentRepo.save(updateAgent);
			return ResponseEntity.status(200).body(true);
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(false);
		}
	}

	@Override
	public ResponseEntity<Boolean> checkAssignedChats(int id) {
		Optional<Agent> agent=this.agentRepo.findById(id);
		Agent details=agent.get();
		if(details.isChatting()==true)
		return ResponseEntity.status(200).body(true);
		return ResponseEntity.status(500).body(false);
	}

	@Override
	public ResponseEntity<String> updateAgentChatting(int id) {
		try {
		      Agent agent=this.agentRepo.findById(id).get();
		      if(agent!=null) {
		    	  agent.setChatting(false);
		    	  this.agentRepo.save(agent);
		    	  return ResponseEntity.status(200).body("Status Updated");
		      }
		}
		      catch(Exception e) {
		    	  return ResponseEntity.status(500).body("Error Occured while updating chatting");
		      }
  	  return ResponseEntity.status(500).body("Error Occured while updating chatting");
		}

	@Override
	public ResponseEntity<Boolean> checkChatEndedorLoggedOutByAgent(int id) {
		Agent agent=this.agentRepo.findById(id).get();
		if(agent.isChatting()==true&&agent.getStatus()==true)
			 return ResponseEntity.status(200).body(true);
		return ResponseEntity.status(500).body(false);
	}

	@Override
	public ResponseEntity<Boolean> getAgentChatting(int id) {
		  Agent agent=this.agentRepo.findById(id).get();
		  if(agent.isChatting()==true)
			   return ResponseEntity.status(200).body(true);
		  return ResponseEntity.status(500).body(false);
	}
	}