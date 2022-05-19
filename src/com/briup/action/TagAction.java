package com.briup.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.briup.entity.Tag;
import com.briup.entity.PageBean;
import com.briup.service.TagService;
import com.briup.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;


/**
 * 标签Action类
 * @author Administrator
 *
 */
@Controller
public class TagAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private TagService tagService;
	
	private String mainPage; // 主页
	private int tagId; // 标签id
	private Tag tag; // 标签
	private String navCode; // 导航代码
	
	private String page;
	private String rows;
	private Tag s_tag;
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

	public Tag getS_tag() {
		return s_tag;
	}

	public void setS_tag(Tag s_tag) {
		this.s_tag = s_tag;
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

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	
	/**
	 * 分页查询标签信息
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Tag> tagList=tagService.findTagList(s_tag, pageBean);
		long total=tagService.getTagCount(s_tag);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(tagList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 后台-保存标签信息
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		tagService.saveTag(tag);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 删除标签
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Tag tag=tagService.getTagById(Integer.parseInt(idsStr[i]));
			tagService.delete(tag);								
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

}
