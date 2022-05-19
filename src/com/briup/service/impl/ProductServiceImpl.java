package com.briup.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.PageBean;
import com.briup.entity.Product;
import com.briup.service.ProductService;
import com.briup.util.StringUtil;

/**
 * 商品Service类
 * @author Administrator
 *
 */
//service注解
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private BaseDAO<Product> baseDAO;
	//分页查询商品
	public List<Product> findProductList(Product s_product, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Product");
		if(s_product!=null){
			//通过大类
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id = ?");
				param.add(s_product.getBigType().getId());
			}
			//通过小类
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id = ?");
				param.add(s_product.getSmallType().getId());
			}
			//模糊查询搜索商品
			if(StringUtil.isNotEmpty(s_product.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_product.getName()+"%");
			}
			//设置特价（按时间降序排列）
			if(s_product.getSpecialPrice()==1){
				hql.append(" and specialPrice =1 order by specialPriceTime desc ");
			}
			//设置热卖（按时间降序排列）
			if(s_product.getHot()==1){
				hql.append(" and hot =1 order by hotTime desc ");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}
	//获取商品数量
	public Long getProductCount(Product s_product) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Product");
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigTypeId = ?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallTypeId = ?");
				param.add(s_product.getSmallType().getId());
			}
			if(StringUtil.isNotEmpty(s_product.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_product.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	//通过商品id获取商品
	public Product getProductById(int productId) {
		return baseDAO.get(Product.class, productId);
	}
	//是否存在指定商品小类的商品
	public boolean existProductWithSmallTypeId(int smallTypeId) {
		StringBuffer hql=new StringBuffer("from Product where smallType.id="+smallTypeId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}
	//保存商品
	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		baseDAO.merge(product);
	}
	//删除商品
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		baseDAO.delete(product);
	}
	//设置商品为热卖
	public void setProductWithHot(int productId) {
		// TODO Auto-generated method stub
		Product product=baseDAO.get(Product.class, productId);
		product.setHot(1);
		product.setHotTime(new Date());
		baseDAO.save(product);
	}
	//设置商品为特价
	public void setProductWithSpecialPrice(int productId) {
		// TODO Auto-generated method stub
		Product product=baseDAO.get(Product.class, productId);
		product.setSpecialPrice(1);
		product.setSpecialPriceTime(new Date());
		baseDAO.save(product);
	}

}
