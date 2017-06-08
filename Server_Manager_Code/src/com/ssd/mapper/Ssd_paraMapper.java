package com.ssd.mapper;

import org.apache.ibatis.annotations.Param;

import com.ssd.po.Ssd_para;

public interface Ssd_paraMapper {
	//查询系统参数
	public Ssd_para GetSsd_para(@Param(value="service_id") String serviceid ,@Param(value="state") String state )throws Exception;

}
