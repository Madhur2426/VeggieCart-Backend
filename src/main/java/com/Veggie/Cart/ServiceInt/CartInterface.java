package com.Veggie.Cart.ServiceInt;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Veggie.Cart.Entity.Cart;

public interface CartInterface {
	public ResponseEntity<String> addToCart(Cart cart);
	public ResponseEntity<List<Cart>> getCart(String emailOfLoggedInUser);
    public ResponseEntity<String> deleteCartItem(Cart item);
}
