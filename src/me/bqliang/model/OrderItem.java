package me.bqliang.model;

public class OrderItem {
	//id
	private String itemId;
	//购买的商品
	private Product product;
	//购买数量
	private int count;
	//小计
	private double subtotal;
	//订单号
	private String oid;
	
	public OrderItem(){}

	
	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSubtotal() {
		return product.getShop_price()*count;
	}

	@Override
	public String toString() {
		return "OrderItem [itemId=" + itemId + ", product=" + product + ", count=" + count + ", subtotal=" + subtotal
				+ "]";
	}
	
	

}
