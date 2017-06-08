package com.ssd.po;

import java.io.Serializable;
/**
 * 系统参数表的实体类
 * @author giga
 *
 */
public class Ssd_para implements Serializable{
	private String service_id ;//服务节点ID ，
	private String state ;//节点状态
	private String order_timeout;//订单付款超时时间 
	private String cart_num_lim ;//用户购物车的大小
	private String product_cache ;//商品缓存大小限制
	private String user_token_lim ;//用户令牌数目限制
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOrder_timeout() {
		return order_timeout;
	}
	public void setOrder_timeout(String order_timeout) {
		this.order_timeout = order_timeout;
	}
	public String getCart_num_lim() {
		return cart_num_lim;
	}
	public void setCart_num_lim(String cart_num_lim) {
		this.cart_num_lim = cart_num_lim;
	}
	public String getProduct_cache() {
		return product_cache;
	}
	public void setProduct_cache(String product_cache) {
		this.product_cache = product_cache;
	}
	public String getUser_token_lim() {
		return user_token_lim;
	}
	public void setUser_token_lim(String user_token_lim) {
		this.user_token_lim = user_token_lim;
	}
	

}
