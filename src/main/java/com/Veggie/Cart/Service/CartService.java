package com.Veggie.Cart.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Veggie.Cart.Dao.CartRepo;
import com.Veggie.Cart.Entity.Cart;
import com.Veggie.Cart.ServiceInt.CartInterface;

import jakarta.transaction.Transactional;
@Service
public class CartService implements CartInterface{

	@Autowired
	CartRepo cart;
	String emailOfLoggedInUser="";
	@Override
	public ResponseEntity<String> addToCart(Cart cart) {
 	try {
 		this.cart.save(cart);
 		return ResponseEntity.status(HttpStatus.OK).body("Data Added In Cart");
 	}
 	catch(Exception e) {
 		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data Cannot be in Cart");
 	}
	}
	@Override
	public ResponseEntity<List<Cart>> getCart(String emailOfLoggedInUser) {
		try {
	        List<Cart> cartList = cart.findByEmail(emailOfLoggedInUser);
	        if(cartList.size()==0)
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        return ResponseEntity.status(HttpStatus.OK).body(cartList);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	 @Override
	    public ResponseEntity<String> deleteCartItem(Cart item) {
		 try {
			   cart.delete(item);
			   return ResponseEntity.status(HttpStatus.OK).body("Item deleted from cart");
			  
			} catch (Exception e) {
			    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting item from cart");
			}

	    }
	
	
	
}
