package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.Accounts;
import com.Veggie.Cart.Entity.Register;

public interface AccountsInterface {
	public List<Accounts> fetchNumberOfAccounts();
	public ResponseEntity<String> bootMap(int numberOfAccounts);
	public void updateMapWithNewRegistration(Register newRegister);
}
