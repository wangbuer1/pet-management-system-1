package com.briup.service;

import java.util.List;

import com.briup.entity.Order;
import com.briup.entity.PageBean;

/**
 * 订单Service接口
 * @author Administrator
 *
 */
public interface OrderService {

	/**
	 * 保存订单
	 * @param order
	 */
	public void saveOrder(Order order);
	
	/**
	 * 查询订单
	 * @param order
	 * @return
	 */
	public List<Order> findOrderList(Order s_order,PageBean pageBean);
	
	/**
	 * 获取订单的记录数
	 * @param s_product
	 * @return
	 */
	public Long getOrderCount(Order s_order);
	
	/**
	 * 修改订单状态
	 * @param status
	 */
	public void updateOrderStatus(int status,String orderNo);
	
	/**
	 * 通过订单号获取订单
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(int orderId);
	
}
