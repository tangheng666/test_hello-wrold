package com.ssd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssd.mapper.Ssd_oplogMapper;
import com.ssd.po.Ssd_oplog;
import com.ssd.service.Ssd_oplogService;
/**
 * 业务逻辑层
 * @author giga
 *
 */
@Service(Ssd_oplogService.ssd_oplogService)
public class Ssd_oplogServiceImpl implements Ssd_oplogService {

	@Autowired
	private Ssd_oplogMapper ssd_oplogmapper;
	
	@Override
	public void add_oplog(Ssd_oplog oplog) throws Exception {
	//	ssd_oplogmapper.
		
	}

}
