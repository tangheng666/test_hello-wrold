package com.ssd.po;

import java.io.Serializable;
 




 



import javax.validation.constraints.Pattern;
 



import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
 



import com.ssd.controller.validation.ValidGroup_Quick_register;
import com.ssd.controller.validation.ValidGroup_common_reister;
import com.ssd.controller.validation.ValidGroup_updateuserValidation;
import com.ssd.controller.validation.ValidGroup_userloginValidation;
import com.ssd.tanghengutil.pageSupport;
 
/**
 * 创建用户实体类
 * @author giga
 *
 */



public class Ssd_user implements Serializable  {

	private static final long serialVersionUID =1L ;
	//^[`~!@#$%^&*()+=-_|{}':;'",/\[]·.<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]$
	@Pattern(regexp="^(((13[0-9])|(15([0-3]|[5-9]))|(18[0-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$",message="{useridlengthOrformat}",groups={ValidGroup_Quick_register.class,ValidGroup_userloginValidation.class,ValidGroup_common_reister.class,ValidGroup_updateuserValidation.class})
	private String userid ;//用户编号
 
	//`~!@#$%^&*()+=|':;',//[//].<>/?~！@#￥%……&*（）——{}【】‘；：”“’。，、？
	@Pattern(regexp="^[\\u4E00-\\u9FA5A-Za-z0-9]+$",message="{user.username.is.notnull}",groups={ValidGroup_common_reister.class})
	private String username; //用户姓名
	@Pattern(regexp="[^*\"'?]+",message="{user.password.pattern.error}",groups={ValidGroup_Quick_register.class,ValidGroup_common_reister.class,ValidGroup_userloginValidation.class})
	/*@NotBlank(message="{user.password.is.notnull}",groups={ValidGroup_common_reister.class})*/
	@Length(min=6,max=16,message="{user.password.length.error}",groups={ValidGroup_Quick_register.class,ValidGroup_common_reister.class,ValidGroup_userloginValidation.class})
	
	private String password ;//用户密码
	@Pattern(regexp="[^*\"'?]+",message="{user.confirmpassword.pattern.error}",groups={ValidGroup_Quick_register.class,ValidGroup_common_reister.class})
	/*@Register_confirm(password=password)*/
	private String confirmpassword; //用户确认密码
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}


	@Pattern(regexp="[^*\"'?]+",message="{user.address1.pattern.error}",groups={ValidGroup_common_reister.class,ValidGroup_updateuserValidation.class})
/*	@NotBlank(message="{user.address.is.notnull}",groups={ValidGroup_common_reister.class})*/
	private String address1;//家庭地址
	@Pattern(regexp="[^*\"'?]+",message="{user.address2.pattern.error}",groups={ValidGroup_common_reister.class,ValidGroup_updateuserValidation.class})
/*	@NotBlank(message="{user.address.is.notnull}",groups={ValidGroup_common_reister.class})*/
	private String address2; //单位地址
//	@Pattern(regexp="/[`~!@#$%^&*()_+<>?:\"{} ,.\\/;'[\\]]/im",message="{user.all。pattern.error}")
/*	@NotBlank(message="{user.phone.is.notnull}",groups={})*/
	@Pattern(regexp = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$",message="{user.phone.pattern.error}",groups={ValidGroup_updateuserValidation.class })
	private String phone ;//用户电话
	@Pattern(regexp = "^0\\d{2,3}-\\d{7,8}$",message="{user.telephone.pattern.error}",groups={ValidGroup_updateuserValidation.class})
	private String telephone ;//用户固定电话
/*	@NotBlank(message="{user.createdate.is.notnull}")*/
	private String createdate ;//用户创建时间
	private String lastlogindate ;//用户最后一次登录时间
	private String lastmodifydate ;//用户更新时间
/*	@NotBlank(message="{user.identity_card.is.notnull}",groups={ })*/
	@Pattern(regexp="^\\d{18}|\\d{15}$",message="{user.identity_card.length.error}",groups={ValidGroup_updateuserValidation.class})
	private String identity_card ;//用户身份证
//	@NotBlank(message="{user.icon.is.notnull}")
	
	private String icon ;//用户头像路径
 
	private Character  sex ;//用户性别
	/*@NotBlank(message="{user.birthday.notnull}")*/
	
	@Pattern(regexp="[^*\"'?]+",message="{user.birthday.pattern.error}",groups={ValidGroup_common_reister.class})
	private String birthday ;//用户生日 
	@Pattern(regexp="^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$",message="{user.email.pattern.error}",groups={ ValidGroup_common_reister.class,ValidGroup_updateuserValidation.class})
	private String email ;//用户邮箱
	private  Integer usertype ;//用户类型
	private String  rank ;//用户级别
	
	private Float integral ;//用户积分sr
	
	private String allergy_his;//过敏史
	
	private String logintype ; 
	
	
	
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}
	public String getAllergy_his() {
		return allergy_his;
	}
	public void setAllergy_his(String allergy_his) {
		this.allergy_his = allergy_his;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
 
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(String lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	public String getLastmodifydate() {
		return lastmodifydate;
	}
	public void setLastmodifydate(String lastmodifydate) {
		this.lastmodifydate = lastmodifydate;
	}
	public String getIdentity_card() {
		return identity_card;
	}
	public void setIdentity_card(String identity_card) {
		this.identity_card = identity_card;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getUsertype() {
		return usertype;
	}
	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Float getIntegral() {
		return integral;
	}
	public void setIntegral(Float integral) {
		this.integral = integral;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
	
	private String token ;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}


	private pageSupport<Ssd_user> pageSupport ;
	public pageSupport<Ssd_user> getPageSupport() {
		return pageSupport;
	}
	public void setPageSupport(pageSupport<Ssd_user> pageSupport) {
		this.pageSupport = pageSupport;
	}
	@Override
	public String toString() {
		return "Ssd_user [userid=" + userid + ", username=" + username
				+ ", password=" + password + ", confirmpassword="
				+ confirmpassword + ", address1=" + address1 + ", address2="
				+ address2 + ", phone=" + phone + ", telephone=" + telephone
				+ ", createdate=" + createdate + ", lastlogindate="
				+ lastlogindate + ", lastmodifydate=" + lastmodifydate
				+ ", identity_card=" + identity_card + ", icon=" + icon
				+ ", sex=" + sex + ", birthday=" + birthday + ", email="
				+ email + ", usertype=" + usertype + ", rank=" + rank
				+ ", integral=" + integral + ", allergy_his=" + allergy_his
				+ "]";
	}
	
	 
	
	
}
