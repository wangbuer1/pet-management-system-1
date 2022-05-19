package com.briup.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.Comment;
import com.briup.entity.Notice;
import com.briup.entity.PageBean;
import com.briup.service.CommentService;
import com.briup.util.StringUtil;

/**
 * ����Service��
 * @author Administrator
 *
 */
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private BaseDAO<Comment> baseDAO;
	//��ѯ����
	public List<Comment> findCommentList(Comment s_Comment,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Comment");
		if(s_Comment!=null){
			if(StringUtil.isNotEmpty(s_Comment.getContent())){
				hql.append(" and content like ?");
				param.add("%"+s_Comment.getContent()+"%");
			}
		}
		//������ʱ�併������
		hql.append(" order by createTime desc");
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}
	//��ȡ��������
	public Long getCommentCount(Comment s_Comment) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Comment");
		if(s_Comment!=null){
			if(StringUtil.isNotEmpty(s_Comment.getContent())){
				hql.append(" and content like ?");
				param.add("%"+s_Comment.getContent()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//��������
	public void saveComment(Comment comment) {
		// TODO Auto-generated method stub
		baseDAO.merge(comment);
	}
	//ɾ������
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		baseDAO.delete(comment);
	}
	//ͨ��ID��ѯ����
	public Comment getCommentById(int commentId) {
		return baseDAO.get(Comment.class, commentId);
	}

}
