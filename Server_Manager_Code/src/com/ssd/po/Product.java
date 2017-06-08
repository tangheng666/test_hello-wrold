package com.ssd.po;

import java.io.Serializable;
/**
 * 商品信息表
 * @author giga
 *
 */
public class Product  implements Serializable  {

	private static final long serialVersionUID =1L ;
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductprice() {
		return productprice;
	}
	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}
	private String productid ;//商品编号
	private Integer producttype ;//商品类型
	private String productname ;//商品名称
	private String productprice ;//商品价格
	private String descript1; //描述1
	private String url1 ;//图片路径1
	private String descript2 ;//描述2
	private String url2; //图片路径2
	private String descript3; //描述3
	private String url3;//图片路径3
	private String descript4;//描述4
	private String url4; //图片路径4
	private String descript5;//描述5
	private String url5;//图片路径5
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public Integer getProducttype() {
		return producttype;
	}
	public void setProducttype(Integer producttype) {
		this.producttype = producttype;
	}
	public String getDescript1() {
		return descript1;
	}
	public void setDescript1(String descript1) {
		this.descript1 = descript1;
	}
	public String getUrl1() {
		return url1;
	}
	public void setUrl1(String url1) {
		this.url1 = url1;
	}
	public String getDescript2() {
		return descript2;
	}
	public void setDescript2(String descript2) {
		this.descript2 = descript2;
	}
	public String getUrl2() {
		return url2;
	}
	public void setUrl2(String url2) {
		this.url2 = url2;
	}
	public String getDescript3() {
		return descript3;
	}
	public void setDescript3(String descript3) {
		this.descript3 = descript3;
	}
	public String getUrl3() {
		return url3;
	}
	public void setUrl3(String url3) {
		this.url3 = url3;
	}
	public String getDescript4() {
		return descript4;
	}
	public void setDescript4(String descript4) {
		this.descript4 = descript4;
	}
	public String getUrl4() {
		return url4;
	}
	public void setUrl4(String url4) {
		this.url4 = url4;
	}
	public String getDescript5() {
		return descript5;
	}
	public void setDescript5(String descript5) {
		this.descript5 = descript5;
	}
	public String getUrl5() {
		return url5;
	}
	public void setUrl5(String url5) {
		this.url5 = url5;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Product [productid=" + productid + ", producttype="
				+ producttype + ", descript1=" + descript1 + ", url1=" + url1
				+ ", descript2=" + descript2 + ", url2=" + url2
				+ ", descript3=" + descript3 + ", url3=" + url3
				+ ", descript4=" + descript4 + ", url4=" + url4
				+ ", descript5=" + descript5 + ", url5=" + url5 + "]";
	} 
	
	
	
}
