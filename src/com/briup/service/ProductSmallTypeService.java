package com.briup.service;

import java.util.List;

import com.briup.entity.PageBean;
import com.briup.entity.ProductBigType;
import com.briup.entity.ProductSmallType;

/**
 * ��ƷС��service�ӿ�
 * @author Administrator
 *
 */
public interface ProductSmallTypeService {

	/**
	 * �Ƿ����ָ����Ʒ�������ƷС��
	 * @param bigTypeId
	 * @return
	 */
	public boolean existSmallTypeWithBigTypeId(int bigTypeId);
	
	/**
	 * ��ҳ��ѯ��ƷС��
	 * @param s_productSmallType
	 * @param pageBean
	 * @return
	 */
	public List<ProductSmallType> findProductSmallTypeList(ProductSmallType s_productSmallType,PageBean pageBean);
	
	/**
	 * ��ѯ��ƷС������
	 * @param s_productSmallType
	 * @return
	 */
	public Long getProductSmallTypeCount(ProductSmallType s_productSmallType);
	
	/**
	 * ������ƷС��
	 * @param productSmallType
	 */
	public void saveProductSmallType(ProductSmallType productSmallType);
	
	/**
	 * ɾ����ƷС��
	 * @param productSmallType
	 */
	public void delete(ProductSmallType productSmallType);
	
	/**
	 * ͨ��id��ȡ��ƷС��ʵ��
	 * @param id
	 * @return
	 */
	public ProductSmallType getProductSmallTypeById(int id);
}
