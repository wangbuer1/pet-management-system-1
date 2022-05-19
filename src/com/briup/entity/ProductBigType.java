package com.briup.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品大类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_bigType")
public class ProductBigType {

	private int id=-1; // 编号
	private String name; // 大类名称
	//与商品小类一对多
	private List<ProductSmallType> smallTypeList=new ArrayList<ProductSmallType>(); // 小类
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
	//配置与商品小类的一对多关系
	//fetch=FetchType.EAGER急加载。加载一个实体时，定义急加载的属性会立即从数据库中加载。
	@OneToMany(mappedBy="bigType",fetch=FetchType.EAGER)
	public List<ProductSmallType> getSmallTypeList() {
		return smallTypeList;
	}
	public void setSmallTypeList(List<ProductSmallType> smallTypeList) {
		this.smallTypeList = smallTypeList;
	}
	//配置一对多映射（双向）
	@OneToMany(mappedBy="bigType")
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
