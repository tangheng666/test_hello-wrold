package com.ssd.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssd.po.Ssd_user;
import com.ssd.tanghengutil.pageSupport;
/**
 * 用户对象  业务逻辑层接口
 * @author giga
 *
 */
public interface Ssd_userService {
	public static final String  UserService = "com.ssd.service.Ssd_userService" ;
	
	/**
	 * 查看用户表下的总记录数
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int GetUsers_count(Ssd_user user )throws Exception ;
	
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
		public pageSupport<Ssd_user> get_Page_User(Ssd_user user )throws Exception ;
		/**
		 * 通过主键查询用户对象
		 * @param userid
		 * @return
		 * @throws Exception
		 */
		public Ssd_user get_User(String  userid )throws Exception ;
		/**
		 * 更新用户信息
		 * @param user
		 * @throws Exception
		 */
		public void update_user(String userid ,  Ssd_user user )throws Exception ;
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
		 * 用户注册时查询用户是否存在
		 * @param username
		 * @return
		 * @throws Exception
		 */
		public Map<String,String> query_useruserid_isexists(String username,HttpServletRequest request )throws Exception;
 
		
		/**
		 * 用户快速注册失败
		 */
		public Map<String, String>   user_Quick_failure(HttpServletRequest request )throws Exception;
		
		
		/**
		 * 用户快速注册的业务方法
		 * @param ssd_user
		 * @param bindingResult
		 * @return
		 * @throws Exception
		 */
		public Map<String, String> save_quick_register(Ssd_user ssd_user ,BindingResult bindingResult,HttpServletRequest request)throws Exception;

		/**
		 * 用户是否注册接口，该接口用于查询用户是否已注册
		 * @param userid
		 * @return
		 * @throws Exception
		 */
		public Map<String, String>  IsRegistered( Map<String, String> paraMap ,HttpServletRequest request)throws Exception;
		
		
		/**
		 * 返回map集合
		 * 查询用户信息接口，该接口用于查询用户详细信息
		 * @param userid
		 * @param token
		 * @return
		 * @throws Exception
		 */
		public Map<String, String> ShowUserDetail(Map<String, String> para_map,HttpServletRequest request )throws Exception;
		
		/**
		 * iOS/Android调用用户信息修改接口后，服务中心校验该用户是否已在用户表注册，
		 * 如果已经注册，则修改用户信息，并返回相应的结果。如果没有注册，则返回相应的错误信息。
		 * @param ssd_user
		 * @param Token
		 * @return
		 * @throws Exception
		 */
	    public Map<String, String> 	ModifyUserDetail( Ssd_user ssd_user,  
				BindingResult bindingResult,HttpServletRequest request)throws Exception;
		
		/**
		 * 返回map集合
		 * iOS/Android调用用户登录接口后，服务中心校验该用户是否已在用户表注册
		 * 密码是否和用户名是否一致，如果一致，则返回登录成功响应，如果不一致，则返回登录失败。
		 * @param ssd_user
		 * @return
		 * @throws Exception
		 */
		public Map<String, String> UserLogin( Ssd_user ssd_user,HttpServletRequest request)throws Exception;

		/**
		 * 用于用户密码重置
		 * 用户中心收到用户重置密码请求后，需要先校验用户ID是否已存在，
		 * 需要重置的密码和确认密码是否一致，如果校验有问题，
		 * 则返回相应的错误码，如果校验通过，则更新用户密码，并返回更新成功信息。
		 * @param user
		 * @param bindingResult
		 * @return
		 * @throws Exception
		 */
		public Map<String, String> ResetPassword(Ssd_user user,
				BindingResult bindingResult,HttpServletRequest request)throws Exception;
   
		/**更新购物车
		 * 购物车更新接口，用于购物车信息更新
		 * @param map
		 * @return
		 * @throws Exception
		 */
		public Map<String,String> AddToCart(Map<String, String> map  , HttpServletRequest request)throws Exception;

		
		/**
		 * 查询购物车
		 * 	 购物车查询接口， 用于购物车信息查询
		 * @param map
		 * @return
		 * @throws Exception
		 */
		public Map<String, String> QueryCart( Map<String, String> map,HttpServletRequest request )throws Exception;


}
