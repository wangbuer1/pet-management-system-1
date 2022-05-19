package com.briup.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * �û�ʵ��
 * @author Administrator
 *
 */
//ͨ��ע��javax.persistence.Table��ȡ���ݿ��ľ�����Ϣ
//java hibernate ���� Table ע�� ��ȡ ���ݿ� ���� �ֶ��� 
@Entity
@Table(name="t_user")
public class User {

	private int id; // ���
	private String trueName; // ��ʵ����
	private String userName; // �û���
	private String password; // ����
	private String sex; // �Ա�
	private Date birthday; // ��������
	private String dentityCode; // ���֤
	private String email; // �ʼ�
	private String mobile; // ��ϵ�绰
	private String address; // �ջ���ַ
	private int status=1; // 1 ��ͨ�û� 2 ����Ա
	//һ�Զ�	
	private List<Order> orderList=new ArrayList<Order>(); // ����
	//����
	//�����������ɲ���generator(����native���أ�
	@Id
	@GeneratedValue(generator="_native")
	//�Զ����������ɲ���
	@GenericGenerator(name="_native",strategy="native")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(length=20)
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	@Column(length=20)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(length=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(length=5)
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(length=20)
	public String getDentityCode() {
		return dentityCode;
	}
	public void setDentityCode(String dentityCode) {
		this.dentityCode = dentityCode;
	}
	
	@Column(length=20)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(length=20)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Column(length=100)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	//ͨ��ע������һ�Զ�
	//targetEntity��Ŀ��ʵ�� cascade ����
	@OneToMany(targetEntity=Order.class,cascade=CascadeType.ALL)
	//ָ������ʵ���ϵ������ ���
	//@JoinColumn(name="userId")
	@JoinColumn(name="userId",updatable=false)
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	
}
