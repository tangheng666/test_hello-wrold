package com.ssd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssd.po.Ssd_tradelog;
import com.ssd.service.Ssd_tradelogService;

/**
 * 订单的信息管理控制类
 * 
 * @author giga
 * 
 */
@Controller
@RequestMapping("/ssd_tradelog")
public class Ssd_tradelogController {

	@Autowired
	Ssd_tradelogService ssd_tradelogService;

	/**
	 * 一.加购 1.向redis中更新用户的购物车数据 2.还可以查询购物车数据
	 * redis存用户购物车用Hash集合，集合名是cart:前缀加上该登录用户的Token，key是土地ID，value是要购买的土地面积 二.提交
	 * 1.去先查询该土地的面积是否够该用户购买，如果不够给出提示，如果够， 先去更新该土地的可购买面积，
	 * 
	 * 然后记录订单日志，
	 * 
	 * 三.付钱
	 */

	/**
	 * 查询我的订单
	 * 
	 * 调用订单查询请求，则返回用户的历史订单信息。
	 * 
	 * @param map
	 *            [Userid,Token,State]
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryOrder.action")
	@ResponseBody
	public Map<String, String> QueryOrder(@RequestBody Map<String, String> map,
			HttpServletRequest request) throws Exception {

		return ssd_tradelogService.QueryOrder(map, request);
	}

}
