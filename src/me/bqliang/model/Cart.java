package me.bqliang.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cart {
	private Map<String,CartItem> cartItems=new HashMap<String,CartItem>();
	private double total;
	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public double getTotal() {
		total=0;
		Set<Entry<String, CartItem>> set = cartItems.entrySet();
		for(Entry<String, CartItem> c:set){
			total+=c.getValue().getSubTotal();
		}
		return total;
	}
	@Override
	public String toString() {
		return "Cart [cartItems=" + cartItems + ", total=" + total + "]";
	}
	
	
	
	
}
