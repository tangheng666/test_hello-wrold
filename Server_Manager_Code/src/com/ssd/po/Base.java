package com.ssd.po;

import java.util.Date;

public class Base {
	private Integer id;
	private Integer retcode; // 响应状态码
 	private String[] Advertising;  //广告图片url
	private String[] details;// 详情图片
 	public Integer getRetcode() {
		return retcode;
	}
	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}
	private Integer current_page;//分页的起始行
	public Integer getCurrent_page() {
		return current_page;
	}
	public void setCurrent_page(Integer current_page) {
		this.current_page = current_page;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	private Integer count;//页显示行数
	private Integer price_min ;//最小价钱
	public Integer getPrice_min() {
		return price_min;
	}
	public void setPrice_min(Integer price_min) {
		this.price_min = price_min;
	}
	public Integer getPrice_max() {
		return price_max;
	}
	public void setPrice_max(Integer price_max) {
		this.price_max = price_max;
	}
	private Integer price_max;//最大价钱
	
	
	private String searchStr;
	
 
	
	public String[] getAdvertising() {
		return Advertising;
	}
	public void setAdvertising(String[] advertising) {
		Advertising = advertising;
	}
	public String[] getDetails() {
		return details;
	}
	public void setDetails(String[] details) {
		this.details = details;
	} 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
}
