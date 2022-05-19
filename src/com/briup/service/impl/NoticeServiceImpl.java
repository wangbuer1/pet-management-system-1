package com.briup.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.Notice;
import com.briup.entity.PageBean;
import com.briup.service.NoticeService;
import com.briup.util.StringUtil;

/**
 * 公告Service类
 * @author Administrator
 *
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private BaseDAO<Notice> baseDAO;
	//查询公告
	public List<Notice> findNoticeList(Notice s_notice, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Notice");
		if(s_notice!=null){
			if(StringUtil.isNotEmpty(s_notice.getTitle())){
				hql.append(" and title like ?");
				param.add("%"+s_notice.getTitle()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}
	//通过ID查询公告
	public Notice getNoticeById(int noticeId) {
		return baseDAO.get(Notice.class, noticeId);
	}
	//获取公告数量
	public Long getNoticeCount(Notice s_notice) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Notice");
		if(s_notice!=null){
			if(StringUtil.isNotEmpty(s_notice.getTitle())){
				hql.append(" and title like ?");
				param.add("%"+s_notice.getTitle()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//保存
	public void saveNotice(Notice notice) {
		// TODO Auto-generated method stub
		baseDAO.merge(notice);
	}
	//删除
	public void delete(Notice notice) {
		// TODO Auto-generated method stub
		baseDAO.delete(notice);
	}

}
