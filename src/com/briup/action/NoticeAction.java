package com.briup.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.briup.entity.Notice;
import com.briup.entity.PageBean;
import com.briup.service.NoticeService;
import com.briup.util.NavUtil;
import com.briup.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 公告Action类
 * @author Administrator
 *
 */
@Controller
public class NoticeAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private NoticeService noticeService;
	
	private String mainPage; // 主页
	private int noticeId; // 公告id
	private Notice notice; // 公告
	private String navCode; // 导航代码
	
	private String page;
	private String rows;
	private Notice s_notice;
	private String ids;
	
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

	public Notice getS_notice() {
		return s_notice;
	}

	public void setS_notice(Notice s_notice) {
		this.s_notice = s_notice;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	/**
	 * 显示新闻详情
	 * @return
	 * @throws Exception
	 */
	public String showNotice()throws Exception{
		notice=noticeService.getNoticeById(noticeId);
		mainPage="notice/noticeDetails.jsp";
		navCode=NavUtil.genNavCode("公告信息");
		return SUCCESS;
	}
	
	/**
	 * 分页查询公告信息
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Notice> noticeList=noticeService.findNoticeList(s_notice, pageBean);
		long total=noticeService.getNoticeCount(s_notice);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(noticeList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 后台-保存公告信息
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		if(notice.getId()==0){
			notice.setCreateTime(new Date());
		}
		noticeService.saveNotice(notice);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 删除公告
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Notice notice=noticeService.getNoticeById(Integer.parseInt(idsStr[i]));
			noticeService.delete(notice);								
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

}
