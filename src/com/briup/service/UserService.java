package com.briup.service;


import java.util.List;

import com.briup.entity.PageBean;
import com.briup.entity.User;

/**
 * 用户Serivce接口
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 是否存在指定用户名的用户
	 * @param userName
	 * @return
	 */
	public boolean existUserWithUserName(String userName);
	
	/**
	 * 保存用户
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * 用户登录
	 * @param user
	 */
	public User login(User user);
	
	/**
	 * 分页查询用户
	 * @param s_user
	 * @param pageBean
	 * @return
	 */
	public List<User> findUserList(User s_user,PageBean pageBean);
	
	/**
	 * 查询用户数量
	 * @param s_user
	 * @return
	 */
	public Long getUserCount(User s_user);
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(User user);
	
	/**
	 * 通过id获取用户实体
	 * @return
	 */
	public User getUserById(int id);
	
}
