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
 * ��Ʒ����
 * @author Administrator
 *
 */
@Entity
@Table(name="t_bigType")
public class ProductBigType {

	private int id=-1; // ���
	private String name; // ��������
	//����ƷС��һ�Զ�
	private List<ProductSmallType> smallTypeList=new ArrayList<ProductSmallType>(); // С��
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
	//��������ƷС���һ�Զ��ϵ
	//fetch=FetchType.EAGER�����ء�����һ��ʵ��ʱ�����弱���ص����Ի����������ݿ��м��ء�
	@OneToMany(mappedBy="bigType",fetch=FetchType.EAGER)
	public List<ProductSmallType> getSmallTypeList() {
		return smallTypeList;
	}
	public void setSmallTypeList(List<ProductSmallType> smallTypeList) {
		this.smallTypeList = smallTypeList;
	}
	//����һ�Զ�ӳ�䣨˫��
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
