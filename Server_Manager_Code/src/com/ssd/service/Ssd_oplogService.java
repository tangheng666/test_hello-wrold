package com.ssd.service;

import com.ssd.po.Ssd_oplog;

/**
 * 日志服务层接口
 * @author giga
 *
 */
public interface Ssd_oplogService {
       static final String ssd_oplogService = "com.ssd.service.Ssd_oplogService" ;
       
   	/**
   	 * 添加一条日志记录
   	 * @param oplog
   	 * @throws Exception
   	 */
   	public void add_oplog(Ssd_oplog oplog)throws Exception;
       

}
