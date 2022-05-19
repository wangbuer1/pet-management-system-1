package com.briup.service;

import java.util.List;

import com.briup.entity.Notice;
import com.briup.entity.PageBean;

/**
 * 公告Service类
 * @author Administrator
 *
 */
public interface NoticeService {

	/**
	 * 查找公告集合
	 * @return
	 */
	public List<Notice> findNoticeList(Notice s_notice,PageBean pageBean);
	
	/**
	 * 查询公告数量
	 * @param s_notice
	 * @return
	 */
	public Long getNoticeCount(Notice s_notice);
	
	/**
	 * 保存公告
	 * @param notice
	 */
	public void saveNotice(Notice notice);
	
	/**
	 * 删除公告
	 * @param notice
	 */
	public void delete(Notice notice);
	
	/**
	 * 通过公告id获取公告
	 * @param noticeId
	 * @return
	 */
	public Notice getNoticeById(int noticeId);
}
