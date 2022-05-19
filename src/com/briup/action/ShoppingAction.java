package com.briup.action;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.briup.entity.Product;
import com.briup.entity.ShoppingCart;
import com.briup.entity.ShoppingCartItem;
import com.briup.entity.User;
import com.briup.service.ProductService;
import com.briup.util.NavUtil;
import com.briup.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物Action类
 * @author Administrator
 *
 */
@Controller
public class ShoppingAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品service
	 */
	@Resource
	private ProductService productService;
	
	private HttpServletRequest request;
	
	private int productId; // 商品ID
	private int count; // 商品数量
	
	private String mainPage; // 主页
	private String navCode; // 导航代码
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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
	
	

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 添加购物车商品类
	 * @return
	 * @throws Exception
	 */
	public String addShoppingCartItem()throws Exception{
		//获取Session
		HttpSession session=request.getSession();
		
		JSONObject result=new JSONObject();
	
		Product product=productService.getProductById(productId);
		ShoppingCartItem shoppingCarItem=new ShoppingCartItem();
		
		ShoppingCart shoppingCart=(ShoppingCart)session.getAttribute("shoppingCart");
		//如果购物车为空
		if(shoppingCart==null){
			shoppingCart=new ShoppingCart();
			User currentUser=(User)session.getAttribute("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		if(shoppingCartItemList==null){
			shoppingCartItemList=new LinkedList<ShoppingCartItem>();
		}
		
		boolean flag=true;
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			if(shoppingCartItem.getProduct().getId()==product.getId()){
				shoppingCartItem.setCount(shoppingCartItem.getCount()+1);
				flag=false;
				break;
			}
		}
		
		if(flag){
			shoppingCarItem.setProduct(product);
			shoppingCarItem.setCount(1);
			shoppingCartItemList.add(shoppingCarItem);
		}
		//添加到Session
		session.setAttribute("shoppingCart", shoppingCart);
			
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		
		return null;
	}
	
	/**
	 * 删除一条商品记录
	 * @return
	 * @throws Exception
	 */
	public String removeShoppingCartItem()throws Exception{
		HttpSession session=request.getSession();
		
		JSONObject result=new JSONObject();
		ShoppingCart shoppingCart=(ShoppingCart)session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		for(int i=0;i<shoppingCartItemList.size();i++){
			if(productId==shoppingCartItemList.get(i).getProduct().getId()){
				shoppingCartItemList.remove(i);
				break;
			}
		}
		shoppingCart.setShoppingCartItems(shoppingCartItemList);
		session.setAttribute("shoppingCart", shoppingCart);
		
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 修改购物车商品类
	 * @throws Exception
	 */
	public String updateShoppingCartItem()throws Exception{
		HttpSession session=request.getSession();
		JSONObject result=new JSONObject();
		
		Product product=productService.getProductById(productId);
		//获取购物车
		ShoppingCart shoppingCart=(ShoppingCart)session.getAttribute("shoppingCart");
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		//遍历购物车项
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			if(shoppingCartItem.getProduct().getId()==product.getId()){
				shoppingCartItem.setCount(count);
				break;
			}
		}
		//保存到Session
		session.setAttribute("shoppingCart", shoppingCart);
		
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 直接购买商品
	 * @return
	 * @throws Exception
	 */
	public String buy()throws Exception{
		HttpSession session=request.getSession();
	
		Product product=productService.getProductById(productId);
		ShoppingCartItem shoppingCarItem=new ShoppingCartItem();
		
		ShoppingCart shoppingCart=(ShoppingCart)session.getAttribute("shoppingCart");
		if(shoppingCart==null){
			shoppingCart=new ShoppingCart();
			User currentUser=(User)session.getAttribute("currentUser");
			shoppingCart.setUserId(currentUser.getId());
		}
		List<ShoppingCartItem> shoppingCartItemList=shoppingCart.getShoppingCartItems();
		if(shoppingCartItemList==null){
			shoppingCartItemList=new LinkedList<ShoppingCartItem>();
		}
		
		boolean flag=true;
		for(ShoppingCartItem shoppingCartItem:shoppingCartItemList){
			if(shoppingCartItem.getProduct().getId()==product.getId()){
				shoppingCartItem.setCount(shoppingCartItem.getCount()+1);
				flag=false;
				break;
			}
		}
		
		if(flag){
			shoppingCarItem.setProduct(product);
			shoppingCarItem.setCount(1);
			shoppingCartItemList.add(shoppingCarItem);
		}
		
		session.setAttribute("shoppingCart", shoppingCart);
		
		
		mainPage="shopping/shopping.jsp";
		navCode=NavUtil.genNavCode("购物车");
		return SUCCESS;
	}
	
	/**
	 * 显示购物车商品列表
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		mainPage="shopping/shopping.jsp";
		navCode=NavUtil.genNavCode("购物车");
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

}
