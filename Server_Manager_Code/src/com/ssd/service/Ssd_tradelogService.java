package com.ssd.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;

import com.ssd.po.Ssd_tradelog;

/**
 * 订单信息管理业务接口
 * @author giga
 *
 */
public interface Ssd_tradelogService {
	
	public static String ssd_tradelogService = "com.ssd.service.Ssd_tradelogService" ;
	
	/**
	 * 添加订单信息
	 * @param ssd_tradelog
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	//public Map<String, String> Add_OrderInfo(Ssd_tradelog ssd_tradelog ,BindingResult bindingResult ,HttpServletRequest request)throws Exception;
	
	
	
	//public 
	
	
	//修改订单信息
	
	
	
	//删除订单信息
	
	
	//分页查询订单 信息
	
	//通过用户编号查询该用户的订单信息
	
	
	//通过指定订单ID查询订单信息
	
	
	
	/**
	 * 查看我的订单
	 * 订单查询接口，用于订单信息查询     	
	 * @param map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> QueryOrder(Map<String, String>  map ,HttpServletRequest request)throws Exception;
	
	

}
