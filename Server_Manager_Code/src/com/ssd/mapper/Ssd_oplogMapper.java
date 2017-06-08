package com.ssd.mapper;

import com.ssd.po.Ssd_oplog;

/**
 * 日志记录数据层
 * @author giga
 *
 */
public interface Ssd_oplogMapper {
	
	/**
	 * 添加一条日志记录
	 * @param oplog
	 * @throws Exception
	 */
	public void add_oplog(Ssd_oplog oplog)throws Exception;
	
	

}
