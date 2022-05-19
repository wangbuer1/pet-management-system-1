package com.briup.action;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.briup.entity.PageBean;
import com.briup.entity.Product;
import com.briup.entity.ProductBigType;
import com.briup.entity.ProductSmallType;
import com.briup.service.ProductService;
import com.briup.util.DateUtil;
import com.briup.util.NavUtil;
import com.briup.util.PageUtil;
import com.briup.util.PropertiesUtil;
import com.briup.util.ResponseUtil;
import com.briup.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��ƷAction��
 * @author Administrator
 *
 */
@Controller
public class ProductAction extends ActionSupport  implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	/**
	 * ��Ʒservice
	 */
	@Resource
	private ProductService productService;
	
	/**
	 * ��Ʒ����
	 */
	private List<Product> productList;
	
	
	private Product s_product;
	
	private String page;  // ��Ʒ �ڼ�ҳ
	private String rows;
	private Long total; // ��Ʒ �ܼ�¼��
	private String pageCode; // ��Ʒ ��ҳ����
	
	private String mainPage; // ��ҳ
	
	private String navCode; // ��������
	
	private int productId; // ��Ʒid
	
	private Product product; // ��Ʒ
	
	private File proPic; 
	private String proPicFileName;
	
	private String ids;
	
	
	public File getProPic() {
		return proPic;
	}



	public void setProPic(File proPic) {
		this.proPic = proPic;
	}



	public String getProPicFileName() {
		return proPicFileName;
	}



	public void setProPicFileName(String proPicFileName) {
		this.proPicFileName = proPicFileName;
	}



	public List<Product> getProductList() {
		return productList;
	}



	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}



	public Product getS_product() {
		return s_product;
	}



	public void setS_product(Product s_product) {
		this.s_product = s_product;
	}



	public String getPage() {
		return page;
	}



	public void setPage(String page) {
		this.page = page;
	}



	public Long getTotal() {
		return total;
	}



	public void setTotal(Long total) {
		this.total = total;
	}



	public String getPageCode() {
		return pageCode;
	}



	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}



	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

	


	public String getMainPage() {
		return mainPage;
	}



	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	
	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}

	public String getRows() {
		return rows;
	}



	public void setRows(String rows) {
		this.rows = rows;
	}

	


	public String getIds() {
		return ids;
	}



	public void setIds(String ids) {
		this.ids = ids;
	}



	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//��ʼ��
		//PageΪ��ʱ��Ĭ��1
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		//ÿҳ��ʾ8������
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		productList=productService.findProductList(s_product, pageBean);
		//��ȡ��ҳ��
		total=productService.getProductCount(s_product);
		StringBuffer param=new StringBuffer();
		if(s_product!=null){
			//��ѯ��Ʒ����
			if(s_product.getBigType()!=null){
				param.append("s_product.bigType.id="+s_product.getBigType().getId());
			}
			//��ѯ��ƷС��
			if(s_product.getSmallType()!=null){
				param.append("s_product.smallType.id="+s_product.getSmallType().getId());
			}
			//ģ����ѯ
			if(StringUtil.isNotEmpty(s_product.getName())){
				param.append("s_product.name="+s_product.getName()); 
			}
		}
		navCode=NavUtil.genNavCode("��Ʒ�б�");
		pageCode=PageUtil.genPagination(request.getContextPath()+"/product.action",total, Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")),param.toString());
		mainPage="product/productList.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��ѯ��Ʒ����
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Product> productList=productService.findProductList(s_product, pageBean);
		long total=productService.getProductCount(s_product);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductSmallType.class));
		jsonConfig.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(productList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��ʾ��Ʒ����
	 * @return
	 * @throws Exception
	 */
	public String showProduct()throws Exception{
		product=productService.getProductById(productId);
		this.saveCurrentBrowse(product);
		navCode=NavUtil.genNavCode("��Ʒ����");
		mainPage="product/productDetails.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����������
	 * @param product
	 * @throws Exception
	 */
	private void saveCurrentBrowse(Product product)throws Exception{
		HttpSession session=request.getSession();
		List<Product> currentBrowseProduct=(List<Product>) session.getAttribute("currentBrowse");
		//��������ʱ
		if(currentBrowseProduct==null){
			currentBrowseProduct=new LinkedList<Product>();
		}
		
		boolean flag=true;
		//����������д��ڵ�ǰ��Ʒ��Ϣ,�����
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag=false;
				break;
			}
		}
		//�����������в����ڵ�ǰ��Ʒ��Ϣ,�����
		if(flag){
			currentBrowseProduct.add(0,product);
		}
		//��������ʾ4�����ݣ�ȥͷ��β
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		//�����������뻺��
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	/**
	 * ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		if(proPic!=null){
			String imageName=DateUtil.getCurrentDateStr();
			String realPath=ServletActionContext.getServletContext().getRealPath("/images/product");
			String imageFile=imageName+"."+proPicFileName.split("\\.")[1];
			File saveFile=new File(realPath,imageFile);
			FileUtils.copyFile(proPic, saveFile);
			product.setProPic("images/product/"+imageFile);
		}else if(StringUtil.isEmpty(product.getProPic())){
			product.setProPic("");
		}
		productService.saveProduct(product);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * ɾ����Ʒ
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Product product=productService.getProductById(Integer.parseInt(idsStr[i]));
			productService.deleteProduct(product);								
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ������ƷΪ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String setProductWithHot()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithHot(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ������ƷΪ������Ʒ
	 * @return
	 * @throws Exception
	 */
	public String setProductWithSpecialPrice()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			productService.setProductWithSpecialPrice(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
}
