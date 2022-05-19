package com.briup.service;

import java.util.List;

import com.briup.entity.PageBean;
import com.briup.entity.ProductBigType;
import com.briup.entity.ProductSmallType;

/**
 * 商品小类service接口
 * @author Administrator
 *
 */
public interface ProductSmallTypeService {

	/**
	 * 是否存在指定商品大类的商品小类
	 * @param bigTypeId
	 * @return
	 */
	public boolean existSmallTypeWithBigTypeId(int bigTypeId);
	
	/**
	 * 分页查询商品小类
	 * @param s_productSmallType
	 * @param pageBean
	 * @return
	 */
	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType s_productSmallType,PageBean pageBean);
	
	/**
	 * 查询商品小类数量
	 * @param s_productSmallType
	 * @return
	 */
	public Long getProductSmallTypeCount(ProductSmallType s_productSmallType);
	
	/**
	 * 保存商品小类
	 * @param productSmallType
	 */
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * 删除商品小类
	 * @param productSmallType
	 */
	public void delete(ProductSmallType productSmallType);
	
	/**
	 * 通过id获取商品小类实体
	 * @param id
	 * @return
	 */
	public ProductSmallType getProductSmallTypeById(int id);
}
