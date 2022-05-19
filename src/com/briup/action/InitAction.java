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
 * 初始化Action类
 * @author Administrator
 *
 */
@Component
public class InitAction  implements ServletContextListener,ApplicationContextAware  {
	
	//创建application实例
	private static ApplicationContext applicationContext;
	

	public InitAction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
	}

	//初始化加载信息
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		//获取application对象
		ServletContext application = servletContextEvent.getServletContext();
		//将大类信息加入到缓存
		ProductBigTypeService productBigTypeService=(ProductBigTypeService)applicationContext.getBean("productBigTypeService");
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		//将标签信息加入到缓存
		TagService tagService=(TagService)applicationContext.getBean("tagService");
		List<Tag> tagList=tagService.findTagList(null,null);
		application.setAttribute("tagList", tagList);
		//将公告信息加入到缓存
		NoticeService noticeService=(NoticeService)applicationContext.getBean("noticeService");
		List<Notice> noticeList=noticeService.findNoticeList(null, new PageBean(1,7));
		application.setAttribute("noticeList", noticeList);
		//将新闻信息加入到缓存（取7条数据）
		NewsService newsService=(NewsService)applicationContext.getBean("newsService");
		List<News> newsList=newsService.findNewsList(null, new PageBean(1,7));
		application.setAttribute("newsList", newsList);
		
		// 今日特价
		Product s_product=new Product();
		s_product.setSpecialPrice(1);
		//将商品信息加入到缓存
		ProductService productService=(ProductService)applicationContext.getBean("productService");
		List<Product> specialPriceProductList= productService.findProductList(s_product, new PageBean(1, 8));
		application.setAttribute("specialPriceProductList", specialPriceProductList);
		
		// 热卖推荐
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
