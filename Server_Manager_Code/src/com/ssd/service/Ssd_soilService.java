package com.ssd.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ssd.common.PageSupport;
import com.ssd.po.Ssd_soil;

public interface Ssd_soilService {
    public static final String SoilService ="com.ssd.service.Ssd_soilService";
    
   //查询单个土地
    public Ssd_soil getSoil(Ssd_soil soil_id); 
    
    //新增土地
    public void SaveSoil(Ssd_soil soil);
    
    //修改土地信息
  	public void  UpdateSoil(Ssd_soil soil);
  	//分页查询
  	public PageSupport<Ssd_soil> findpaging(Ssd_soil soil ) throws Exception ;
  	//删除土地
  	public  void DeleteSoil(Ssd_soil soil);

  	/**
  	 * 查询土地详细信息   参数：土地编号
  	 * @param soil
  	 * @param request
  	 * @return  返回Map集合
  	 * @throws Exception
  	 */
  	public Map<String, String> ShowFieldDetail(Ssd_soil soil , HttpServletRequest request)throws Exception;
  	//-----------------------------------
  	/**
  	 * 土地交易功能
  	 * iOS/Android调用土地交易接口后，服务中心校验该土地库存是否足够，如果足够，则减掉相应的库存。
  	 * @param map
  	 * @param request
  	 * @return
  	 * @throws Exception
  	 */
  	public Map<String, String> TrandeCommit(Map<String,String> map,HttpServletRequest request )throws Exception;
  	
    /**
     * 分页查询， 输入参数有： pageCount ，Current_page ,province ,urban_area , Price_min ,Price_max,state
     * 查询土地概要信息
     * @param soil
     * @param request
     * @return
     * @throws Exception
     */
  	public Map<String, String> ShowFieldinfo(Ssd_soil soil, HttpServletRequest request )throws Exception;
    

}
