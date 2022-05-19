package com.briup.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.briup.entity.Order;
import com.briup.entity.OrderProduct;
import com.briup.entity.PageBean;
import com.briup.entity.Product;
import com.briup.entity.ProductBigType;
import com.briup.entity.ProductSmallType;
import com.briup.entity.ShoppingCart;
import com.briup.entity.ShoppingCartItem;
import com.briup.entity.User;
import com.briup.service.OrderService;
import com.briup.util.DateUtil;
import com.briup.util.NavUtil;
import com.briup.util.ResponseUtil;
import com.briup.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ����Action��
 * @author Administrator
 *
 */
@Controller
public class OrderAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	/**
	 * ����Service
	 */
	@Resource
	private OrderService orderService;
	
	private Order s_order; // ������ѯ
	

	
	private String navCode;
	
	private String mainPage; // ��ҳ
	
	private List<Order> orderList; // ��������
	
	private int status; // ����״̬
	private String orderNo; // ������
	
	private String page;  // ��Ʒ �ڼ�ҳ
	private String rows;
	private Long total; // ��Ʒ �ܼ�¼��
	
	private String orderNos;
	
	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	
	

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
	

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}
	
	

	public Order getS_order() {
		return s_order;
	}

	public void setS_order(Order s_order) {
		this.s_order = s_order;
	}
	
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	

	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	

	public String getOrderNos() {
		return orderNos;
	}

	public void setOrderNos(String orderNos) {
		this.orderNos = orderNos;
	}

	/**
	 * ���涩��
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		//��ȡSession
		HttpSession session=request.getSession();
		Order order=new Order();
		//��Session�л�ȡ��ǰ�û�
		User currentUser=(User) session.getAttribute("currentUser");
		//���ö���
		order.setUser(currentUser);
		order.setCreateTime(new Date());
		order.setOrderNo(DateUtil.getCurrentDateStr());
		
		ShoppingCart shoppingCart=(ShoppingCart)session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		
		List<OrderProduct> orderProductList=new LinkedList<OrderProduct>();
		
		float cost=0;
		//�������ﳵ��
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			Product product=shoppingCartItem.getProduct();
			OrderProduct orderProduct=new OrderProduct();
			orderProduct.setNum(shoppingCartItem.getCount());
			orderProduct.setOrder(order);
			orderProduct.setProduct(product);
			cost+=product.getPrice()*shoppingCartItem.getCount();
			orderProductList.add(orderProduct);
			
		}
		//��������Ʒ���뵽����
		order.setCost(cost);
		order.setStatus(1);
		order.setOrderProductList(orderProductList);
		navCode=NavUtil.genNavCode("����");
		mainPage="shopping/shopping-result.jsp";
		//���涩��
		orderService.saveOrder(order);
		

		// ��չ��ﳵ
		session.removeAttribute("shoppingCart");
		return SUCCESS;
	}
	
	/**
	 * ��ѯ����
	 * @return
	 * @throws Exception
	 */
	public String findOrder()throws Exception{
		HttpSession session=request.getSession();
		//��Session�л�ȡ��ǰ�û�
		User currentUser=(User) session.getAttribute("currentUser");
		if(s_order==null){
			s_order=new Order();
		}
		s_order.setUser(currentUser);
		orderList=orderService.findOrderList(s_order,null);
		navCode=NavUtil.genNavCode("��������");
		mainPage="userCenter/orderList.jsp";
		return "orderList";
	}
	
	/**
	 * ȷ���ջ�
	 * @return
	 * @throws Exception
	 */
	public String confirmReceive()throws Exception{
		orderService.updateOrderStatus(status,orderNo);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��ѯ��������
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Order> orderList=orderService.findOrderList(s_order, pageBean);
		long total=orderService.getOrderCount(s_order);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderProductList"});
		jsonConfig.registerJsonValueProcessor(User.class, new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(orderList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ͨ�������Ų�ѯ��Ʒ����
	 * @return
	 * @throws Exception
	 */
	public String findProductListByOrderId()throws Exception{
		if(StringUtil.isEmpty(orderNo)){
			orderNo = request.getParameter("id");
			if(StringUtil.isEmpty(orderNo)){
				return null;
			}
		}
		Order order=orderService.getOrderById(Integer.parseInt(orderNo));
		List<OrderProduct> orderProductList=order.getOrderProductList();
		JSONObject result=new JSONObject();
		JSONArray rows=new JSONArray();
		for(OrderProduct orderProduct:orderProductList){
			Product product=orderProduct.getProduct();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("productName", product.getName());
			jsonObject.put("proPic", product.getProPic());
			jsonObject.put("price", product.getPrice());
			jsonObject.put("num", orderProduct.getNum());
			jsonObject.put("subtotal", product.getPrice()*orderProduct.getNum());
			rows.add(jsonObject);
		}
		result.put("rows", rows);
		result.put("total", rows.size());
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * �޸Ķ���״̬
	 * @return
	 * @throws Exception
	 */
	public String modifyOrderStatus()throws Exception{
		String []orderNosStr=orderNos.split(",");
		for(int i=0;i<orderNosStr.length;i++){
			orderService.updateOrderStatus(status, orderNosStr[i]);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
