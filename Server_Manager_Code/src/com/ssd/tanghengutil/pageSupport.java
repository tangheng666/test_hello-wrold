package com.ssd.tanghengutil;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author giga
 *  分页帮助类
 * @param <T>
 */
public class pageSupport<T> implements Serializable {
	private int pageIndex;
	private int totalPage;
	private int totalRecord;
	private int pageSize;
	private List<T> data;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getTotalPage() {
		int temp = totalRecord/pageSize;
		return  totalRecord%pageSize==0?temp:temp+1;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	} 
	
	
}