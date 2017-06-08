package com.ssd.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.controller.Ssd_userController;
import com.ssd.controller.validation.User_Quick_RegisterValidation;
import com.ssd.mapper.Ssd_oplogMapper;
import com.ssd.mapper.Ssd_userMapper;
import com.ssd.po.Ssd_oplog;
import com.ssd.po.Ssd_user;
import com.ssd.service.Ssd_userService;
import com.ssd.tanghengutil.AtomicIntegerUtil;
import com.ssd.tanghengutil.SimpleDateFormatUtil;
import com.ssd.tanghengutil.pageSupport;
import com.ssd.tanghengutil.RedisUtil.UserloginCommonUtil;
import com.ssd.tanghengutil.my3des.SecretUtils;

@Service(Ssd_userService.UserService)
public class Ssd_userServiceImpl implements Ssd_userService {

	@Autowired
	private Ssd_userMapper userMapper;// 用户数据层的接口
	@Autowired
	private Ssd_oplogMapper ssd_oplogmapper;// 日志数据层的接口

	@Autowired
	User_Quick_RegisterValidation user_Quick_RegisterValidation;

	@Autowired
	UserloginCommonUtil userloginCommonUtil;

	ObjectMapper mapper = new ObjectMapper();
	volatile int count = 0;

	// 日志
	Logger logger = Logger.getLogger(Ssd_userServiceImpl.class);
	// 自增
	private static AtomicInteger atomicInteger = new AtomicInteger(1000);;

	@Override
	public int GetUsers_count(Ssd_user user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.GetUser_count(user);
	}

	@Override
	public void add_user(Ssd_user user) throws Exception {
		// SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
		String userid = SimpleDateFormatUtil.GetStringDateNot(new Date())
				+ atomicInteger.getAndIncrement();
		user.setUserid(userid);
		user.setCreatedate(SimpleDateFormatUtil.GetStringDate(new Date()));
		userMapper.add_user(user);

	}

	/**
	 * 实现分页查询用户信息的方法
	 */
	@Override
	public pageSupport<Ssd_user> get_Page_User(Ssd_user user) throws Exception {
		List<Ssd_user> list = userMapper.GetPage_User(user);
		pageSupport<Ssd_user> page = new pageSupport<Ssd_user>();
		page.setData(list);
		page.setTotalRecord(userMapper.GetUser_count(user));
		page.setPageIndex(user.getPageSupport().getPageIndex());
		page.setPageSize(user.getPageSupport().getPageSize());
		return page;
	}

	@Override
	public Ssd_user get_User(String userid) throws Exception {

		return userMapper.get_User(userid);
	}

	@Override
	public void update_user(String userid, Ssd_user user) throws Exception {
		if (userid == null) {
			throw new Exception("修改时，用户编号不能为空 ！");
		}
		userMapper.update_user(user);

	}

	@Override
	public void delete_user(String userid) throws Exception {

		userMapper.delete_user(userid);
	}

	@Override
	public Ssd_user user_login(Ssd_user user) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.user_login(user);
	}

	/**
	 * 用户注册时，查询用户名是否存在
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> query_useruserid_isexists(String userid,
			HttpServletRequest request) throws Exception {

		// return userMapper.query_useruserid_isexists(userid);
		Map<String, String> map = new HashMap<String, String>();
		if (userid == null) {
			logger.info(" 用户注册时，查询用户名是否存在的服务接口(query_useruserid_isexists())：{接口调用时间："
					+ SimpleDateFormatUtil.GetStringDate(new Date())
					+ " 接口的请求参数：userid——"
					+ userid
					+ "  接口的响应结果："
					+ map
					+ "   接口的调用者：" + request.getRemoteAddr());
			throw new Exception("查询的用户名不能为空 ");
		}
		int count = userMapper.query_useruserid_isexists(userid);
		if (count > 0) {
			map.put("retcode", "1003");
			map.put("description", "该号码已经被注册，请重新输入");
		}
		logger.info(" 用户注册时，查询用户名是否存在的服务接口(query_useruserid_isexists())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：userid——"
				+ userid
				+ "   接口的响应结果："
				+ map
				+ "   接口的调用者：" + request.getRemoteAddr());
		return map;
	}

	/*
	 * @Override public void save_user_Quick_register(Ssd_user ssd_user) throws
	 * Exception { userMapper.user_Quick_register(ssd_user);
	 * 
	 * }
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> user_Quick_failure(HttpServletRequest request)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("retcode", "1001");
		map.put("description", "对不起，系统繁忙，请稍后再试。");

		logger.info("用户快速注册失败调用的服务接口(user_Quick_failure())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date()) + " 接口的请求参数："
				+ null + "  接口的响应结果：" + map + "   接口的调用者："
				+ request.getRemoteAddr());
		return map;
	}

	// 快速注册的业务方法和普通注册的业务方法
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 1)
	@Override
	public Map<String, String> save_quick_register(Ssd_user ssd_user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		user_Quick_RegisterValidation.validate(ssd_user, bindingResult);
		if (bindingResult.hasErrors()) {
			/*
			 * String error = bindingResult.getObjectName(); List<ObjectError>
			 * errors=bindingResult.getAllErrors();
			 */
			FieldError error1 = bindingResult.getFieldError();
			// map.put(1004, error1.getDefaultMessage());
			map.put("retcode", "1004");
			map.put("description", error1.getDefaultMessage());

			logger.info("用户快速注册调用的服务接口(save_quick_register())：{接口调用时间："
					+ SimpleDateFormatUtil.GetStringDate(new Date())
					+ " 接口的请求参数：user——" + ssd_user.toString() + "  接口的响应结果："
					+ map + "   接口的调用者：" + request.getRemoteAddr());
			return map;
		} else {
			try {
				count = userMapper.query_useruserid_isexists(ssd_user
						.getUserid());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (count > 0) {
				// map.put(1003, "该号码已经被注册，请重新输入");
				map.put("retcode", "1003");
				map.put("description", "该号码已经被注册，请重新输入");

			} else {
				// ssd_user.setUserid(null);
				/*
				 * Map<Integer, String> resultmap =
				 * this.query_useruserid_isexists(ssd_user.getUserid());
				 */
				try {
					String opid = SimpleDateFormatUtil
							.GetStringDateNot(new Date())
							+ atomicInteger.getAndIncrement();
					// 编写日志对象
					Ssd_oplog ssd_oplog = new Ssd_oplog(opid, null,
							ssd_user.getUserid(), ssd_user.getUsername(), '1',
							null, null);
					// 加密密码
					byte[] pwd = ssd_user.getPassword().getBytes();
					byte[] secretAttr = SecretUtils.encryptMode(pwd);
					ssd_user.setPassword(Base64.encodeBase64String(secretAttr));
					ssd_user.setCreatedate(SimpleDateFormatUtil
							.GetStringDate(new Date()));
					userMapper.user_Quick_register(ssd_user); // 用户注册

					ssd_oplogmapper.add_oplog(ssd_oplog);// 添加日志记录
					// SimpleDateFormat df = new
					// SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
					map.put("retcode", "0");
					map.put("description", "用户注册成功");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("retcode", "1002");
					map.put("description", "操作数据库失败");
					throw new Exception(e);
				}
			}
			logger.info("用户快速注册或者普通注册调用的服务接口(save_quick_register())：{接口调用时间："
					+ SimpleDateFormatUtil.GetStringDate(new Date())
					+ " 接口的请求参数：user——" + ssd_user.toString() + "  接口的响应结果："
					+ map + "   接口的调用者：" + request.getRemoteAddr());
			return map;
		}

	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> IsRegistered(Map<String, String> paraMap,
			HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (paraMap.size() == 0) {
			map.put("retcode", "404");
			map.put("description", "要查询的用户名不能为空。");

		} else {

			count = userMapper.query_useruserid_isexists(paraMap.get("userid"));
			if (count != 0) {
				map.put("retcode", "1");
				map.put("description", paraMap.get("userid") + "该用户名已被使用。");
			} else {
				map.put("retcode", "0");
				map.put("description", paraMap.get("userid") + "该用户名可以使用。");
			}
		}
		logger.info("查询用户是否已注册调用的服务接口(IsRegistered())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Map——" + paraMap + "  接口的响应结果：" + map
				+ "   接口的调用者：" + request.getRemoteAddr());

		return map;
	}

	// 调用用户信息查询接口后，服务中心校验该用户是否已在用户表注册，如果已经注册，则返回相应的结果。如果没有注册，则返回相应的错误信息。
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 1)
	@Override
	public Map<String, String> ShowUserDetail(Map<String, String> para_map,
			HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (para_map.size() == 0) {
			map.put("retcode", "404");
			map.put("description", "用户名或者令牌不能为空。");

		} else {
			// 去查询是否登录
			String value = userloginCommonUtil.checkToken(
					userloginCommonUtil.init(), para_map.get("token"));
			Ssd_user ssd_user = userMapper.get_User(para_map.get("userid"));

			if (ssd_user != null && value != null) {
				map.put("retcode", "0");
				map.put("description", mapper.writeValueAsString(ssd_user));

			} else {
				map.put("retcode", "1001");
				map.put("description", "查询失败。");
			}

		}
		logger.info("查询用户信息调用的服务接口(ShowUserDetail())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Map——" + para_map + "  接口的响应结果：" + map
				+ "   接口的调用者：" + request.getRemoteAddr());

		return map;
	}

	/**
	 * 
	 * 返回map集合 iOS/Android调用用户登录接口后，服务中心校验该用户是否已在用户表注册
	 * 密码是否和用户名是否一致，如果一致，则返回登录成功响应，如果不一致，则返回登录失败。
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> UserLogin(Ssd_user ssd_user,
			HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		if (ssd_user != null) {
			/*
			 * if (userMapper.query_useruserid_isexists(ssd_user.getUserid()) <
			 * 0) { map.put("retcode", "10001"); map.put("description",
			 * ssd_user.getUserid() + "该用户不存在。"); } else {
			 */
			if ("99".equals(ssd_user.getLogintype())) {
				Ssd_user ssd_user2 = userMapper.get_User(ssd_user.getUserid());
				if (ssd_user2 != null) {

					map.put("retcode", "0");
					// map.put("Token", );
					map.put("description", mapper.writeValueAsString(ssd_user2));
					String token = UUID.randomUUID().toString();
					userloginCommonUtil.updateToken(userloginCommonUtil.init(),
							token, ssd_user2, null, null);
					map.put("Token", token);
				} else {
					map.put("retcode", "10001");
					map.put("description", "用户不存在。");
				}

			} else {

				// 把用户的密码加密去数据库对比，
				byte[] pwd = ssd_user.getPassword().getBytes();
				byte[] secretAttr = SecretUtils.encryptMode(pwd);
				ssd_user.setPassword(Base64.encodeBase64String(secretAttr));
				Ssd_user user = userMapper.user_login(ssd_user);
				if (user == null) {
					map.put("retcode", "10002");
					map.put("description", "用户名或者密码有误。");
				} else {

					map.put("retcode", "0");
					// map.put("Token", );
					map.put("description", mapper.writeValueAsString(user));
					String token = UUID.randomUUID().toString();
					userloginCommonUtil.updateToken(userloginCommonUtil.init(),
							token, user, null, null);
					map.put("Token", token);
				}
			}

		}

		// }

		logger.info("用户登录调用的服务接口(UserLogin())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Ssd_user——" + ssd_user.toString() + "  接口的响应结果："
				+ map + "   接口的调用者：" + request.getRemoteAddr());
		return map;
	}

	// 修改用户详细信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 1)
	@Override
	public Map<String, String> ModifyUserDetail(Ssd_user ssd_user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// user_Quick_RegisterValidation.validate(ssd_user, bindingResult);
		if (bindingResult.hasErrors()) {
			/*
			 * String error = bindingResult.getObjectName(); List<ObjectError>
			 * errors=bindingResult.getAllErrors();
			 */
			FieldError error1 = bindingResult.getFieldError();
			// map.put(1004, error1.getDefaultMessage());
			map.put("retcode", "1004");
			map.put("description", error1.getDefaultMessage());

		} else {
			if (ssd_user == null) {
				map.put("retcode", "404");
				map.put("description", "该用户对象或者令牌不能为空。");

			} else {
				String tokenvalue = userloginCommonUtil.checkToken(
						userloginCommonUtil.init(), ssd_user.getToken());
				Ssd_user result_user = userMapper
						.get_User(ssd_user.getUserid());
				if (tokenvalue == null) {
					map.put("retcode", "1001");
					map.put("description", ssd_user.getUserid() + "该用户没有登录");

				} else if (result_user == null) {
					map.put("retcode", "1002");
					map.put("description", ssd_user.getUserid() + "该用户没有不存在");

				} else {
					try {

						String opid = SimpleDateFormatUtil
								.GetStringDateNot(new Date())
								+ atomicInteger.getAndIncrement();
						// 编写日志对象
						Ssd_oplog ssd_oplog = new Ssd_oplog(opid, null,
								result_user.getUserid(),
								result_user.getUsername(), '2',
								result_user.toString(), ssd_user.toString());
						if (ssd_user.getPassword() != null
								&& ssd_user.getPassword() != "") {
							byte[] pwd = ssd_user.getPassword().getBytes();
							byte[] secretAttr = SecretUtils.encryptMode(pwd);
							ssd_user.setPassword(Base64
									.encodeBase64String(secretAttr));
						}
						ssd_user.setLastmodifydate(SimpleDateFormatUtil
								.GetStringDate(new Date()));
						userMapper.update_user(ssd_user);
						ssd_oplogmapper.add_oplog(ssd_oplog);
						map.put("recode", "0");
						map.put("description", ssd_user.getUserid()
								+ "该用户修改成功。");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						map.put("recode", "1003");
						map.put("description", "修改用户时，操作数据库失败。");
					}
				}
			}
		}
		logger.info("修改用户信息调用的服务接口(ModifyUserDetail())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Ssd_user——" + ssd_user.toString() + "  接口的响应结果："
				+ map + "   接口的调用者：" + request.getRemoteAddr());
		return map;
	}
	// 重启密码
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, timeout = 1)
	@Override
	public Map<String, String> ResetPassword(Ssd_user user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		user_Quick_RegisterValidation.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			/*
			 * String error = bindingResult.getObjectName(); List<ObjectError>
			 * errors=bindingResult.getAllErrors();
			 */
			FieldError error1 = bindingResult.getFieldError();
			// map.put(1004, error1.getDefaultMessage());
			map.put("retcode", "10002");
			map.put("description", error1.getDefaultMessage());
		} else {
			Ssd_user resultuser = userMapper.get_User(user.getUserid());
			// 把用户的密码加密去数据库对比，
			byte[] pwd = user.getPassword().getBytes();
			byte[] secretAttr = SecretUtils.encryptMode(pwd);
			user.setPassword(Base64.encodeBase64String(secretAttr));
			if (resultuser != null) {
				// 生成日志对象的原子性id
				String opid = SimpleDateFormatUtil.GetStringDateNot(new Date())
						+ atomicInteger.getAndIncrement();
				// 编写日志对象
				Ssd_oplog ssd_oplog = new Ssd_oplog(opid, null,
						resultuser.getUserid(), resultuser.getUsername(), '2',
						resultuser.getPassword(),
						Base64.encodeBase64String(secretAttr));
				try {
					user.setLastmodifydate(SimpleDateFormatUtil
							.GetStringDate(new Date()));
					userMapper.update_user(user);
					ssd_oplogmapper.add_oplog(ssd_oplog);
					map.put("recode", "0");
					map.put("description", "用户密码重置成功。");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					map.put("recode", "10003");
					map.put("description", "重置密码时，操作数据库失败。");
				}
			} else {
				map.put("recode", "10001");
				map.put("description", "该用户不存在。");
			}
		}
		logger.info("重置密码调用的服务接口(ResetPassword())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：Ssd_user——" + user.toString() + "  接口的响应结果：" + map
				+ "   接口的调用者：" + request.getRemoteAddr());
		return map;
	}
	// 商品添加到购物车
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> AddToCart(Map<String, String> map,
			HttpServletRequest request) throws Exception {
		Map<String, String> result_map = new HashMap<String, String>();
		if (map.size() != 6) {
			result_map.put("retcode", "1001");
			result_map.put("description", "传入本方法的参数有误，请好好检查");
		} else {
			// 查询用户是否存在
			/*
			 * int result = userMapper
			 * .query_useruserid_isexists(map.get("Userid"));
			 */
			// 查询该令牌是否已登陆
			String value = userloginCommonUtil.checkToken(
					userloginCommonUtil.init(), map.get("Token"));
			if (value != null) {
				try {
					userloginCommonUtil.addToCart(userloginCommonUtil.init(),
							map.get("Token"), map.get("Item"),
							Float.valueOf(map.get("Count")),
							Float.valueOf(map.get("Price")), map.get("Title"),
							map.get("Deadline"));
					result_map.put("retcode", "0");
					result_map.put("description", "购物车商品数量更新成功。");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result_map.put("retcode", "1001");
					result_map.put("description", "后台出现异常信息");
				}
			} else {
				result_map.put("retcode", "1001");
				result_map.put("description", "该用户没有登录。");
			}

		}
		logger.info("更新购物车调用的服务接口(AddToCart())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
				+ "   接口的调用者：" + request.getRemoteAddr());
		return result_map;
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	@Override
	public Map<String, String> QueryCart(Map<String, String> map,
			HttpServletRequest request) throws Exception {
		Map<String, String> result_map = new HashMap<String, String>();
		if (map.size() != 2) {
			result_map.put("recode", "1001");
			result_map.put("description", "传入到本接口的参数不正确。");
		} else {
			// 查询用户是否存在
			int result = userMapper
					.query_useruserid_isexists(map.get("Userid"));
			// 查询该令牌是否已登陆
			String value = userloginCommonUtil.checkToken(
					userloginCommonUtil.init(), map.get("Token"));

			if (result != 0 && value != null) {

				Map<String, String> resultmap = userloginCommonUtil
						.queryToCart(userloginCommonUtil.init(),
								map.get("Token"));
				result_map.put("retcode", "0");
				result_map.put("description",
						mapper.writeValueAsString(resultmap));
			} else {
				result_map.put("retcode", "1001");
				result_map.put("description", "用户名不存在或者该用户没有登录。");
			}
		}
		logger.info("查询购物车调用的服务接口(QueryCart())：{接口调用时间："
				+ SimpleDateFormatUtil.GetStringDate(new Date())
				+ " 接口的请求参数：map——" + map + "  接口的响应结果：" + result_map
				+ "   接口的调用者：" + request.getRemoteAddr());
		return result_map;
	}
}
