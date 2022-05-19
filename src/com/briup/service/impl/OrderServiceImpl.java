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
 * ����Serviceʵ����
 * @author Administrator
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

	@Resource
	private BaseDAO<Order> baseDAO;
	//���涩��
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		baseDAO.save(order);
	}
	//��ѯ����
	public List<Order> findOrderList(Order s_order,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Order");
		//������Ϊ��
		if(s_order!=null){
			//ͨ���û�ID��ѯ����
			if(s_order.getUser()!=null&&s_order.getUser().getId()!=0){
				hql.append(" and user.id=?");
				param.add(s_order.getUser().getId());
			}
			//ͨ���û�����ѯ����
			if(s_order.getUser()!=null&&StringUtil.isNotEmpty(s_order.getUser().getUserName())){
				hql.append(" and user.userName like ?");
				param.add("%"+s_order.getUser().getUserName()+"%");
			}
			//ͨ�������Ų�ѯ����
			if(StringUtil.isNotEmpty(s_order.getOrderNo())){
				hql.append(" and orderNo like ?");
				param.add("%"+s_order.getOrderNo()+"%");
			}
		}
		hql.append(" order by  createTime desc ");
		//�ж��Ƿ��ҳ
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param);			
		}
	}
	//���¶���״̬
	public void updateOrderStatus(int status,String orderNo) {
		List<Object> param=new LinkedList<Object>();
		String hql="update Order set status=? where orderNo=?";
		param.add(status);
		param.add(orderNo);
		baseDAO.executeHql(hql, param);
	}
	//��ȡ��������
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
	//ͨ��ID��ѯ����
	public Order getOrderById(int orderId) {
		return baseDAO.get(Order.class, orderId);
	}

}
