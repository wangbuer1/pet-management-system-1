package com.briup.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.Order;
import com.briup.entity.PageBean;
import com.briup.service.OrderService;
import com.briup.util.StringUtil;

/**
 * 订单Service实现类
 * @author Administrator
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Resource
	private BaseDAO<Order> baseDAO;
	//保存订单
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		baseDAO.save(order);
	}
	//查询订单
	public List<Order> findOrderList(Order s_order,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Order");
		//订单不为空
		if(s_order!=null){
			//通过用户ID查询订单
			if(s_order.getUser()!=null&&s_order.getUser().getId()!=0){
				hql.append(" and user.id=?");
				param.add(s_order.getUser().getId());
			}
			//通过用户名查询订单
			if(s_order.getUser()!=null&&StringUtil.isNotEmpty(s_order.getUser().getUserName())){
				hql.append(" and user.userName like ?");
				param.add("%"+s_order.getUser().getUserName()+"%");
			}
			//通过订单号查询订单
			if(StringUtil.isNotEmpty(s_order.getOrderNo())){
				hql.append(" and orderNo like ?");
				param.add("%"+s_order.getOrderNo()+"%");
			}
		}
		hql.append(" order by  createTime desc ");
		//判断是否分页
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param);			
		}
	}
	//更新订单状态
	public void updateOrderStatus(int status,String orderNo) {
		List<Object> param=new LinkedList<Object>();
		String hql="update Order set status=? where orderNo=?";
		param.add(status);
		param.add(orderNo);
		baseDAO.executeHql(hql, param);
	}
	//获取订单数量
	public Long getOrderCount(Order s_order) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Order");
		if(s_order!=null){
			if(s_order.getUser()!=null&&s_order.getUser().getId()!=0){
				hql.append(" and user.id=?");
				param.add(s_order.getUser().getId());
			}
			if(s_order.getUser()!=null&&StringUtil.isNotEmpty(s_order.getUser().getUserName())){
				hql.append(" and user.userName like ?");
				param.add("%"+s_order.getUser().getUserName()+"%");
			}
			if(StringUtil.isNotEmpty(s_order.getOrderNo())){
				hql.append(" and orderNo like ?");
				param.add("%"+s_order.getOrderNo()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//通过ID查询订单
	public Order getOrderById(int orderId) {
		return baseDAO.get(Order.class, orderId);
	}

}
