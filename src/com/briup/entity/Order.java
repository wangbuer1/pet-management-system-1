package com.briup.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;


/**
 * 订单实体
 * @author Administrator
 *
 */
@Entity
@Table(name="t_order")
public class Order {

	private int id; // 编号
	private String orderNo; // 订单号
	private User user; // 用户
	private Date createTime; // 创建时间
	private float cost; // 总金额
	private int status; // 状态 1 待审核 2 审核通过 3 卖家已发货 4 已收获
	//与商品订单实体一对多
	private List<OrderProduct>orderProductList=new ArrayList<OrderProduct>(); // 中间表集合
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
	//配置与User实体多对一关系
	@ManyToOne
	@JoinColumn(name="userId",updatable=false)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	//配置与商品订单实体见的一对多关系
	//	fetch=FetchType.EAGER
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade(value={CascadeType.SAVE_UPDATE})
	@JoinColumn(name="orderId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	

}
