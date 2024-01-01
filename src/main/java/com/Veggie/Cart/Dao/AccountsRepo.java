package com.Veggie.Cart.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Veggie.Cart.Entity.Accounts;

public interface AccountsRepo extends JpaRepository<Accounts, String> {
}
