package com.Veggie.Cart.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Veggie.Cart.Entity.AgentChat;

public interface MySqlRepo extends JpaRepository<AgentChat,String>{

}
