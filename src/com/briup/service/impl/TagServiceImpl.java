package com.briup.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.Notice;
import com.briup.entity.PageBean;
import com.briup.entity.Tag;
import com.briup.service.TagService;
import com.briup.util.StringUtil;

/**
 * ��Ʒ����ʵ����
 * @author Administrator
 *
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

	@Resource
	private BaseDAO<Tag> baseDAO;
	//��ѯ��ǩ����
	public List<Tag> findTagList(Tag s_tag,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Tag");
		if(s_tag!=null){
			if(StringUtil.isNotEmpty(s_tag.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_tag.getName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param);
		}
	}
	//��ѯ��ǩ����
	public Long getTagCount(Tag s_tag) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Tag");
		if(s_tag!=null){
			if(StringUtil.isNotEmpty(s_tag.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_tag.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//�����ǩ
	public void saveTag(Tag tag) {
		// TODO Auto-generated method stub
		baseDAO.merge(tag);
	}
	//ɾ����ǩ
	public void delete(Tag tag) {
		// TODO Auto-generated method stub
		baseDAO.delete(tag);
	}
	//ͨ����ǩid��ȡ��ǩ
	public Tag getTagById(int tagId) {
		// TODO Auto-generated method stub
		return baseDAO.get(Tag.class, tagId);
	}

}
