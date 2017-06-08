package com.ssd.mapper;

import java.util.List;
import java.util.Map;

import com.ssd.po.Ssd_soil;

public interface Ssd_soilMapper {

	//通过查询条件单个土地
	public Ssd_soil getSoil( Ssd_soil  soil  ); 
	//添加土地
	public void SaveSoil(Ssd_soil soil);
	//修改土地信息
	public void  UpdateSoil(Ssd_soil soil);
	//查询全部土地信息
	public List<Ssd_soil> findSoil();
	//查询总记录数
	//查询总记录数
	public int count(Ssd_soil soil);
	//分页查询
	public List<Ssd_soil> findpaging(Ssd_soil soil)throws Exception;
	//删除土地
	public  void DeleteSoil(Ssd_soil soil);
	
	
	//调用更新订单日志表和更新土地表的存储过程
	public void Update_Tradelog_And_Soil(Map<String, Object> map )throws Exception ;
}
