package com.Veggie.Cart.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Veggie.Cart.Entity.Issue;

public interface IssueDao extends JpaRepository<Issue,Integer>{
	List<Issue> findByEmail(String email);
}