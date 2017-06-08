package com.ssd.po;

import java.io.Serializable;

/**
 * 创建菜单实体类
 * @author giga
 *
 */
public class Menu implements Serializable {

	
	private static final long serialVersionUID =1L ;
	private String menuid ;//菜单编号
	private String menuname ;//菜单名称
	private String menuurl ;//菜单对应的路径
	private String parentid ;//父级菜单编号
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getMenuurl() {
		return menuurl;
	}
	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Menu [menuid=" + menuid + ", menuname=" + menuname
				+ ", menuurl=" + menuurl + ", parentid=" + parentid + "]";
	}
	
	  
}
