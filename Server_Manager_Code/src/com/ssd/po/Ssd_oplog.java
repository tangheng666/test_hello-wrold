package com.ssd.po;

import java.io.Serializable;

public class Ssd_oplog implements Serializable {

	private String op_id ;//该字段使用年月日时分秒再加四个标识值来生成.
	private String sys_id ;
	private String user_id; 
	private String username; 
	private Character type ;
	private String old_value; 
	private String new_value;
	public String getOp_id() {
		return op_id;
	}
	public void setOp_id(String op_id) {
		this.op_id = op_id;
	}
	public String getSys_id() {
		return sys_id;
	}
	public void setSys_id(String sys_id) {
		this.sys_id = sys_id;
	}
 
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Character getType() {
		return type;
	}
	public void setType(Character type) {
		this.type = type;
	}
	public String getOld_value() {
		return old_value;
	}
	public void setOld_value(String old_value) {
		this.old_value = old_value;
	}
	public String getNew_value() {
		return new_value;
	}
	public void setNew_value(String new_value) {
		this.new_value = new_value;
	}
	public Ssd_oplog(String op_id, String sys_id, String user_id,
			String username, Character type, String old_value, String new_value) {
		super();
		this.op_id = op_id;
		this.sys_id = sys_id;
		this.user_id = user_id;
		this.username = username;
		this.type = type;
		this.old_value = old_value;
		this.new_value = new_value;
	}
	public Ssd_oplog() {
		 
	} 
	
	
}
