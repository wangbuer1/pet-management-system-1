package com.briup.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品实体
 * @author Administrator
 *
 */
@Entity
@Table(name="t_product")
public class Product {

	private int id; // 编号
	private String name; // 商品名称
	private int price; // 价格
	private int stock; // 库存
	private String proPic; // 商品图片
	private String description; // 描述
	//与商品小类实体多对一
	private ProductSmallType smallType; // 所属小类
	//与商品大类实体多对一
	private ProductBigType bigType; // 所属大类
	//与商品订单实体一对多
	private List<OrderProduct>orderProductList=new ArrayList<OrderProduct>(); // 中间表集合
	private int hot; // 是否热卖
	private Date hotTime; // 设置热卖时间
	private int specialPrice; // 是否特价
	private Date specialPriceTime; // 设置特价时间
	
	//主键
	//设置主键生成策略generator(引用native本地
	@Id
	@GeneratedValue(generator="_native")
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getProPic() {
		return proPic;
	}
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}
	
	@Column(length=2000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	//配置与商品小类多对一
	@ManyToOne
	//设置外键
	@JoinColumn(name="smallTypeId")
	public ProductSmallType getSmallType() {
		return smallType;
	}
	public void setSmallType(ProductSmallType smallType) {
		this.smallType = smallType;
	}
	
	//配置与商品大类多对一
	@ManyToOne
	//设置外键
	@JoinColumn(name="bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	//配置与商品订单实体的一对多关系
	@OneToMany
	//设置外键
	@JoinColumn(name="productId")
	public List<OrderProduct> getOrderProductList() {
		return orderProductList;
	}
	public void setOrderProductList(List<OrderProduct> orderProductList) {
		this.orderProductList = orderProductList;
	}
	public Date getHotTime() {
		return hotTime;
	}
	public void setHotTime(Date hotTime) {
		this.hotTime = hotTime;
	}
	
	public Date getSpecialPriceTime() {
		return specialPriceTime;
	}
	public void setSpecialPriceTime(Date specialPriceTime) {
		this.specialPriceTime = specialPriceTime;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}
	
	
	
	
}
