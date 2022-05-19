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
 * ��ƷС��
 * @author Administrator
 *
 */
@Entity
@Table(name="t_smallType")
public class ProductSmallType {

	private int id; // ���
	private String name; // С������
	//����Ʒ����ʵ����һ
	private ProductBigType bigType; // ����
	//����Ʒʵ��һ�Զ�
	private List<Product> productList=new ArrayList<Product>(); // ��Ʒ����
	private String remarks; // ��ע
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
	
	@Column(length=50)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//������Ʒ����Ķ��һ��ϵ
	//cascade={CascadeType.PERSIST}��������
	@ManyToOne(cascade={CascadeType.PERSIST})
	//�������
	@JoinColumn(name="bigTypeId")
	public ProductBigType getBigType() {
		return bigType;
	}
	
	public void setBigType(ProductBigType bigType) {
		this.bigType = bigType;
	}
	//��������Ʒʵ���һ�Զ��ϵ��˫��ά����
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
