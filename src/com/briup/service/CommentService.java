package com.briup.service;

import java.util.List;

import com.briup.entity.Comment;
import com.briup.entity.PageBean;

/**
 * ����Service�ӿ�
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * �������Լ���
	 * @param pageBean
	 * @return
	 */
	public List<Comment> findCommentList(Comment s_Comment,PageBean pageBean);
	
	/**
	 * ��ȡ�����ܼ�¼��
	 * @param s_Comment
	 * @return
	 */
	public Long getCommentCount(Comment s_Comment);
	
	/**
	 * ��������
	 * @param comment
	 */
	public void saveComment(Comment comment);
	
	/**
	 * ɾ������
	 * @param comment
	 */
	public void delete(Comment comment);
	
	/**
	 * ͨ������id��ȡ����
	 * @param commentId
	 * @return
	 */
	public Comment getCommentById(int commentId);
}
