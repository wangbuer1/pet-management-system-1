package com.briup.service;

import java.util.List;

import com.briup.entity.Notice;
import com.briup.entity.PageBean;

/**
 * ����Service��
 * @author Administrator
 *
 */
public interface NoticeService {

	/**
	 * ���ҹ��漯��
	 * @return
	 */
	public List<Notice> findNoticeList(Notice s_notice,PageBean pageBean);
	
	/**
	 * ��ѯ��������
	 * @param s_notice
	 * @return
	 */
	public Long getNoticeCount(Notice s_notice);
	
	/**
	 * ���湫��
	 * @param notice
	 */
	public void saveNotice(Notice notice);
	
	/**
	 * ɾ������
	 * @param notice
	 */
	public void delete(Notice notice);
	
	/**
	 * ͨ������id��ȡ����
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(int noticeId);
}
