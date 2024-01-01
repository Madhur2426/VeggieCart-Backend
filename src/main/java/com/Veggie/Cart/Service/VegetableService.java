package com.Veggie.Cart.Service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Dao.VegetableDao;
import com.Veggie.Cart.Entity.Vegetables;
import com.Veggie.Cart.ServiceInt.VegetableInterface;

@Service
public class VegetableService implements VegetableInterface{
	@Autowired
	VegetableDao vegetable;

	@Override
	public ResponseEntity<String> saveVegetable(List<Vegetables> vegetableData) {
		this.vegetable.saveAll(vegetableData);
	    return ResponseEntity.status(HttpStatus.OK).body("Data saved successfully.");
		
	}

	@Override
	public List<Vegetables> getVegetable() {
		return this.vegetable.findAll();
	}

	@Override
	public ResponseEntity<List<Vegetables>> searchVegetble(String name) {
		try {
			List<Vegetables> searchData=new ArrayList<>();
			searchData=this.vegetable.findByName(name);
			if(searchData.size()!=0)
			return ResponseEntity.status(HttpStatus.OK).body(searchData);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
	}
	

	
}
