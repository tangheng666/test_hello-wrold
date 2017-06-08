package com.ssd.mapper;

import java.util.List;
import java.util.Map;

import com.ssd.po.Ssd_user;

/**
 * 用户数据访问层
 * @author giga
 *
 */
public interface Ssd_userMapper {
	
	
	/**
	 * 查看用户表下的总记录数
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int GetUser_count(Ssd_user user )throws Exception ;
	
	/**
	 * 添加用户
	 * @param user
	 * @throws Exception
	 */
	public void add_user(Ssd_user user )throws Exception ;
   /**
    * 分页查询用户信息
    * @param user
    * @return
    * @throws Exception
    */
	public List<Ssd_user> GetPage_User(Ssd_user user )throws Exception ;
	/**
	 * 通过主键查询用户对象
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	public Ssd_user get_User(String userid )throws Exception ;
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	public void update_user(Ssd_user user )throws Exception ;
	/**
	 * 删除用户信息
	 * @param userid
	 * @throws Exception
	 */
	public void delete_user(String userid )throws Exception ;
	/**
	 * 用户实现登录的方法
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Ssd_user user_login(Ssd_user user )throws Exception ;
	/**
	 * 判断用户是否存在的方法 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public int query_useruserid_isexists(String userid)throws Exception;
	
	
	/**
	 * 用户快速注册的方法
	 * @param ssd_user
	 * @throws Exception
	 */
	public void user_Quick_register(Ssd_user ssd_user)throws Exception ;
	

}
