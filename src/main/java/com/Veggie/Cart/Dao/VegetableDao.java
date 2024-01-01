package com.Veggie.Cart.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Veggie.Cart.Entity.Vegetables;

public interface VegetableDao extends JpaRepository<Vegetables, Integer>{

	List<Vegetables> findByName(String name);

}
