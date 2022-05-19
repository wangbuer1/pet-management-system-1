package com.briup.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.briup.entity.Comment;
import com.briup.entity.Notice;
import com.briup.entity.PageBean;
import com.briup.service.CommentService;
import com.briup.util.PageUtil;
import com.briup.util.PropertiesUtil;
import com.briup.util.ResponseUtil;
import com.briup.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 留言Action类
 * @author Administrator
 *
 */
@Controller
public class CommentAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	/**
	 * 留言Service
	 */
	@Resource
	private CommentService commentService;
	
	private List<Comment> commentList;
	
	private String mainPage; // 主页
	private String page;  //  第几页
	private Long total; //  总记录数
	private String pageCode; //  分页代码
	
	private Comment s_comment; // 查询留言
	
	private Comment comment; // 留言实体
	
	private String rows;
	private String ids;
	
	private int commentId;
	

	public Comment getS_comment() {
		return s_comment;
	}

	public void setS_comment(Comment s_comment) {
		this.s_comment = s_comment;
	}


	public String getRows() {
		return rows;
	}


	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	/**
	 * 留言查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		// TODO Auto-generated method stub
		//留言为空时，默认“1”
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		//每页显示3条留言
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("commentPageSize")));
		commentList=commentService.findCommentList(s_comment, pageBean);
		//总计数
		total=commentService.getCommentCount(s_comment);
		//分页代码
		pageCode=PageUtil.genPaginationNoParam(request.getContextPath()+"/comment_list.action",total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("commentPageSize")));
		mainPage="comment/commentList.jsp";
		return SUCCESS;
	}
	
	/**
	 * 后台留言分页查询
	 * @return
	 * @throws Exception
	 */
	public String listComment()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Comment> commentList=commentService.findCommentList(s_comment, pageBean);
		long total=commentService.getCommentCount(s_comment);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(commentList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 保存留言
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		if(comment.getCreateTime()==null){
			comment.setCreateTime(new Date());
		}
		commentService.saveComment(comment);
		return "save";
	}
	
	/**
	 * 回复留言
	 * @return
	 * @throws Exception
	 */
	public String replay()throws Exception{
		comment.setReplyTime(new Date());
		commentService.saveComment(comment);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}


	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	/**
	 * 删除留言
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Comment comment=commentService.getCommentById(Integer.parseInt(idsStr[i]));
			commentService.delete(comment);								
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 通过id加载Comment实体
	 * @return
	 * @throws Exception
	 */
	public String loadCommentById()throws Exception{
		Comment comment=commentService.getCommentById(commentId);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONObject result=JSONObject.fromObject(comment,jsonConfig);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	

}
