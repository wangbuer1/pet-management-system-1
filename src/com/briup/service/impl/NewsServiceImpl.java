package com.briup.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.News;
import com.briup.entity.PageBean;
import com.briup.service.NewsService;
import com.briup.util.StringUtil;

/**
 * 新闻Service类
 * @author Administrator
 *
 */
@Service("newsService")
public class NewsServiceImpl implements NewsService {

	@Resource
	private BaseDAO<News> baseDAO;
	//查询新闻
	public List<News> findNewsList(News s_news, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from News");
		if(s_news!=null){
			if(StringUtil.isNotEmpty(s_news.getTitle())){
				hql.append(" and title like ?");
				param.add("%"+s_news.getTitle()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}
	//通过ID查询新闻
	public News getNewsById(int newsId) {
		return baseDAO.get(News.class, newsId);
	}
	//获取新闻数量
	public Long getNewsCount(News s_news) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from News");
		if(s_news!=null){
			if(StringUtil.isNotEmpty(s_news.getTitle())){
				hql.append(" and title like ?");
				param.add("%"+s_news.getTitle()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//保存
	public void saveNews(News news) {
		// TODO Auto-generated method stub
		baseDAO.merge(news);
	}
	//删除
	public void delete(News news) {
		// TODO Auto-generated method stub
		baseDAO.delete(news);
	}

}
