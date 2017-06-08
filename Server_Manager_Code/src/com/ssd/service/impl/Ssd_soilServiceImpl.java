package com.ssd.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.common.PageSupport;
import com.ssd.common.StringHelper;
import com.ssd.mapper.Ssd_soilMapper;
import com.ssd.mapper.Soil_imageMapper;
import com.ssd.mapper.Ssd_tradelogMapper;
import com.ssd.po.Ssd_soil;
import com.ssd.po.Ssd_tradelog;
import com.ssd.service.Ssd_soilService;
import com.ssd.tanghengutil.AtomicIntegerUtil;
import com.ssd.tanghengutil.SimpleDateFormatUtil;
import com.ssd.tanghengutil.RedisUtil.UserloginCommonUtil;

@Service(Ssd_soilService.SoilService)
public class Ssd_soilServiceImpl implements Ssd_soilService {

	@Autowired
	private Ssd_soilMapper ssd_soilMapper;

	@Autowired
	private Soil_imageMapper soil_imageMapper;
	@Autowired
	UserloginCommonUtil userloginCommonUtil;

	@Autowired
	private Ssd_tradelogMapper ssd_tradelogMapper;

	// 转换JSON数据对象
	private ObjectMapper mapper = new ObjectMapper();

	// 订单日志日志对象
	Logger logger = Logger.getLogger(Ssd_soilServiceImpl.class);

	// 自增
	private static AtomicInteger atomicInteger = new AtomicInteger(1000);;

	// 查询单个土地
	/*
	 * @Override public Ssd_soil getSoil(Ssd_soil soil) { // TODO Auto-generated
	 * method stub return ssd_soilMapper.getSoil(soil); }
	 */

	// 新增土地
	@Override
	public void SaveSoil(Ssd_soil soil) {
		// TODO Auto-generated method stub
		ssd_soilMapper.SaveSoil(soil);

	}

	// 修改土地信息
	@Override
	public void UpdateSoil(Ssd_soil soil) {
		// TODO Auto-generated method stub
	}

	/*
	 * // 查询多个土地信息
	 * 
	 * @Override public List<Ssd_soil> findSoil() { // TODO Auto-generated
	 * method stub return soilMapper.findSoil(); }
	 */
	// 查土地总记录数
	/*
	 * @Override public int count() { // TODO Auto-generated method stub return
	 * soilMapper.count(); }
	 */

	// 分页查询土地
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public PageSupport<Ssd_soil> findpaging(Ssd_soil soil) throws Exception {
		// TODO Auto-generated method stub
		PageSupport<Ssd_soil> page = new PageSupport<Ssd_soil>();

		List<Ssd_soil> soil1 = ssd_soilMapper.findpaging(soil);
		for (Ssd_soil soil3 : soil1) {
			soil3.setAdvertising(StringHelper.Break(soil3.getSoil_ad_url())); // 拆分广告图片url
			soil3.setDetails(StringHelper.Break(soil3.getSoil_detail_url())); // 拆分土地详情图片url
		}

		page.setItems(soil1); // 返回查询的值
		page.setPageSize(soil.getCount()); // 页容量
		page.setPageIndex(soil.getCurrent_page());
		page.setTotalCount(ssd_soilMapper.count(soil));

		return page;
	}

	// 删除土地信息
	@Override
	public void DeleteSoil(Ssd_soil soil) {
		// TODO Auto-generated method stub
	}

	// 查询土地详细信息
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> ShowFieldDetail(Ssd_soil soil,
			HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		if (soil == null) {
			result.put("retcode", "1001");
			result.put("description", "传入参数为空。");
		} else {
			Ssd_soil soil2 = ssd_soilMapper.getSoil(soil);

			if (soil2 != null) {
				result.put("description", mapper.writeValueAsString(soil2));
				result.put("retcode", "0");
			} else {
				result.put("retcode", "1001");
				result.put("description", "该土地记录不存在");
			}
		}
		logger.info("查询土地详细信息调用的服务接口(ShowFieldDetail())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Ssd_soil——" + soil + "  接口的响应结果：" + result
				+ "   接口的调用者：" + request.getRemoteAddr());
		return result;
	}

	// 接受的参数有：userID、fieldID、token、type、orderID、size
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 1)
	@Override
	public Map<String, String> TrandeCommit(Map<String, String> map,
			HttpServletRequest request) throws Exception {
		Map<String, String> result_map = new HashMap<String, String>();
		if (map.size() == 0) {
			result_map.put("recode", "90000");
			result_map.put("description", "传入到本接口的参数内容不正确。");
		} else {
			if (Integer.valueOf(map.get("type")) == 1) { // 订单提交流程
				Ssd_soil soil = ssd_soilMapper.getSoil(new Ssd_soil(map
						.get("fieldID")));
				Map<String, String> method_ReturnMap = this.Order_Manager_if(
						map, soil);
				if (method_ReturnMap == null) {

					// 创建一个订单日志对象
					Ssd_tradelog ssd_tradelog = new Ssd_tradelog();

					// 给它的属性赋值
					ssd_tradelog.setArea(Float.valueOf(map.get("size")));
					ssd_tradelog.setCommit_date(SimpleDateFormatUtil
							.GetStringDateNot(new Date()));
					ssd_tradelog.setDetailed_address(soil
							.getSoil_detailed_address());
					ssd_tradelog.setField_id(soil.getSoil_id());
					ssd_tradelog.setOrder_id((SimpleDateFormatUtil
							.GetStringDateNot(new Date()) + atomicInteger
							.getAndIncrement()).trim());
					ssd_tradelog.setPrice(soil.getSoil_price());
					ssd_tradelog.setProvince(soil.getSoil_province());
					// ssd_tradelog.setState(1);
					// 这里的订单状态是通过上面的判断来确定它的值是1
					ssd_tradelog.setState(1);
					ssd_tradelog.setTitle(soil.getSoil_title());
					ssd_tradelog.setType(soil.getSoil_type());
					ssd_tradelog.setUrban_area(soil.getSoil_urban_area());
					ssd_tradelog.setUser_id(map.get("userID"));
					ssd_tradelog.setDeadline(map.get("deadline"));
					ssd_tradelog.setDistinguish(Integer.valueOf(map
							.get("userID").trim()
							.substring(map.get("userID").trim().length() - 2)));
					// 定义一个土地对象
					Ssd_soil updateSoil = new Ssd_soil();
					// 赋值给该对象的属性
					updateSoil.setSoil_id(soil.getSoil_id());
					updateSoil.setSoil_update_date(SimpleDateFormatUtil
							.GetStringDate(new Date()));

					// 判读查询出来的土地对象，土地剩余属性是否有值
					if (soil.getSoil_remain() != null) {
						updateSoil.setSoil_remain(soil.getSoil_remain()
								- Float.valueOf(map.get("size")));
					} else {
						updateSoil.setSoil_remain(soil.getSoil_area()
								- Float.valueOf(map.get("size")));
					}

					try {
						// 查询该订单ID是否存在
						Ssd_tradelog ssd_tradelog2 = ssd_tradelogMapper
								.Get_orderidOrderInfo(ssd_tradelog
										.getOrder_id());
						// 不存在执行添加订单和修改土地信息
						if (ssd_tradelog2 == null) {
							ssd_tradelogMapper.add_Ssd_order(ssd_tradelog);
							ssd_soilMapper.UpdateSoil(updateSoil);
							result_map.put("recode", "0");
							result_map.put("id", ssd_tradelog.getOrder_id());
							result_map.put("description", "订单已成功提交。");
						} else {
							result_map.put("recode", "90000");
							result_map.put("description", "该订单记录数据库已存在。");
						}
					} catch (Exception e) {
						e.printStackTrace();
						result_map.put("recode", "90000");
						result_map.put("description", "系统繁忙，请稍后再试。");
					}

				} else {
					result_map = method_ReturnMap;
				}
				logger.info("用户提交土地交易订单调用的服务接口(TrandeCommit())：{接口调用时间："
						+ SimpleDateFormatUtil.GetStringDate(new Date())
						+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
						+ "   接口的调用者：" + request.getRemoteAddr());
				return result_map;
				// 订单付款流程
			} else if (Integer.valueOf(map.get("type")) == 2) {
				Ssd_soil soil = ssd_soilMapper.getSoil(new Ssd_soil(map
						.get("fieldID")));
				Map<String, String> method_ReturnMap = this.Order_Manager_if(
						map, soil);
				if (method_ReturnMap == null) {
					result_map.put("recode", "0");
					result_map.put("description", "该土地是可以购买的，所有的验证信息通过。");
				} else {
					result_map = method_ReturnMap;
				}
				logger.info("用户提交土地订单付款流程调用的服务接口(TrandeCommit())：{接口调用时间："
						+ SimpleDateFormatUtil.GetStringDate(new Date())
						+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
						+ "   接口的调用者：" + request.getRemoteAddr());
				return result_map;
				// 订单取消流程
			} else if (Integer.valueOf(map.get("type")) == 3) {
				String result1 = userloginCommonUtil.checkToken(
						userloginCommonUtil.init(), map.get("token"));
				if (result1 == null) {
					result_map.put("recode", "10003");
					result_map.put("description", "对不起，该" + map.get("userID")
							+ "还没有登录，请登录后再操作，谢谢。");
				} else {
					//查询到要取消的订单对象
				//	Ssd_tradelog ssd_tradelogquery =ssd_tradelogMapper.Get_orderidOrderInfo(map.get("orderID"));
				
					//查询该订单中购买的土地信息
				/*	Ssd_soil soil  = ssd_soilMapper.getSoil(new Ssd_soil(map.get("fieldID")));
					soil.setSoil_remain(soil.getSoil_remain()+ssd_tradelogquery.getArea());
					soil.setSoil_update_date(SimpleDateFormatUtil.GetStringDate(new Date()));*/
					
					
				/*	Ssd_tradelog ssd_tradelog = new Ssd_tradelog();
					ssd_tradelog.setOrder_id(map.get("orderID")); 
					ssd_tradelog.setComplete_date(SimpleDateFormatUtil
							.GetStringDateNot(new Date()));*/
				//	ssd_tradelog.setState(3);
					try {
						/*ssd_tradelogMapper.update_Ssd_order(ssd_tradelog);
						ssd_soilMapper.UpdateSoil(soil);
						 IN soil_remain float,IN soil_update_date varchar(30),IN soil_id 
		varchar(30) ,IN c int , IN complete_date varchar(20) ,IN order_id varchar(30))
						*/ 
						Map<String, Object> para_map  = new  HashMap<String,Object>();
					    para_map.put("statea",5);
						para_map.put("complete_datea",SimpleDateFormatUtil
								.GetStringDateNot(new Date()) );
						para_map.put("order_ida",map.get("orderID"));
						ssd_soilMapper.Update_Tradelog_And_Soil(para_map); 
						//Update_Tradelog_And_Soil 
						result_map.put("recode", "0");
						result_map.put("description",
								"订单编号为：" + map.get("orderID") + "的订单已取消。");
					} catch (Exception e) {
						e.printStackTrace();
						result_map.put("recode", "90000");
						result_map.put("description", "更新订单记录时，操作数据库失败。");
					}

				}
				logger.info("用户取消交易订单调用的服务接口(TrandeCommit())：{接口调用时间："
						+ SimpleDateFormatUtil.GetStringDate(new Date())
						+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
						+ "   接口的调用者：" + request.getRemoteAddr());
				return result_map;
			} else if (Integer.valueOf(map.get("type")) == 8) {// 确认付款流程
				// 创建一个订单日志对象
				Ssd_tradelog ssd_tradelog = new Ssd_tradelog(); 
				ssd_tradelog.setOrder_id(map.get("orderID"));
				ssd_tradelog.setComplete_date(SimpleDateFormatUtil
						.GetStringDateNot(new Date()));
				ssd_tradelog.setState(2);
				try {
					String filename = map.get("orderID")+"_"+SimpleDateFormatUtil
							.GetStringDateNot(new Date())
							+ "_"
							+ request.getRemoteHost()
							+ "_"
							+ request.getRemoteAddr();
					File file = new File(request.getServletContext()
							.getRealPath("/logs/All_OrderlogInfo/")
							+ filename
							+ ".unl");
					if (!file.exists()) {
						file.createNewFile();
					}
					Ssd_tradelog ssd_tradelog2 = ssd_tradelogMapper
							.Get_orderidOrderInfo(map.get("orderID"));
					FileWriter fileWriter = new FileWriter(
							file.getAbsoluteFile());
					BufferedWriter writer = new BufferedWriter(fileWriter);
					writer.write(ssd_tradelog2.toString());
					writer.close();
					ssd_tradelogMapper.update_Ssd_order(ssd_tradelog);
					result_map.put("recode", "0");
					result_map.put("description", "交易成功");
				} catch (Exception e) {
					e.printStackTrace();
					result_map.put("recode", "90000");
					result_map.put("description",
							"更新订单记录时，操作数据库失败或者记录话单信息文件写入失败。");
				}
				logger.info("用户确认订单付款流程调用的服务接口(TrandeCommit())：{接口调用时间："
						+ SimpleDateFormatUtil.GetStringDate(new Date())
						+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
						+ "   接口的调用者：" + request.getRemoteAddr());
				return result_map;
			}
		}
		// 输出日志
		logger.info("调用本服务接口传入参数不对(TrandeCommit())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
				+ "   接口的调用者：" + request.getRemoteAddr());
		return result_map;
	}

	// 用于用户提交订单和付款交易的条件判断
	private Map<String, String> Order_Manager_if(Map<String, String> map,
			Ssd_soil soil) {
		Map<String, String> result_map = new HashMap<String, String>();

		// a) 校验用户Token信息，如果用户Token信息存在，则继续下一步；
		String result1 = userloginCommonUtil.checkToken(
				userloginCommonUtil.init(), map.get("token"));

		if (result1 == null) {
			result_map.put("recode", "10003");
			result_map.put("description", "对不起，该" + map.get("userID")
					+ "还没有登录，请登录后再操作，谢谢。");
			return result_map;
		}
		// b) 检验土地ID是否存在，如果存在，则继续下一步；
		// Soil soil = soilMapper.getSoil(new Soil(map.get("fieldID")));
		if (soil == null) {
			result_map.put("recode", "10001");
			result_map.put("description", "对不起，所选土地已下架，请重新选择。");
			return result_map;
		}
		// c) 检验土地状态，如果是可售状态，则继续下一步； 这里是获取代表土地状态的数字， 0代表已售完 1代表未售完
		Integer result2 = soil.getSoil_state();
		if (result2 != 1) {
			result_map.put("recode", "10004");
			result_map.put("description", "对不起，所选土地已售完，请重新选择。");
			return result_map;

		}
		// d) 检验土地库存是否足够，如果库存足够，则继续下一步；
		Float sy = soil.getSoil_remain();
		if (Integer.valueOf(map.get("type"))!= 2) {

			if (sy != null) {
				if (soil.getSoil_remain() <= Float.valueOf(map.get("size"))) {

					result_map.put("recode", "10002");
					result_map.put("description", "对不起，所选土地库存不足，请重新选择。");
					return result_map;

				}
			} else {
				if (soil.getSoil_area() <= Float.valueOf(map.get("size"))) {

					result_map.put("recode", "10002");
					result_map.put("description", "对不起，所选土地库存不足，请重新选择。");
					return result_map;

				}
			}

		}

		return null;

	}

	/**
	 * 分页查询用户信息
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> ShowFieldinfo(Ssd_soil soil,
			HttpServletRequest request) throws Exception {
		Map<String, String> result = new HashMap<String, String>();
		PageSupport<Ssd_soil> page = new PageSupport<Ssd_soil>();
		List<Ssd_soil> soil1 = ssd_soilMapper.findpaging(soil);
		if (soil1 != null) {
			for (Ssd_soil soil3 : soil1) {
				soil3.setAdvertising(StringHelper.Break(soil3.getSoil_ad_url())); // 拆分广告图片url
				soil3.setDetails(StringHelper.Break(soil3.getSoil_detail_url())); // 拆分土地详情图片url
			}
			page.setItems(soil1); // 返回查询的值
			page.setPageSize(soil.getCount()); // 页容量
			page.setPageIndex(soil.getCurrent_page());
			page.setTotalCount(ssd_soilMapper.count(soil));
			result.put("retcode", "0");
			result.put("description", mapper.writeValueAsString(page));

		} else {
			result.put("retcode", "1001");
			result.put("description", "该土地信息不存在。");
		}

		logger.info("分页查询土地信息调用的服务接口(ShowFieldinfo())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Soil——" + soil + "  接口的响应结果：" + result
				+ "   接口的调用者：" + request.getRemoteAddr());
		return result;
	}

	@Override
	public Ssd_soil getSoil(Ssd_soil soil_id) {
		// TODO Auto-generated method stub
		return ssd_soilMapper.getSoil(soil_id);
	}
}
