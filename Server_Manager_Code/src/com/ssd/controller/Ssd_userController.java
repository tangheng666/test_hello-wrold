package com.ssd.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ssd.controller.validation.ValidGroup_Quick_register;
import com.ssd.controller.validation.ValidGroup_common_reister;
import com.ssd.controller.validation.ValidGroup_updateuserValidation;
import com.ssd.controller.validation.ValidGroup_userloginValidation;
import com.ssd.po.Ssd_user;
import com.ssd.service.Ssd_userService;

@Controller
@RequestMapping("/ssd_user")
@Scope("prototype")
public class Ssd_userController {

	@Autowired
	private Ssd_userService userService;

	@RequestMapping("/MyJsp.action")
	public String getpage() {

		return "/MyJsp";
	}

	@RequestMapping("/login.action")
	public String login() {

		return "/login";
	}

	@RequestMapping("/index.action")
	public String index() {

		return "/index";
	}

	@RequestMapping("/UserDetail.action")
	public String UserDetail() {
		return "/UserDetail";
	}

	@RequestMapping("/register.action")
	public String register() {
		return "/register";
	}

	/*
	*//**
	 * 普通注册
	 * 
	 * @param user
	 * @return, @Validated User users
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/add_user.action", produces =
	 * "text/html;charset=UTF-8") public @ResponseBody String
	 * add_user(@Validated @RequestBody Ssd_user user, BindingResult
	 * bindingResult, Model model, MultipartFile usericonfile,
	 * HttpServletRequest request, HttpServletResponse response) throws
	 * Exception { if (user == null) { return "调用本方法时不能不传入参数"; } ObjectMapper
	 * mapper = new ObjectMapper(); // Map<String, String> map = new
	 * HashMap<String,String>(); if (bindingResult.hasErrors()) {
	 * List<ObjectError> errrors = bindingResult.getAllErrors();
	 * model.addAttribute("errors", errrors);
	 * 
	 * for (ObjectError objectError : errrors) {
	 * System.out.println(objectError.getDefaultMessage()); }
	 * 
	 * map.put("info", "所填信息未通过后台验证器");
	 * 
	 * return map ;
	 * 
	 * // return // . model.addAttribute("user", user); return
	 * mapper.writeValueAsString(errrors); }
	 * 
	 * if(usericonfile.getSize() !=0){ String filepath =
	 * "classpath:WEB-INF/user_icon"; //图片上传成功后，将图片的地址写到数据库 String filepaths =
	 * request.getServletContext().getRealPath("/WEB-INF/user_icon/");
	 * //上传文件原始名称 String originalFilename = usericonfile.getOriginalFilename();
	 * //新的图片名称 String newFilename = UUID
	 * .randomUUID()+originalFilename.substring(originalFilename.lastIndexOf
	 * (".")); //新文件 File file = new File(filepaths+newFilename);
	 * usericonfile.transferTo(file); //图片上传成功，将新图片地址写入数据库
	 * user.setIcon(newFilename); }else {
	 * 
	 * //model.addAttribute("iconerror", "");
	 * 
	 * return "用户头像不能为空" ; //return "/MyJsp" ; }
	 * 
	 * try { // int count = //
	 * userService.query_useruserid_isexists(user.getUsername());
	 * 
	 * if(count >0 ){ return "该用户名已被使用" ; }
	 * 
	 * userService.add_user(user); return "添加成功!";
	 * 
	 * 
	 * map.put("info", "添加成功");
	 * 
	 * return map ;
	 * 
	 * // new HashMap<String,String>().put("info", "添加成功");
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block throw new
	 * Exception(e); }
	 * 
	 * }
	 */

	/*	*//**
	 * 获取用户测试json数据
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping("/getuser.action") public @ResponseBody Ssd_user
	 * getuser() throws Exception { Ssd_user user = new Ssd_user();
	 * user.setUsername("唐衡"); user.setAddress("湖南永州");
	 * user.setBirthday("2000-03-19"); user.setEmail("2605253179@qq.com");
	 * user.setIcon("/WEB-INF/icons/qeqw.img");
	 * user.setIdentity_card("431126200003198478"); user.setIntegral(10F);
	 * user.setPassword("123456789"); user.setPhone("18566403223");
	 * user.setSex('男');
	 * 
	 * return user; }
	 */

	/**
	 * 用户登录的方法
	 * 
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/user_login.action", produces =
	 * "text/html;charset=UTF-8") public @ResponseBody String
	 * user_login(@RequestBody Ssd_user user) throws Exception { if (user ==
	 * null) { throw new Exception("传入信息不能为空!");
	 * 
	 * }
	 * 
	 * Map<String, String> resultmap = new HashMap<String, String>();
	 * 
	 * ObjectMapper mapper = new ObjectMapper(); Ssd_user usera =
	 * userService.user_login(user); String result = null; if (usera == null) {
	 * result = "用户登录失败"; resultmap.put("result", result); } else { result =
	 * mapper.writeValueAsString(usera); String token =
	 * UUID.randomUUID().toString(); resultmap.put("result", result);
	 * resultmap.put("token", token); }
	 * 
	 * return mapper.writeValueAsString(resultmap);
	 * 
	 * }
	 */

	/**
	 * 修改用户的方法
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "update_user.action", produces =
	 * "text/html;charset=UTF-8")
	 * 
	 * @ResponseBody public Map<String, String> update_user(@RequestBody
	 * Ssd_user user) throws Exception { Map<String, String> map = new
	 * HashMap<String, String>();
	 * 
	 * if (user != null) { userService.update_user(user.getUserid(), user);
	 * map.put("info", "用户信息修改成功"); return map; } map.put("info", "用户信息修改失败");
	 * 
	 * return map; }
	 *//**
	 * 删除用户的方法
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "delete_user.action", produces =
	 * "text/html;charset=UTF-8")
	 * 
	 * @ResponseBody public Map<String, String> delete_user(String userid)
	 * throws Exception { Map<String, String> map = new HashMap<String,
	 * String>(); if (userid == null) {
	 * 
	 * map.put("info", "删除用户时要传入用户编号"); return map; }
	 * userService.delete_user(userid); map.put("info", "用户删除成功!");
	 * 
	 * return map;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/getpage_user.action", produces =
	 * "text/html;charset=UTF-8") public @ResponseBody pageSupport<Ssd_user>
	 * get_pageUser(
	 * 
	 * @RequestParam(value = "user", required = false) Ssd_user user) throws
	 * Exception { if (user == null) { throw new Exception("传入的用户对象不能为空"); }
	 * 
	 * pageSupport<Ssd_user> pageSupport = userService.get_Page_User(user);
	 * 
	 * return pageSupport; }
	 */

	/**
	 * 无用 快速注册时要调用的查询接口
	 * 调用查询接口，校验所输入的用户号码是否已经注册，如用户输入的号码已经被注册了，则提示用户：“该号码已经被注册，请重新输入。
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping("/query_username_isexists.action")
	 * 
	 * @ResponseBody public Map<String, String>
	 * query_useruserid_isexists(@RequestBody String userid, HttpServletRequest
	 * request) throws Exception {
	 * 
	 * return userService.query_useruserid_isexists(userid, request);
	 * 
	 * }
	 */

	/**
	 * 快速注册 快速注册
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/quick_register.action")
	@ResponseBody
	public Map<String, String> quick_register(
			@Validated(value = ValidGroup_Quick_register.class) @RequestBody Ssd_user user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {

		Map<String, String> map = userService.save_quick_register(user,
				bindingResult, request);
		// ObjectMapper mapper = new ObjectMapper();
		/*
		 * response.setCharacterEncoding("utf-8");
		 * response.setContentType("text/html;charset=utf-8");
		 * 
		 * PrintWriter pw = response.getWriter();
		 * pw.print(mapper.writeValueAsString(map));
		 */
		return map;
	}

	/**
	 * 无用 快速注册时开户失败，失败后调用的接口 快速注册失败调用的接口
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * @RequestMapping(value = "/user_quick_failure.action")
	 * 
	 * @ResponseBody public Map<String, String>
	 * user_Quick_failure(HttpServletRequest request) throws Exception { //
	 * logger.warn("调用快速注册的注册失败服务接口"); String img = "567890-67891210921209.jpg";
	 * String urla = request.getRequestURI(); StringBuffer sb =
	 * request.getRequestURL();
	 * 
	 * String duankou = request.getProtocol();
	 * 
	 * int ada = request.getServerPort();
	 * 
	 * 
	 * // 拼接全路径
	 * 
	 * return userService.user_Quick_failure(request); }
	 */
	/**
	 * 普通注册 实现普通注册
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/common_register.action")
	@ResponseBody
	public Map<String, String> common_register(
			@Validated(value = ValidGroup_common_reister.class) @RequestBody Ssd_user user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		return userService.save_quick_register(user, bindingResult, request);
	}

	/**
	 * 查询用户是否注册
	 * 
	 * 用户服务中心提供用户是否存在功能，iOS/Android端能够调用相关接口查询用户是否已经注册 用户是否注册接口，该接口用于查询用户是否已注册
	 * 
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/isRegistered.action")
	@ResponseBody
	public Map<String, String> IsRegistered(
			@RequestBody Map<String, String> map, HttpServletRequest request)
			throws Exception {

		return userService.IsRegistered(map, request);
	}

	/**
	 * 查询用户信息 用户服务中心提供查询用户详细信息功能，iOS/Android端能够调用相关接口查询用户的详细信息。
	 * 
	 * @param userid
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showUserDetail.action")
	@ResponseBody
	public Map<String, String> ShowUserDetail(
			@RequestBody Map<String, String> map, HttpServletRequest request)
			throws Exception {

		return userService.ShowUserDetail(map, request);

	}

	// 修改用户信息
	// 用户服务中心提供查询用户详细信息功能，iOS/Android端能够调用相关接口查询用户的详细信息。
	// iOS/Android调用用户信息修改接口后，
	// 服务中心校验该用户是否已在用户表注册，如果已经注册，则修改用户信息，并返回相应的结果。如果没有注册，则返回相应的错误信息。
	@RequestMapping("/modifyUserDetail.action")
	@ResponseBody
	public Map<String, String> ModifyUserDetail(
			@Validated(value = ValidGroup_updateuserValidation.class) @RequestBody Ssd_user ssd_user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		return userService.ModifyUserDetail(ssd_user, bindingResult, request);
	}

	/**
	 * 登录 用户服务中心提供查询用户登录功能，iOS/Android端能够调用相关接口进行用户登录
	 * iOS/Android调用用户登录接口后，服务中心校验该用户是否已在用户表注册
	 * ，密码是否和用户名是否一致，如果一致，则返回登录成功响应，如果不一致，则返回登录失败。
	 * 
	 * @param ssd_user
	 * @param bindingResult
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userLogin.action")
	@ResponseBody
	public Map<String, String> UserLogin(
			@Validated(value = ValidGroup_userloginValidation.class) @RequestBody Ssd_user ssd_user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {

		return userService.UserLogin(ssd_user, request);
	}

	/**
	 * 重置密码 /增加密码重置接口，该接口用于用户密码重置
	 * 
	 * @param user
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/resetPassword.action")
	@ResponseBody
	public Map<String, String> ResetPassword(
			@Validated(value = ValidGroup_Quick_register.class) @RequestBody Ssd_user user,
			BindingResult bindingResult, HttpServletRequest request)
			throws Exception {
		return userService.ResetPassword(user, bindingResult, request);
	}

	/**
	 * 购物车 购物车更新接口，用于购物车信息更新 //传入本接口的参数有令牌、商品ID、面积、标题、价格、最后租用期限
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addToCart.action")
	@ResponseBody
	public Map<String, String> AddToCart(@RequestBody Map<String, String> map,
			HttpServletRequest request) throws Exception {

		return userService.AddToCart(map, request);
	}

	// 购物车查询接口，用于购物车信息查询
	@RequestMapping("/queryCart.action")
	@ResponseBody
	public Map<String, String> QueryCart(@RequestBody Map<String, String> map,
			HttpServletRequest request) throws Exception {
		return userService.QueryCart(map, request);
	}

	// --------------------------------------------
	// 用户上传头像功能
	public String Upload_Icon(MultipartFile multipartFile) throws Exception {
		return null;
	}

	@RequestMapping("/changePhoto")
	public ModelAndView uploadPhoto(MultipartFile filePhoto,
			HttpSession httpSession, HttpServletRequest request) {
		// Users user = (Users) httpSession.getAttribute("user");
		ServletContext context = httpSession.getServletContext();
		// 上传路径 需注意，在tomcat中配置虚拟路径或者在其目录下的conf中的server.xml中的<Host>节点下配置
		/*
		 * <Context path="/项目名/虚拟的路径如pic，任何字符都可以"
		 * docBase="D:\\upload\\users\\imgs"></Context>
		 */
		// 当你在项目中使用/pic时，实际使用的是D:\\upload\\users\\imgs
		String filePath = "D:/upload/users/imgs/";
		// 获取图片原始名称
		String originalFilename = filePhoto.getOriginalFilename();
		// 图片扩展名
		String types = originalFilename.substring(
				originalFilename.lastIndexOf(".") + 1).toLowerCase();

		try {
			System.out.println(types);
			// 以用户id加图片扩展名给图片命名
			// String
			// newFileName=user.getId()+originalFilename.substring(originalFilename.lastIndexOf("."));
			// / File file=new File(filePath+newFileName);
			// 上传
			// filePhoto.transferTo(file);
			// 以80*80大小改变图片，此处使用thumbnailator-0.4.2.jar改变图片大小
			// Thumbnails.of(file).size(80,
			// 80).keepAspectRatio(false).toFile(file);
			// userBiz.updatePhoto(user,newFileName);
			context.setAttribute("photo", "photo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("/user/success");
	}

	// ---------------------------------------------------

	private final int MAX_SIZE = 1024 * 1024 * 1; // 限制用户头像的最大值为1M
	private String[] extendNamesArray = { ".jpg", ".jpeg" }; // 用户头像的扩展名数组，方面验证
	private String rootPath; // 文件根路径
	private String imageNewPath; // 头像新路径（包含头像名以及扩展名）
	private String imageOldPath; // 头像在数据库中的原有路径（包含头像名以及扩展名）
	private String imageNames; // 头像的新名字（时间+用户名），时间精确到毫秒
	private String extendName; // 头像的扩展名，进行扩展名验证，以达到对用户头像的图片类型限制
	private String message; // 用于返回上传头像的信息
	private String imageURL; // 用于返回用户头像存放的物理路径
	private MultipartFile imageFile;

	// 测试时使用方法
	@RequestMapping(value = "/then/uploadHeadPortrait.jspx", method = RequestMethod.GET)
	public void upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("../MyJsp.jsp").forward(request, response);
		return;
	}

	@RequestMapping(value = "/then/uploadHeadPortrait.jspx", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadHeadPortrait(String account,
			HttpServletRequest request) {
		String imageAccount = account; // 存储用户临时的用户名，以便进行文件的命名
		Map<String, String> map = new HashMap<String, String>();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取上传头像
		imageFile = multipartRequest.getFile("file");
		// 获取上传头像的文件名
		String fileName = imageFile.getOriginalFilename();
		System.out.println("OriginalFilename:" + fileName);
		// 获取文件扩展名
		extendName = fileName.substring(fileName.lastIndexOf("."));
		// 获取上传头像的大小
		int imageSize = (int) imageFile.getSize();
		// 验证头像的扩展名是否符合要求
		if ((extendName.equals(extendNamesArray[0]) || extendName
				.equals(extendNamesArray[1])) && (imageSize <= MAX_SIZE)) {
			rootPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/user_icon");
			System.out.println("rootPath:" + rootPath);
			imageNames = getUploadCurrentTime() + imageAccount; // 重新命名上传头像名称
			System.out.println("imageNames:" + imageNames);
			imageNewPath = rootPath + "\\" + imageNames + extendName;
			System.out.println("imageNewPath:" + imageNewPath);
			// 判断新路径是否等于数据库中已存在的路径，不等于，则存储新路径，删除原有头像文件
			/*
			 * if(!imageNewPath.equals(getDatabaseImageOldPath(imageAccount))){
			 * if
			 * (getDatabaseImageOldPath(imageAccount)!=null&&getDatabaseImageOldPath
			 * (imageAccount)!=""){ File file = new
			 * File(getDatabaseImageOldPath(imageAccount));
			 * if(imageSave(imageAccount,imageNewPath)){ if(file.exists()){
			 * file.delete(); } message = "头像上传成功"; imageURL = imageNewPath;
			 * map.put("message",message); map.put("imageURL",imageURL); }else{
			 * //保存失败 message = "头像保存失败"; imageURL = null;
			 * map.put("message",message); map.put("imageURL",imageURL);
			 * 
			 * }
			 * 
			 * }
			 * 
			 * }else{ message = "头像上传失败"; imageURL = null;
			 * map.put("message",message); map.put("imageURL",imageURL); }
			 */

		} else { // 图像格式不符合或者头像的大小大于1M
			message = "头像上传失败，格式或大小不符合";
			imageURL = null;
			map.put("message", message);
			map.put("imageURL", imageURL);
		}
		return map;
	}

	// 获取头上上传的当前时间
	private String getUploadCurrentTime() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
	}

	/*
	 * //获取数据库中原有路径，如有则返回路径，否则返回可能为空或“” private String
	 * getDatabaseImageOldPath(String account){ //UserDetail
	 * userDetatilOldPath=userService.findByUsername(account).getDetail();
	 * imageOldPath = userDetatilOldPath.getAvatar(); return imageOldPath; }
	 */
	// 对原有头像的新路径进行存储，存储后进行检查，时候已经存储，存储成功返回true，失败则返回false
	private boolean imageSave(String account, String imageNewPath) {
		File uploadFile = new File(imageNewPath);
		try {
			FileCopyUtils.copy(imageFile.getBytes(), uploadFile);
			FileUtils.copyInputStreamToFile(imageFile.getInputStream(),
					new File(imageNewPath));
			// UserDetail ud=userService.findByUsername(account).getDetail();
			// ud.setAvatar(imageNewPath); //设置头像新路径
			// userDetailService.update(ud) ; //对头像进行数据库更新操作
			/*
			 * if(getDatabaseImageOldPath(account)!=null||getDatabaseImageOldPath
			 * (account).equals("")){ return true; }
			 */
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("【上传头像失败...】");
			return false;
		}
		return false;
	}

}
