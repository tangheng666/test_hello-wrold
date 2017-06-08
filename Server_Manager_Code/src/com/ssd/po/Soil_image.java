package com.ssd.po;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
/**
 * 土地图片url表
 * @author giga
 *
 */
public class Soil_image implements Serializable {  
	
	
	
	private  String  soil_image_id;  //土地图片url主键

	private String soil_image_url;//土地图片url 地址
	 private String  soil_id;//属于某个土地
	 
	 
	public String getSoil_image_id() {
		return soil_image_id;
	}
	public void setSoil_image_id(String soil_image_id) {
		this.soil_image_id = soil_image_id;
	}
	public String getSoil_image_url() {
		return soil_image_url;
	}
	public void setSoil_image_url(String soil_image_url) {
		this.soil_image_url = soil_image_url;
	}
	public String getSoil_id() {
		return soil_id;
	}
	public void setSoil_id(String soil_id) {
		this.soil_id = soil_id;
	}
	@Override
	public String toString() {
		return "Soil_image [soil_image_id=" + soil_image_id
				+ ", soil_image_url=" + soil_image_url + ", soil_id=" + soil_id
				+ ", getSoil_image_id()=" + getSoil_image_id()
				+ ", getSoil_image_url()=" + getSoil_image_url()
				+ ", getSoil_id()=" + getSoil_id() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
