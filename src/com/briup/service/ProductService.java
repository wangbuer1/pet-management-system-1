package com.briup.service;

import java.util.List;

import com.briup.entity.PageBean;
import com.briup.entity.Product;

/**
 * 商品Service类
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * 查找商品集合
	 * @return
	 */
	public List<Product> findProductList(Product s_product,PageBean pageBean);
	
	/**
	 * 获取商品的记录数
	 * @param s_product
	 * @return
	 */
	public Long getProductCount(Product s_product);
	
	/**
	 * 通过商品id获取商品
	 * @param productId
	 * @return
	 */
	public Product getProductById(int productId);
	
	/**
	 * 是否存在指定商品小类的商品
	 * @param smallTypeId
	 * @return
	 */
	public boolean existProductWithSmallTypeId(int smallTypeId);
	
	/**
	 * 保存商品
	 * @param product
	 */
	public void saveProduct(Product product);
	
	/**
	 * 删除商品
	 * @param product
	 */
	public void deleteProduct(Product product);
	
	/**
	 * 设置商品为热卖
	 * @param productId
	 */
	public void setProductWithHot(int productId);
	
	/**
	 * 设置商品为特价
	 * @param productId
	 */
	public void setProductWithSpecialPrice(int productId);
	
}
