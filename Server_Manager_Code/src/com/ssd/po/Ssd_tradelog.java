package com.ssd.po;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.ssd.controller.validation.ValidGroupAddOrderValidation;
import com.ssd.controller.validation.ValidGroup_Quick_register;
import com.ssd.controller.validation.ValidGroup_common_reister;
import com.ssd.controller.validation.ValidGroup_updateuserValidation;
import com.ssd.controller.validation.ValidGroup_userloginValidation;
import com.ssd.tanghengutil.pageSupport;

/**
 * 订单记录日志实体类
 * @author giga
 *
 */
public class Ssd_tradelog    implements Serializable  {
	@Pattern(regexp="^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$",message="{orderl.user_id.error}",groups={ValidGroupAddOrderValidation.class })
	private String user_id ;//用户ID
	private String order_id ;//订单ID
	private Integer state ;//订单状态
	private String commit_date ;//提交时间
	private String complete_date ;//订单完成或取消时间
	
	private String field_id; //土地编号
	private String title ;//土地标题
	private Integer type ;//土地类型
	private Float area ;//土地面积
	private Float price ;//土地价格
	private String province ;//土地省份
	private String urban_area ; //土地市区
	private String detailed_address ;//土地详细地址
	private String deadline ;
	
	private Integer distinguish; //分区字段
	
	
	public Integer getDistinguish() {
		return distinguish;
	}
	public void setDistinguish(Integer distinguish) {
		this.distinguish = distinguish;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCommit_date() {
		return commit_date;
	}
	public void setCommit_date(String commit_date) {
		this.commit_date = commit_date;
	}
	public String getComplete_date() {
		return complete_date;
	}
	public void setComplete_date(String complete_date) {
		this.complete_date = complete_date;
	}
	public String getField_id() {
		return field_id;
	}
	public void setField_id(String field_id) {
		this.field_id = field_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Float getArea() {
		return area;
	}
	public void setArea(Float area) {
		this.area = area;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getUrban_area() {
		return urban_area;
	}
	public void setUrban_area(String urban_area) {
		this.urban_area = urban_area;
	}
	public String getDetailed_address() {
		return detailed_address;
	}
	public void setDetailed_address(String detailed_address) {
		this.detailed_address = detailed_address;
	}
	
	private pageSupport<Ssd_tradelog>  pageSupport;
	
	public pageSupport<Ssd_tradelog> getPageSupport() {
		return pageSupport;
	}
	public void setPageSupport(pageSupport<Ssd_tradelog> pageSupport) {
		this.pageSupport = pageSupport;
	}
	@Override
	public String toString() {
		return  user_id + "|" + order_id
				+  "|" + state +  "|" + commit_date
				+  "|"+ complete_date + "|" + field_id
				+  "|" + title +  "|" + type + "|"+ area
				+  "|" + price + "|" + province
				+ "|" + urban_area + "|"
				+ detailed_address ;
	}
	 
	
	
	
	

}
