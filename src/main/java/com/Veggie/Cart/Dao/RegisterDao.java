package com.Veggie.Cart.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Veggie.Cart.Entity.Register;

public interface RegisterDao extends JpaRepository<Register,String>{
	  @Query("SELECT r.otp FROM Register r WHERE r.email = ?1")
	    int findOtpByEmail(String email);
}
