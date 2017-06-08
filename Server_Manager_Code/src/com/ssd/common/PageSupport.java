package com.ssd.common;

import java.util.ArrayList;
import java.util.List;

public class PageSupport<T>   {  //分页帮助类
	
	
	private Integer pageIndex;  //当前页
	
	private Integer pageCount; //总页数
	
	private Integer pageSize;//页大小 每页记录数
	
	private  List<T> items=new ArrayList<T>();  //保存的数据
	
	private  Integer totalCount; //总记录数
	
	private Integer num=5;//显示的页码

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		if(totalCount>0){
			this.totalCount = totalCount;
			pageCount =totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount/pageSize ;
			
		}
		
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	
	
	
	
	

}
