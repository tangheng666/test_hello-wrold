package com.ssd.po;

import java.io.Serializable;

public class Thedrop_down_list implements Serializable {
	
	
	private String id;
	private String name;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Thedrop_down_list [id=" + id + ", name=" + name + "]";
	}

}
