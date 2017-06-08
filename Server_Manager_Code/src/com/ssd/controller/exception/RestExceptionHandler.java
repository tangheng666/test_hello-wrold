package com.ssd.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler({TypeMismatchException.class})
	@ResponseBody
	public String requestTypeMismatch(TypeMismatchException ex){
	    ex.printStackTrace();
	    return outputJson(-400, "参数类型不匹配,参数" + ex.getPropertyName() + "类型应该为" + ex.getRequiredType());
	}
	//缺少参数异常
	//getParameterName() 缺少的参数名称
	@ExceptionHandler({MissingServletRequestParameterException.class})
	@ResponseBody
	public String requestMissingServletRequest(MissingServletRequestParameterException ex){
	    ex.printStackTrace();
	    return outputJson(-400, "缺少必要参数,参数名称为" + ex.getParameterName());
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class})
	public String messageNoReadableException(HttpMessageNotReadableException ex,Model model ){
		
		
	    ex.printStackTrace();
	    model.addAttribute("statecode", -400);
	    model.addAttribute("message", ex.getMessage());
	    
	    return "/error";
	  //  return outputJson(-400, "缺少必要参数,参数名称为" + ex.g);
	}
	
	@ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
	public String mediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex,Model model ){
		
		
	    ex.printStackTrace();
	    model.addAttribute("statecode", -400);
	    model.addAttribute("message", ex.getMessage());
	    
	    return "/error";
	  //  return outputJson(-400, "缺少必要参数,参数名称为" + ex.g);
	}
	
	
	
	private  String  outputJson(Integer statecode ,String message){
	     Map<Integer, String> map =  new HashMap<Integer, String>();
		map.put(statecode, message);
		ObjectMapper mapper = new ObjectMapper();
		try {
			return  mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}
}
