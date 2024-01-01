package com.Veggie.Cart.ServiceInt;



import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.Vegetables;

public interface VegetableInterface {
	public ResponseEntity<String> saveVegetable(List<Vegetables> vegetableData);
    public ResponseEntity<List<Vegetables>> searchVegetble(String name);
	public List<Vegetables> getVegetable();
}
