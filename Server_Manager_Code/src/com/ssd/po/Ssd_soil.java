package com.ssd.po;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.ssd.controller.validation.ValidGroup1;
/**
 * 创建土地实体类
 * @author giga
 *
 */

public class Ssd_soil extends Base  implements Serializable{
	
	private static final long serialVersionUID =1L ;
	
	
	private String soil_id; //土地编号
	

	
	@NotBlank(message="{soil.soil_title}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil.soil_title.illegal}")
	private String soil_title ;//土地标题

	private String soil_ad_folder; //保存广告图片文件夹的名字
	
	public String getSoil_ad_folder() {
		return soil_ad_folder;
	}
	public void setSoil_ad_folder(String soil_ad_folder) {
		this.soil_ad_folder = soil_ad_folder;
	}
	public String getSoil_detail_folder() {
		return soil_detail_folder;
	}
	public void setSoil_detail_folder(String soil_detail_folder) {
		this.soil_detail_folder = soil_detail_folder;
	}
	private String soil_detail_folder; //保存详情图片文件夹的名字
	
	private Integer soil_type ;//土地类型
   
	
    
	private Float soil_area ;//土地面积
   
	private Float soil_remain; // 5.土地剩余可售面积
	
	private Integer soil_state ; //土地状态
	
	
	@NotBlank(message="{soil_publish_date}")	
	//@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil.soil_title.illegal}")
	private String soil_publish_date ;//土地发布时间
	
	
	@NotBlank(message="{soil_update_date}",groups={ValidGroup1.class})
	private String soil_update_date ;//土地更新时间
	
	

	private Integer soil_expires ;//土地租凭期限
	
	
	private Float soil_price ;//土地租用价格
	@NotBlank(message="{soil_province}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil_province_illegal}")
	private String soil_province ;//土地省份
	
	@NotBlank(message="{soil_urban_area}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$", message="{soil_urban_area_illegal}")
	private String soil_urban_area ;//土地市区
	
	
	@NotBlank(message="{soil_detailed_address}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil_detailed_address_illegal}")
	private String soil_detailed_address ;//土地详细地址
	
	
	@NotBlank(message="{soil_phone}")
	@Pattern(regexp="([0-9]{3})+-([0-9]{4})+-([0-9]{4})",message="{soil_phone_format}")
	private String soil_phone ;//土地联系电话
	
	
	@NotBlank(message="{soil_description}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil_description_illegal}")
	private String soil_description ;//土地详细描述
	
	private Integer soil_property ;//土地性质
	
	private Integer soil_water_state ;//土地水资源状态
     
	@NotBlank(message="{soil_traffic}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil_traffic_format}")
	private String soil_traffic ;//土地周围交通情况
	
	
	@NotBlank(message="{soil_life_mating}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil_life_mating_format}")
	private String soil_life_mating ;//土地生活配套
	
	@NotBlank(message="{soil_surrounding}")
	@Pattern(regexp="^(?:[\\u4e00-\\u9fa5]*\\w*\\s*)+$",message="{soil_surrounding_format}")
	private String soil_surrounding ;//周围环境
	
	
	private String soil_ad_url; //广告栏图片路径
	
	public Float getSoil_remain() {
		return soil_remain;
	}
	public void setSoil_remain(Float soil_remain) {
		this.soil_remain = soil_remain;
	}
	private String soil_detail_url; //详情页面图片url
	
	
	private String soil_report; // 土壤检疫报告
	
	
	private String soil_water_report; // 水质检疫报告
	
	
	private String soil_type_report;//资质
	

	
	
	
	
 
	public Integer getSoil_property() {
		return soil_property;
	}
	public void setSoil_property(Integer soil_property) {
		this.soil_property = soil_property;
	}
	public Integer getSoil_water_state() {
		return soil_water_state;
	}
	public void setSoil_water_state(Integer soil_water_state) {
		this.soil_water_state = soil_water_state;
	}
	public String getSoil_traffic() {
		return soil_traffic;
	}
	public void setSoil_traffic(String soil_traffic) {
		this.soil_traffic = soil_traffic;
	}
	public String getSoil_life_mating() {
		return soil_life_mating;
	}
	public void setSoil_life_mating(String soil_life_mating) {
		this.soil_life_mating = soil_life_mating;
	}
	public String getSoil_surrounding() {
		return soil_surrounding;
	}
	public void setSoil_surrounding(String soil_surrounding) {
		this.soil_surrounding = soil_surrounding;
	}
	public String getSoil_ad_url() {
		return soil_ad_url;
	}
	public void setSoil_ad_url(String soil_ad_url) {
		this.soil_ad_url = soil_ad_url;
	}
	public String getSoil_detail_url() {
		return soil_detail_url;
	}
	public void setSoil_detail_url(String soil_detail_url) {
		this.soil_detail_url = soil_detail_url;
	}
	public String getSoil_report() {
		return soil_report;
	}
	public void setSoil_report(String soil_report) {
		this.soil_report = soil_report;
	}
	public String getSoil_water_report() {
		return soil_water_report;
	}
	public void setSoil_water_report(String soil_water_report) {
		this.soil_water_report = soil_water_report;
	}
	public String getSoil_type_report() {
		return soil_type_report;
	}
	public void setSoil_type_report(String soil_type_report) {
		this.soil_type_report = soil_type_report;
	}
	public String getSoil_id() {
		return soil_id;
	}
	public void setSoil_id(String soil_id) {
		this.soil_id = soil_id;
	}
	public String getSoil_title() {
		return soil_title;
	}
	public void setSoil_title(String soil_title) {
		this.soil_title = soil_title;
	}
	public Integer getSoil_type() {
		return soil_type;
	}
	public void setSoil_type(Integer soil_type) {
		this.soil_type = soil_type;
	}
	public Float getSoil_area() {
		return soil_area;
	}
	public void setSoil_area(Float soil_area) {
		this.soil_area = soil_area;
	}
	public Integer getSoil_state() {
		return soil_state;
	}
	public void setSoil_state(Integer soil_state) {
		this.soil_state = soil_state;
	}
	public String getSoil_publish_date() {
		return soil_publish_date;
	}
	public void setSoil_publish_date(String soil_publish_date) {
		this.soil_publish_date = soil_publish_date;
	}
	public String getSoil_update_date() {
		return soil_update_date;
	}
	public void setSoil_update_date(String soil_update_date) {
		this.soil_update_date = soil_update_date;
	}
	public Integer getSoil_expires() {
		return soil_expires;
	}
	public void setSoil_expires(Integer soil_expires) {
		this.soil_expires = soil_expires;
	}
	public Float getSoil_price() {
		return soil_price;
	}
	public void setSoil_price(Float soil_price) {
		this.soil_price = soil_price;
	}
	public String getSoil_province() {
		return soil_province;
	}
	public void setSoil_province(String soil_province) {
		this.soil_province = soil_province;
	}
	public String getSoil_urban_area() {
		return soil_urban_area;
	}
	public void setSoil_urban_area(String soil_urban_area) {
		this.soil_urban_area = soil_urban_area;
	}
	public String getSoil_detailed_address() {
		return soil_detailed_address;
	}
	public void setSoil_detailed_address(String soil_detailed_address) {
		this.soil_detailed_address = soil_detailed_address;
	}
	public String getSoil_phone() {
		return soil_phone;
	}
	public void setSoil_phone(String soil_phone) {
		this.soil_phone = soil_phone;
	}
	public String getSoil_description() {
		return soil_description;
	}
	public void setSoil_description(String soil_description) {
		this.soil_description = soil_description;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public Ssd_soil(String soil_id) {
		super();
		this.soil_id = soil_id;
	}
	public Ssd_soil( ) {
		super();
	}
	@Override
	public String toString() {
		return "Soil [soil_id=" + soil_id + ", soil_title=" + soil_title
				+ ", soil_type=" + soil_type + ", soil_area=" + soil_area
				+ ", soil_state=" + soil_state + ", soil_publish_date="
				+ soil_publish_date + ", soil_update_date=" + soil_update_date
				+ ", soil_expires=" + soil_expires + ", soil_price="
				+ soil_price + ", soil_province=" + soil_province
				+ ", soil_urban_area=" + soil_urban_area
				+ ", soil_detailed_address=" + soil_detailed_address
				+ ", soil_phone=" + soil_phone + ", soil_description="
				+ soil_description + ", soil_property=" + soil_property
				+ ", soil_water_state=" + soil_water_state + ", soil_traffic="
				+ soil_traffic + ", soil_life_mating=" + soil_life_mating
				+ ", soil_surrounding=" + soil_surrounding + ", soil_ad_url="
				+ soil_ad_url + ", soil_detail_url=" + soil_detail_url
				+ ", soil_report=" + soil_report + ", soil_water_report="
				+ soil_water_report + ", soil_type_report=" + soil_type_report
				+ "]";
	}
	
	
	
	

}
