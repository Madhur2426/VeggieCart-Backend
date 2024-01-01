package com.Veggie.Cart.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Veggie.Cart.Entity.Customer;

public interface MySqlCustomerRepo extends JpaRepository<Customer,String>{

}
