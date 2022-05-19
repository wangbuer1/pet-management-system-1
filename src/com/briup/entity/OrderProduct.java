package com.briup.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * ������Ʒ������
 * @author Administrator
 *
 */
@Entity
@Table(name="t_order_product")
public class OrderProduct {
	private int id; // ����
	private int num; // ��Ʒ����
	//�붩�����һ
	private Order order; // ����
	//����Ʒʵ����һ
	private Product product; // ��Ʒ
	//����
	//�����������ɲ���generator(����native���أ�
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
	//�����붩��ʵ��Ķ��һ��ϵ
	@ManyToOne
	@JoinColumn(name="orderId")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	//��������Ʒʵ��Ķ��һ��ϵ
	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
