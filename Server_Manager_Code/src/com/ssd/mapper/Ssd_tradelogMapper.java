package com.ssd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssd.po.Ssd_tradelog;
import com.ssd.tanghengutil.pageSupport;

/**
 * 订单信息数据层
 * @author giga
 *
 */
public interface Ssd_tradelogMapper {

	
	/**
	 * 添加订单信息
	 * @param ssd_tradelog  包括所有的要添加的信息
	 * @throws Exception
	 */
	public void add_Ssd_order(Ssd_tradelog ssd_tradelog  )throws Exception;
	
	
	
	
	
	/**
	 * 修改订单信息
	 * @param ssd_tradelog   包括要修改的内容和修改的条件
	 * @throws Exception
	 */
    public void 	update_Ssd_order(Ssd_tradelog ssd_tradelog)throws Exception;
	
	
	/**
	 * 删除订单信息
	 * @param orderid   订单编号
 	 * @param userid    用户编号
	 * @throws Exception 
	 */
    public void delete_Ssd_order(@Param(value = "orderid") String orderid  )throws Exception ;
	
	
	/**
	 * 分页查询订单 信息 
	 * @param pageSupport   
	 * @return
	 * @throws Exception
	 */
    public List<Ssd_tradelog >  get_page_orderinfo(Ssd_tradelog ssd_tradelog )throws Exception;
     
	/**
	 * 通过用户编号查询该用户的订单信息
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public List<Ssd_tradelog> Get_useridOrderInfo(@Param(value="userid") String userid,@Param(value="state") Integer state )throws Exception; 
	
	/**
	 * 通过指定订单ID查询订单信息
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public Ssd_tradelog Get_orderidOrderInfo(String orderid)throws Exception;
	
	/**
	 * 查询分页时的总记录数
	 * @param pageSupport   有查询的条件， 
	 * @return
	 * @throws Exception
	 */
	public int Get_OrderinfoCount(Ssd_tradelog ssd_tradelog)throws Exception;
}
