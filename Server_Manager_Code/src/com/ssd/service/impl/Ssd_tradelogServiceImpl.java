package com.ssd.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.mapper.Ssd_tradelogMapper;
import com.ssd.mapper.Ssd_userMapper;
import com.ssd.po.Ssd_tradelog;
import com.ssd.service.Ssd_tradelogService;
import com.ssd.tanghengutil.SimpleDateFormatUtil;
import com.ssd.tanghengutil.pageSupport;
import com.ssd.tanghengutil.RedisUtil.UserloginCommonUtil;

@Service(Ssd_tradelogService.ssd_tradelogService)
public class Ssd_tradelogServiceImpl implements Ssd_tradelogService {

	@Autowired
	private Ssd_tradelogMapper ssd_tradelogMapper; 
	
    @Autowired
	private Ssd_userMapper ssd_userMapper ;
	
	@Autowired
	UserloginCommonUtil userloginCommonUtil;
	
	
	//日志对象
	Logger logger = Logger.getLogger(Ssd_tradelogServiceImpl.class);


	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,timeout=1)
	@Override
	public Map<String, String> QueryOrder(Map<String, String> map,
			HttpServletRequest request) throws Exception {
		Map<String, String> result_map = new HashMap<String,String>();
		if(map.size() <4 ){
			result_map.put("recode", "1001");
			result_map.put("description", "传入到本接口的参数不正确。");
		}else{

			ObjectMapper objectMapper =  new ObjectMapper();
			String result =userloginCommonUtil.checkToken(userloginCommonUtil.init(), map.get("Token"));
			if(result!=null){
			 
					Ssd_tradelog ssd_tradelog= new Ssd_tradelog();
					pageSupport<Ssd_tradelog> pa= new pageSupport<>();
					pa.setPageIndex(Integer.valueOf(map.get("cur_page")));
					pa.setPageSize(Integer.valueOf(map.get("count")));
					
					ssd_tradelog.setPageSupport(pa);
					ssd_tradelog.setTitle(map.get("title"));
					ssd_tradelog.setState(map.get("State")!=null?Integer.valueOf(map.get("State")):null);
					ssd_tradelog.setUser_id(map.get("Userid"));
					
				/*	List<Ssd_tradelog> list = ssd_tradelogMapper.Get_useridOrderInfo(map.get("Userid"),null);*/
					pageSupport<Ssd_tradelog> page = new pageSupport<>();
					page.setData(ssd_tradelogMapper.get_page_orderinfo(ssd_tradelog));
					page.setTotalRecord(ssd_tradelogMapper.Get_OrderinfoCount(ssd_tradelog));
					page.setPageIndex(ssd_tradelog.getPageSupport().getPageIndex());
					page.setPageSize(ssd_tradelog.getPageSupport().getPageSize());
 					
					
					if(page.getData().size()!=0){
						result_map.put("recode", "0");
						result_map.put("description",objectMapper.writeValueAsString(page));
					}else{
						result_map.put("recode", "1001");
						result_map.put("description", "所查询到的订单信息为空。");
					}
			 
			}else{
				result_map.put("recode", "1001");
				result_map.put("description", "该用户未登录。");
			}
		}
		logger.info("订单查询接口，用于订单信息查询的服务接口(QueryOrder())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Map——"
				+ map
				+ "   接口的响应结果："
				+ result_map
				+ "   接口的调用者：" + request.getRemoteAddr());
		return result_map;
	}
 
  
}
