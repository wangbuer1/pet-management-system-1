package com.briup.entity;

/**
 * 购物车条目实体
 * @author Administrator
 *
 */
public class ShoppingCartItem {

	private int id; // 编号
	private Product product; // 商品
	private int count; // 数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	
	
}
