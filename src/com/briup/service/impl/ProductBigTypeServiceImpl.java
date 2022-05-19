package com.briup.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.briup.dao.BaseDAO;
import com.briup.entity.PageBean;
import com.briup.entity.ProductBigType;
import com.briup.service.ProductBigTypeService;
import com.briup.util.StringUtil;

/**
 * 商品大类实现类
 * @author Administrator
 *
 */
//service注解
@Service("productBigTypeService")
public class ProductBigTypeServiceImpl  implements  ProductBigTypeService{
	//泛型注入
	@Resource
	private BaseDAO<ProductBigType> baseDAO;
	
	


	//查询商品大类
	public List<ProductBigType> findAllBigTypeList() {
		return baseDAO.find(" from ProductBigType");
	}
	//分页查询商品大类
	public List<ProductBigType> findProductBigTypeList(
			ProductBigType s_productBigType, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from ProductBigType");
		if(s_productBigType!=null){
			if(StringUtil.isNotEmpty(s_productBigType.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_productBigType.getName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);			
		}
	}
	//获取商品大类数量
	public Long getProductBigTypeCount(ProductBigType s_productBigType) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from ProductBigType");
		if(s_productBigType!=null){
			if(StringUtil.isNotEmpty(s_productBigType.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_productBigType.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	//保存
	public void saveProductBigType(ProductBigType productBigType) {
		baseDAO.merge(productBigType);
	}
	//删除
	public void delete(ProductBigType productBigType) {
		// TODO Auto-generated method stub
		baseDAO.delete(productBigType);
	}

	//通过ID查询商品大类
	public ProductBigType getProductBigTypeById(int id) {
		return baseDAO.get(ProductBigType.class, id);
	}

}
