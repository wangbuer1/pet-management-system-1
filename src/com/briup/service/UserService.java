package com.briup.service;


import java.util.List;

import com.briup.entity.PageBean;
import com.briup.entity.User;

/**
 * �û�Serivce�ӿ�
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * �Ƿ����ָ���û������û�
	 * @param userName
	 * @return
	 */
	public boolean existUserWithUserName(String userName);
	
	/**
	 * �����û�
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * �û���¼
	 * @param user
	 */
	public User login(User user);
	
	/**
	 * ��ҳ��ѯ�û�
	 * @param s_user
	 * @param pageBean
	 * @return
	 */
	public List<User> findUserList(User s_user,PageBean pageBean);
	
	/**
	 * ��ѯ�û�����
	 * @param s_user
	 * @return
	 */
	public Long getUserCount(User s_user);
	
	/**
	 * ɾ���û�
	 * @param user
	 */
	public void delete(User user);
	
	/**
	 * ͨ��id��ȡ�û�ʵ��
	 * @return
	 */
	public User getUserById(int id);
	
}
