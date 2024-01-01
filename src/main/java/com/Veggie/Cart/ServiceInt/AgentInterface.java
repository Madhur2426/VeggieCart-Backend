package com.Veggie.Cart.ServiceInt;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.Agent;

public interface AgentInterface {
 public ResponseEntity<String> saveAgentDetails(Agent agent);
 public ResponseEntity<Agent> isAgentAvailaible();
 public ResponseEntity<Agent> chatRejectedByAgent(int id, boolean rejected);
 public ResponseEntity<String> updateAgentStatusOnLogin(Agent agent);
 public ResponseEntity<String> updateAgentStatusOnLogout(int id);
 public ResponseEntity<Integer> getLatestAgentId();
 public ResponseEntity<Agent> getProfile(int id);
 public ResponseEntity<Boolean> updateAgentStatusOnChatStart(int id);
 public ResponseEntity<Boolean> checkAssignedChats(int id);
public ResponseEntity<String> updateAgentChatting(int id);
public ResponseEntity<Boolean> checkChatEndedorLoggedOutByAgent(int id);
public ResponseEntity<Boolean> getAgentChatting(int id);
 }
