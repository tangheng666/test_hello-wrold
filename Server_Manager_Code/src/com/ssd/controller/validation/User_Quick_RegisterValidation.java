package com.ssd.controller.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors; 

import com.ssd.po.Ssd_user;
/**
 * 用户快速注册，验证类
 * @author giga
 *
 */
@Component("user_Quick_RegisterValidation")
public class User_Quick_RegisterValidation {
	public boolean supports(Class<?> klass) {
		 return Ssd_user.class.isAssignableFrom(klass);
		 }

		 public void validate(Object target, Errors errors) {
			 Ssd_user registration = (Ssd_user) target;
	/*	 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userid",
		 "NotEmpty.registration.userid",
		 "请输入11位的数字。");
		 String userName = registration.getUserid();
		 if ((userName.length()) > 50) {
		 errors.rejectValue("userName",
		 "lengthOfUser.registration.userName",
		 "User Name must not more than 50 characters.");
		 }*/
		 if (!(registration.getPassword()).equals(registration
		 .getConfirmpassword())) {
		 errors.rejectValue("password",
		 "matchingPassword.registration.password",
		 "密码和确认密码需要一致，请重新输入。");
		 }
		 }
		}
