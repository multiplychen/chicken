package com.tien.chicken.domain;

public class OrderDetail {
	
	private Order order;
	private Integer unitQty;
	private Product product;
	private Float quantity;
	private Float quantityTw;
	private Integer unitPrice;
	private Integer amount;
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public Float getQuantityTw() {
		return quantityTw;
	}
	public void setQuantityTw(Float quantityTw) {
		this.quantityTw = quantityTw;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public Integer getUnitQty() {
		return unitQty;
	}
	public void setUnitQty(Integer unitQty) {
		this.unitQty = unitQty;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
}
