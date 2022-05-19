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
 * 用户Service类
 * @author Administrator
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private BaseDAO<User> baseDAO;
	//是否存在指定用户名的用户
	public boolean existUserWithUserName(String userName) {
		//查询个数
		String hql="select count(*) from User where userName=?";
		long count=baseDAO.count(hql, new Object[]{userName});
		//该用户数量大于0
		if(count>0){
			//用户存在
			return true;
		}else{
			//用户不存在
			return false;			
		}
	}
	//保存用户
	public void saveUser(User user) {
		baseDAO.merge(user);
	}
	//用户登录
	public User login(User user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User u where u.userName=? and u.password=?");
		param.add(user.getUserName());
		param.add(user.getPassword());
		//判断是否是管理员
		if(user.getStatus()==2){
			hql.append(" and u.status=2");
		}
		return baseDAO.get(hql.toString(), param);
	}
	//分页查询用户
	public List<User> findUserList(User s_user,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		//模糊查询用户
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
	// 查询用户数量
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
	//删除用户
	public void delete(User user) {
		baseDAO.delete(user);
	}
	//通过id获取用户实体
	public User getUserById(int id) {
		return baseDAO.get(User.class, id);
	}

}
