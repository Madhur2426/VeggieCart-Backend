package com.Veggie.Cart.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Veggie.Cart.Entity.Agent;

public interface AgentRepo extends JpaRepository<Agent,Integer>{
public boolean findByStatus(boolean status);
public boolean findByisChatting(boolean isChatting);
}
