package com.briup.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;


/**
 * 商品小类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_smallType")
public class ProductSmallType {

	private int id; // 编号
	private String name; // 小类名称
	//与商品大类实体多对一
	private ProductBigType bigType; // 大类
	//与商品实体一对多
	private List<Product> productList=new ArrayList<Product>(); // 商品集合
	private String remarks; // 备注
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
	
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//配置商品大类的多对一关系
	//cascade={CascadeType.PERSIST}级联保存
	@ManyToOne(cascade={CascadeType.PERSIST})
	//配置外键
	@JoinColumn(name="bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}
	
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	//配置与商品实体的一对多关系（双向维护）
	@OneToMany(mappedBy="smallType")
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
	
}
