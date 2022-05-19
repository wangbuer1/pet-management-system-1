package com.briup.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.briup.entity.News;
import com.briup.entity.Notice;
import com.briup.entity.PageBean;
import com.briup.entity.Product;
import com.briup.entity.ProductBigType;
import com.briup.entity.Tag;
import com.briup.service.NewsService;
import com.briup.service.NoticeService;
import com.briup.service.ProductBigTypeService;
import com.briup.service.ProductService;
import com.briup.service.TagService;
/**
 * ��ʼ��Action��
 * @author Administrator
 *
 */
@Component
public class InitAction  implements ServletContextListener,ApplicationContextAware  {
	
	//����applicationʵ��
	private static ApplicationContext applicationContext;
	

	public InitAction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
	}

	//��ʼ��������Ϣ
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		//��ȡapplication����
		ServletContext application = servletContextEvent.getServletContext();
		//��������Ϣ���뵽����
		ProductBigTypeService productBigTypeService=(ProductBigTypeService)applicationContext.getBean("productBigTypeService");
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		//����ǩ��Ϣ���뵽����
		TagService tagService=(TagService)applicationContext.getBean("tagService");
		List<Tag> tagList=tagService.findTagList(null,null);
		application.setAttribute("tagList", tagList);
		//��������Ϣ���뵽����
		NoticeService noticeService=(NoticeService)applicationContext.getBean("noticeService");
		List<Notice> noticeList=noticeService.findNoticeList(null, new PageBean(1,7));
		application.setAttribute("noticeList", noticeList);
		//��������Ϣ���뵽���棨ȡ7�����ݣ�
		NewsService newsService=(NewsService)applicationContext.getBean("newsService");
		List<News> newsList=newsService.findNewsList(null, new PageBean(1,7));
		application.setAttribute("newsList", newsList);
		
		// �����ؼ�
		Product s_product=new Product();
		s_product.setSpecialPrice(1);
		//����Ʒ��Ϣ���뵽����
		ProductService productService=(ProductService)applicationContext.getBean("productService");
		List<Product> specialPriceProductList= productService.findProductList(s_product, new PageBean(1, 8));
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		// �����Ƽ�
		s_product=new Product();
		s_product.setHot(1);
		List<Product> hotProductList= productService.findProductList(s_product, new PageBean(1, 6));
		application.setAttribute("hotProductList", hotProductList);
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
	}

	
}
