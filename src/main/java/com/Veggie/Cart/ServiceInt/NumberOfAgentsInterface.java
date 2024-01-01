package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.NumberOfAgents;


public interface NumberOfAgentsInterface {
	public List<NumberOfAgents> fetchNumberOfAgents();
	public ResponseEntity<String> bootAgentMap(int numberOfAccounts);
}
