package me.bqliang.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	//订单编号
	private String oid;
	//订单时间
	private LocalDateTime orderTime;
	//收货人姓名
	private String name;
	//收货地址
	private String address;
	//收货人联系方式
	private String telephone;
	//订单总价格
	private double total;
	//订单状态
	private int state = 0;
	//用户id
	private String uid;
	//订单评价
	private String assess;
	//购买的商品以及数量
	private List<OrderItem> orderItems=new ArrayList<OrderItem>(); 
	public Order(){}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getTotal() {
		total=0;
		for(OrderItem o:orderItems){
			total+=o.getSubtotal();
		}
		return total;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	public String getAssess() {
		return assess;
	}
	public void setAssess(String assess) {
		this.assess = assess;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", orderTime=" + orderTime + ", name=" + name + ", address=" + address
				+ ", telephone=" + telephone + ", total=" + total + ", state=" + state + ", uid=" + uid + ", assess="
				+ assess + ", orderItems=" + orderItems + "]";
	}
	
	
	

}
