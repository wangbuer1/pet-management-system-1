package com.briup.service;

import java.util.List;

import com.briup.entity.PageBean;
import com.briup.entity.ProductBigType;

/**
 * ��Ʒ����service�ӿ�
 * @author Administrator
 *
 */
public interface ProductBigTypeService {

	/**
	 * ��ѯ������Ʒ����
	 * @return
	 */
	public List<ProductBigType> findAllBigTypeList();
	
	/**
	 * ��ҳ��ѯ��Ʒ����
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	public List<ProductBigType> findProductBigTypeList(ProductBigType s_productBigType,PageBean pageBean);
	
	/**
	 * ��ѯ��Ʒ��������
	 * @param s_productBigType
	 * @return
	 */
	public Long getProductBigTypeCount(ProductBigType s_productBigType);
	
	/**
	 * ������Ʒ����
	 * @param productBigType
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * ɾ����Ʒ����
	 * @param productBigType
	 */
	public void delete(ProductBigType productBigType);
	
	/**
	 * ͨ��id��ȡ��Ʒ����ʵ��
	 * @param id
	 * @return
	 */
	public ProductBigType getProductBigTypeById(int id);
}
