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
 * 用户实体
 * @author Administrator
 *
 */
//通过注解javax.persistence.Table获取数据库表的具体信息
//java hibernate 根据 Table 注解 获取 数据库 表名 字段名 
@Entity
@Table(name="t_user")
public class User {

	private int id; // 编号
	private String trueName; // 真实姓名
	private String userName; // 用户名
	private String password; // 密码
	private String sex; // 性别
	private Date birthday; // 出生日期
	private String dentityCode; // 身份证
	private String email; // 邮件
	private String mobile; // 联系电话
	private String address; // 收货地址
	private int status=1; // 1 普通用户 2 管理员
	//一对多	
	private List<Order> orderList=new ArrayList<Order>(); // 订单
	//主键
	//设置主键生成策略generator(引用native本地）
	@Id
	@GeneratedValue(generator="_native")
	//自定义主键生成策略
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
	//通过注解配置一对多
	//targetEntity：目标实体 cascade 级联
	@OneToMany(targetEntity=Order.class,cascade=CascadeType.ALL)
	//指定保存实体关系的配置 外键
	//@JoinColumn(name="userId")
	@JoinColumn(name="userId",updatable=false)
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	
}
