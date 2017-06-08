package com.ssd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssd.mapper.Ssd_paraMapper;
import com.ssd.po.Ssd_para;
import com.ssd.service.Ssd_paraService;
@Service(Ssd_paraService.ssd_paraService)
public class Ssd_paraServiceImpl  implements Ssd_paraService{

	@Autowired
	private Ssd_paraMapper ssd_paraMapper ;
	
	@Override
	public Ssd_para GetSsd_para(String serviceid, String state)
			throws Exception {
		// TODO Auto-generated method stub
		return ssd_paraMapper.GetSsd_para(serviceid, state);
	}

}
