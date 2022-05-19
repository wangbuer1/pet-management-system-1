package com.briup.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.briup.entity.PageBean;
import com.briup.entity.User;
import com.briup.service.UserService;
import com.briup.util.NavUtil;
import com.briup.util.ResponseUtil;
import com.briup.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * �û�Action��
 * @author Administrator
 *
 */
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	@Resource
	private UserService userService;
	
	private String userName;//���浽Session
	private User user;//�û�ʵ��
	private String imageCode;//��֤��
	private String error;
	
	private String mainPage;//��ҳ
	private String navCode;//��������
	
	
	private String page;
	private String rows;
	
	private User s_user;
	
	private String ids;
	
	

	public User getS_user() {
		return s_user;
	}

	public void setS_user(User s_user) {
		this.s_user = s_user;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}



	public String getRows() {
		return rows;
	}



	public void setRows(String rows) {
		this.rows = rows;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	



	public String getImageCode() {
		return imageCode;
	}



	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}
	
	



	public String getMainPage() {
		return mainPage;
	}



	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}



	public String getNavCode() {
		return navCode;
	}



	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	
	



	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * �ж�ָ���û������û��Ƿ����
	 * @return
	 * @throws Exception
	 */
	public String existUserWithUserName()throws Exception{
		boolean exist=userService.existUserWithUserName(userName);
		//д��JSON����
		JSONObject result=new JSONObject();
		if(exist){
			//�û�����
			result.put("exist", true);
		}else{
			//�û�������
			result.put("exist", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * �û�ע��
	 * @return
	 * @throws Exception
	 */
	public String register()throws Exception{
		userService.saveUser(user);
		return "register_success";
	}
	
	/**
	 * �û���¼
	 * @return
	 * @throws Exception
	 */
	public String login()throws Exception{
		HttpSession session=request.getSession();
		//��ǰ�û�
		User currentUser=userService.login(user);
		//�ж��û�����
		if(currentUser==null){
			error="�û���������Ϊ�գ�";
			if(user.getStatus()==2){
				return "adminError";
			}else{
				return ERROR;			
			}
		}
		//��֤��֤��
		else if(!imageCode.equals(session.getAttribute("sRand"))){
			error="��֤�����";
			if(user.getStatus()==2){
				return "adminError";
			}else{
				return ERROR;			
			}
		}else{
			session.setAttribute("currentUser", currentUser);
		}
		if(currentUser.getStatus()==2){
			return "adminLogin";
		}else{
			return "login";			
		}
	}
	
	/**
	 * ע���û�
	 * @throws Exception
	 */
	public String logout()throws Exception{
		request.getSession().invalidate();
		return "logout";
	}
	
	/**
	 * ����Աע��
	 * @return
	 * @throws Exception
	 */
	public String logout2()throws Exception{
		request.getSession().invalidate();
		return "logout2";
	}
	
	/**
	 * �û�����
	 * @return
	 * @throws Exception
	 */
	public String userCenter()throws Exception{
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/ucDefault.jsp";
		return "userCenter";
	}
	
	/**
	 * ��ȡ�û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String getUserInfo()throws Exception{
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	/**
	 * �޸ĸ�����ϢԤ����
	 * @return
	 * @throws Exception
	 */
	public String preSave()throws Exception{
		HttpSession session=request.getSession();
		user=(User)session.getAttribute("currentUser");
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/userSave.jsp";
		return "userCenter";
	}
	
	/**
	 * �����û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		HttpSession session=request.getSession();
		userService.saveUser(user);
		session.setAttribute("currentUser", user);
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/userInfo.jsp";
		return "userCenter";
	}
	
	/**
	 * ��̨-�����û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String saveUser()throws Exception{
		userService.saveUser(user);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * ��ҳ��ѯ�û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		//��ҳ��ѯ
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<User> userList=userService.findUserList(s_user, pageBean);
		//��ȡ�ܼ�¼��
		long total=userService.getUserCount(s_user);
		//��װJSON��
		JsonConfig jsonConfig = new JsonConfig();
		//����orderlist
		jsonConfig.setExcludes(new String[]{"orderList"});
		//����JSON������ʱ���ʽ
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(userList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ɾ���û�
	 * @return
	 * @throws Exception
	 */
	public String deleteUsers()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			User u=userService.getUserById(Integer.parseInt(idsStr[i]));
			userService.delete(u);
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * �޸�����
	 * @return
	 * @throws Exception
	 */
	public String modifyPassword()throws Exception{
		User u=userService.getUserById(user.getId());
		u.setPassword(user.getPassword());
		userService.saveUser(u);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}


	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

}
