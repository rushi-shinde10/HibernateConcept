package com.bmc.entity;

public class ProductModel {
	
	private Object productId;
	private Object productName;
	private Object productPrice;
	
	public ProductModel() {
	}

	
	
	public Object getProductId() {
		return productId;
	}

	public void setProductId(Object productId) {
		this.productId = productId;
	}

	public Object getProductName() {
		return productName;
	}

	public void setProductName(Object productName) {
		this.productName = productName;
	}

	public Object getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Object productPrice) {
		this.productPrice = productPrice;
	}

	
	
	
	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + "]";
	}
	
	

}
