package com.briup.service.impl;


import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.PageBean;
import com.briup.entity.User;
import com.briup.service.UserService;
import com.briup.util.StringUtil;

/**
 * �û�Service��
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private BaseDAO<User> baseDAO;
	//�Ƿ����ָ���û������û�
	public boolean existUserWithUserName(String userName) {
		//��ѯ����
		String hql="select count(*) from User where userName=?";
		long count=baseDAO.count(hql, new Object[]{userName});
		//���û���������0
		if(count>0){
			//�û�����
			return true;
		}else{
			//�û�������
			return false;			
		}
	}
	//�����û�
	public void saveUser(User user) {
		baseDAO.merge(user);
	}
	//�û���¼
	public User login(User user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User u where u.userName=? and u.password=?");
		param.add(user.getUserName());
		param.add(user.getPassword());
		//�ж��Ƿ��ǹ���Ա
		if(user.getStatus()==2){
			hql.append(" and u.status=2");
		}
		return baseDAO.get(hql.toString(), param);
	}
	//��ҳ��ѯ�û�
	public List<User> findUserList(User s_user,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		//ģ����ѯ�û�
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and userName like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		hql.append(" and status=1");
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return null;			
		}
	}
	// ��ѯ�û�����
	public Long getUserCount(User s_user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from User");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and userName like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		hql.append(" and status=1");
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//ɾ���û�
	public void delete(User user) {
		baseDAO.delete(user);
	}
	//ͨ��id��ȡ�û�ʵ��
	public User getUserById(int id) {
		return baseDAO.get(User.class, id);
	}

}
