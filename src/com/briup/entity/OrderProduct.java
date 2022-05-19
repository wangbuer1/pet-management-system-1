package com.briup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 订单商品关联表
 * @author Administrator
 *
 */
@Entity
@Table(name="t_order_product")
public class OrderProduct {
	private int id; // 主键
	private int num; // 商品数量
	//与订单多对一
	private Order order; // 订单
	//与商品实体多对一
	private Product product; // 商品
	//主键
	//设置主键生成策略generator(引用native本地）
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	//配置与订单实体的多对一关系
	@ManyToOne
	@JoinColumn(name="orderId")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	//配置与商品实体的多对一关系
	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
