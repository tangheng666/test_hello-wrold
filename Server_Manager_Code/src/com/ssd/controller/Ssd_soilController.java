package com.ssd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import net.sf.json.JSONObject;*/

import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.common.DocConverter;
import com.ssd.common.PageSupport;
import com.ssd.po.Ssd_soil;
import com.ssd.po.Thedrop_down_list;
import com.ssd.service.Ssd_soilService;
import com.ssd.service.impl.Ssd_soilServiceImpl;
import com.ssd.util.StringHelper;
import com.ssd.util.Token;
import com.ssd.util.landUtil;
import com.ssd.util.primaryUUID;

@Controller
@RequestMapping("/land")
public class Ssd_soilController {
	@Autowired
	private Ssd_soilService soilService;

    ObjectMapper	mapper = new ObjectMapper();
 

	// 处理页面请求
	// 查询土地详情
 	@RequestMapping("/ShowFieldDetail.htm")
	public String ShowFieldDetail(Ssd_soil soil, Model model,
			HttpServletRequest request) {

		Ssd_soil soil2 = soilService.getSoil(soil);
		if (soil2 != null) {

			soil2.setAdvertising(StringHelper.Break(soil2.getSoil_ad_url()));// 拆分土地广告详情url地址
			soil2.setDetails(StringHelper.Break(soil2.getSoil_detail_url())); // 拆分土地详情url地址
			model.addAttribute("soil", soil2);

		} else {
			model.addAttribute("soil", null);
		}
		return "index";
	}
 
	// 处理app请求
	// 查询土地详情
	@RequestMapping("/showFieldDetail.action")
	public @ResponseBody
	Map<String, String> ShowFieldDetail(@RequestBody   Ssd_soil soil,HttpServletRequest request) throws Exception {

		
		return     soilService.ShowFieldDetail(soil, request);
	}


	// 添加客户端土地信息和修改方法在一起
	@RequestMapping("/save.action")
	public String Save(HttpServletRequest request, @Valid Ssd_soil soil,
			BindingResult result, @RequestParam MultipartFile[] myfile,
			Model model, MultipartFile[] myFiles, MultipartFile word1,
			MultipartFile word2, MultipartFile word3,String type) {
		if (result.hasErrors()) {// 判断是否有错误产生
			// 获取错误
			List<ObjectError> errors = result.getAllErrors();
			// 准备在页面输出errors,遍历jstl
			 model.addAttribute("errors", errors); 
			
			  for (ObjectError objectError : errors) {
			  System.out.println(objectError);
			  }
			 
			 return "redirect:/land/map.action"; 

	/*		map.put("error", mapper.writeValueAsString(errors));*/
		}
		soil.setSoil_id(primaryUUID.IdGenerate()); // 给主键id赋值
		List<String> ad_url = new ArrayList<String>(); // 存放广告图片url
		if (myfile != null && myfile.length > 0) {
			for (MultipartFile ad_url1 : myfile) {
				if (checkFile(ad_url1.getOriginalFilename())) {

					MultipartFile file = ad_url1;
					String path = saveFile(request, file, "image", soil.getSoil_id());
					ad_url.add(path);
				} else {
					/*map.put("tishi", "只能上传图片,谢谢");*/
					throw new RuntimeException("请上传图片,谢谢");
					/* model.addAttribute("tishi", "请上传图片,谢谢"); */
					/* return "index"; */
				}
			}
			soil.setSoil_ad_url(ad_url.toString()); // 添加广告图片
		}
		List<String> detail_url = new ArrayList<String>(); // 存放广告图片url
		if (myFiles != null && myFiles.length > 0) {
             for (MultipartFile ad_url1 : myFiles) {
				if (checkFile(ad_url1.getOriginalFilename())) { // 判断类型是否是图片,true表示是
					MultipartFile file = ad_url1;
					String path = saveFile(request, file, "image", soil.getSoil_id());
					detail_url.add(path);
				} else {
					/*
					 * //  // return
					 * "index";
					 *//*map.put("tishi", "只能上传图片,谢谢");*/
					throw new RuntimeException("请上传图片,谢谢");
				}
			}
             soil.setSoil_detail_url(detail_url.toString()); // 添加详情图片
		}
		if (word1 != null ) { //判断土壤报告是否为空
			
			soil.setSoil_report(saveFile(request, word1, "soil", soil.getSoil_id())); // 添加土壤报告url
		}
		if(word2 != null){//判断水质报告是否为空
			soil.setSoil_water_report(saveFile(request, word2, "quality", soil.getSoil_id()));// 添加水质检疫报告
		}
		if(word3 != null){//判断性质报告是否为空
			soil.setSoil_type_report(saveFile(request, word3, "nature", soil.getSoil_id())); // 添加性质报告
		}
		try {
			if("update".equals(type)){  //如果type值为update 就执行修改语句 ,否测就执行新增土地方法
				soilService.UpdateSoil(soil); //先执行修改数据库操作
			}else {
				soilService.SaveSoil(soil); // 执行添加
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return "index";
	}
	// 处理页面请求新增土地
	//处理手机端
	@RequestMapping("/save.json")
	@Token(save = true)
	public @ResponseBody Map<String, String> Save(HttpServletRequest request,
			 @RequestBody   @Valid Ssd_soil soil, BindingResult result,
			@RequestParam MultipartFile[] myfile,
			@RequestParam MultipartFile[] myFiles,
			@RequestParam MultipartFile word1,
			@RequestParam MultipartFile word2, @RequestParam MultipartFile word3)
			throws Exception {	
		Map<String, String> map = new HashMap<>();
		if (result.hasErrors()) {// 判断是否有错误产生
			// 获取错误
			List<ObjectError> errors = result.getAllErrors();
			// 准备在页面输出errors,遍历jstl
			/* model.addAttribute("errors", errors); */
			/*
			 * for (ObjectError objectError : errors) {
			 * 
			 * }
			 */
			/* return "redirect:/land/map.action"; */

			map.put("error", mapper.writeValueAsString(errors));
		}
		soil.setSoil_id(primaryUUID.IdGenerate()); // 给主键id赋值
		List<String> ad_url = new ArrayList<String>(); // 存放广告图片url
		if (myfile != null && myfile.length > 0) {
			for (MultipartFile ad_url1 : myfile) {
				if (checkFile(ad_url1.getOriginalFilename())) {

					MultipartFile file = ad_url1;
					String path = saveFile(request, file, "image", soil.getSoil_id());
					ad_url.add(path);
				} else {
					map.put("tishi", "只能上传图片,谢谢");
					/* model.addAttribute("tishi", "请上传图片,谢谢"); */
					/* return "index"; */
				}
			}
			soil.setSoil_ad_url(ad_url.toString()); // 添加广告图片
		}
		List<String> detail_url = new ArrayList<String>(); // 存放广告图片url
		if (myFiles != null && myFiles.length > 0) {
             for (MultipartFile ad_url1 : myFiles) {
				if (checkFile(ad_url1.getOriginalFilename())) { // 判断类型是否是图片,true表示是
					MultipartFile file = ad_url1;
					String path = saveFile(request, file, "image", soil.getSoil_id());
					detail_url.add(path);
				} else {
					/*
					 * // model.addAttribute("tishi", "请上传图片,谢谢"); // return
					 * "index";
					 */map.put("tishi", "只能上传图片,谢谢");
				}
			}
             soil.setSoil_detail_url(detail_url.toString()); // 添加详情图片
		}
		
		
		
		if (word1 != null ) { //判断土壤报告是否为空
			
			soil.setSoil_report(saveFile(request, word1, "soil", soil.getSoil_id())); // 添加土壤报告url
			
			
		}
		if(word2 != null){//判断水质报告是否为空
			soil.setSoil_water_report(saveFile(request, word2, "quality", soil.getSoil_id()));// 添加水质检疫报告
		}
		if(word3 != null){//判断性质报告是否为空
			soil.setSoil_type_report(saveFile(request, word3, "nature", soil.getSoil_id())); // 添加性质报告
		}
		
		
		try {
			soilService.SaveSoil(soil); // 执行添加
			map.put("retcode", "0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("retcode", "1001");
		}
		return map;
	}

	/***
	 * 保存文件方法
	 * 
	 * @param file
	 * @return
	 */
	private String saveFile(HttpServletRequest request, MultipartFile file,
			String type, String id) {
		// 判断文件是否为空
		if (!file.isEmpty()) {

			try {
				String path = null;
				String orignalfilename = null;

				StringBuffer lujin = new StringBuffer();

				lujin.append("http://");
				lujin.append(request.getRemoteAddr() + ":");// 本机ip地址
				lujin.append(request.getServerPort()); // 服务器端口号
				lujin.append(request.getContextPath());// 拿取根目录

				// 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
				// )

				if ("image".equals(type)) {// 处理图片
					orignalfilename = primaryUUID.UUIDImage()
							+ file.getOriginalFilename()
									.substring(
											file.getOriginalFilename()
													.lastIndexOf("."));
					lujin.append("/" + id + "/image/" + orignalfilename);//拼接图片存在路径
					path = request.getSession().getServletContext()  //创建服务器路径
							.getRealPath("/" + id + "/"+"image/");
				} else {
					// 处理word文件 这些

					/*orignalfilename = primaryUUID.UUIDImage()
							+ file.getOriginalFilename()
									.substring(
											file.getOriginalFilename()
													.lastIndexOf("."));*/
					orignalfilename=new String(file.getOriginalFilename().getBytes("ISO8859-1"),"UTF-8");
					
					  //土地土壤报告文件夹名
						lujin.append("/" + id + "/word/" + orignalfilename);
					path = request.getSession().getServletContext()
							.getRealPath("/" + id + "/"+type+"/");
				}

				File saveDir = new File(path, orignalfilename);
				if (!saveDir.getParentFile().exists()) {
					saveDir.getParentFile().mkdirs();
				}

				// 转存文件
				file.transferTo(new File(path + File.separator
						+ orignalfilename));
				/*
				 * FileUtils.copyInputStreamToFile(file.getInputStream(),saveDir)
				 * ;
				 */

				return lujin.toString(); // 保存到数据库中
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	// 详情页面跳转处理
	@RequestMapping("/map.htm")
	public String jump(Model model) throws FileNotFoundException {
		List<Thedrop_down_list> lop = landUtil.province("Provinces.xml"); // 保存所有省份
		List<Thedrop_down_list> list = landUtil.readvMap("Landtype.properties"); // 保存
		List<Thedrop_down_list> statics = landUtil
				.readvMap("landstate.properties");// 保存所有土地状态
		List<Thedrop_down_list> nature = landUtil
				.readvMap("LandNature.properties");// 保存土地性质
		List<Thedrop_down_list> resources = landUtil
				.readvMap("ResourcesStatus.properties");// 保存水资源
		model.addAttribute("resources", resources);
		model.addAttribute("nature", nature);
		model.addAttribute("lop", lop);
		model.addAttribute("statics", statics);
		model.addAttribute("list", list);
		return "index";
	}

	// 详情页面跳转处理
	// 处理手机端
	@RequestMapping("/map.action")
	public @ResponseBody
	Map<String, String> jump() throws FileNotFoundException {
		Map<String, String> result = new HashMap<>();
		List<Thedrop_down_list> province = landUtil.province("Provinces.xml"); // 保存所有省份
		List<Thedrop_down_list> Landtype = landUtil
				.readvMap("Landtype.properties"); // 保存土地类型
		List<Thedrop_down_list> statics = landUtil
				.readvMap("landstate.properties");// 保存所有土地状态
		List<Thedrop_down_list> nature = landUtil
				.readvMap("LandNature.properties");// 保存土地性质
		List<Thedrop_down_list> resources = landUtil
				.readvMap("ResourcesStatus.properties");// 保存水资源
		if (resources != null && nature != null && statics != null
				&& Landtype != null && province != null) {
			ObjectMapper ko = new ObjectMapper();

			try {
				result.put("resources", ko.writeValueAsString(resources));
				result.put("nature", ko.writeValueAsString(nature));
				result.put("statics", ko.writeValueAsString(statics));
				result.put("Landtype", ko.writeValueAsString(Landtype));
				result.put("province", ko.writeValueAsString(province));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			result.put("retcode", "0");
		} else {
			result.put("retcode", "1001");
		}

		return result;

	}

	// 根据省份id查询地区
	// 处理手机端
	@RequestMapping("/are.action")
	public @ResponseBody
	Map<String, String> selectarea(@RequestBody String id) throws Exception {
		String areid = id.trim();
		Map<String, String> result = new HashMap<>();
		List<Thedrop_down_list> area = landUtil.area(areid, "Provinces.xml");
		if (area != null) {
			result.put("area", mapper.writeValueAsString(area));
			result.put("retcode", "0");
		} else {
			result.put("retcode", "1001");
		}
		return result;
	}

	// 处理页面请求
	// 根据省份id查询地区
	@RequestMapping("/are.htm")
	public @ResponseBody
	List<Thedrop_down_list> selectarea(String id, Model model) {
		String areid = id.trim();
		List<Thedrop_down_list> list = landUtil.area(areid, "Provinces.xml");
		return list;
	}

	// 文件下载
	@RequestMapping("/download.htm")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			String filename) throws IOException {

		String path = request.getServletContext().getRealPath("");
		System.out.println(path);
		File file = new File(path + File.separator
				+ filename.substring(filename.indexOf("/", 4)));
		HttpHeaders headers = new HttpHeaders();
		String filname = new String(filename.getBytes("ISO8859-1"), "UTF-8");
		String dowloadFilenam = new String(filename);
		System.out.println(dowloadFilenam);
		// 通知浏览器以attachemt(下载方式)打开图片
		headers.setContentDispositionFormData("attachment", dowloadFilenam);
		// 二级制流数据
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

		try {
			return new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	// 在线浏览功能
	@RequestMapping(value = "/docPrvew.htm")
	public String docUplod(HttpServletRequest request, String filename) {
                
		
		String patht1 = filename.substring(filename.lastIndexOf("/") - 4);
		System.out.println(patht1);
		String path = request.getServletContext().getRealPath("/")
				+ filename.substring(filename.lastIndexOf("/") - 4);
		DocConverter d = new DocConverter(path);
		d.conver();

		// 生成swf相对路径,以便传递给flexpaper播放器
		String swfpath = "word"
				+ d.getswfPath().substring(d.getswfPath().lastIndexOf("/"));

		request.getSession().setAttribute("swfpath", swfpath);

		return "documentView";

	}

	/**查询土地概要信息
	 *  分页查询
	 * @param soil
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ShowFieldinfo.action")
	public @ResponseBody
	Map<String, String> ShowFieldinfo(@RequestBody Ssd_soil soil,HttpServletRequest request) throws Exception {
 
		return soilService.ShowFieldinfo(soil, request);
	}

	@RequestMapping(value = "/ShowFieldinfo.htm")
	public String ShowFieldinfo(Ssd_soil soil, Model model) throws Exception {
		/*
		 * Soil soil2 = new Soil(); soil2.setSoil_province("2");
		 * soil2.setCount(5);// 页容量 soil2.setCurrent_page(2);
		 */
		PageSupport<Ssd_soil> page = soilService.findpaging(soil);
		List<Ssd_soil> soils = page.getItems(); // 拿到土地集合

		ObjectMapper mapper = new ObjectMapper();
		String pae = mapper.writeValueAsString(page);
		System.out.println(pae);
		return "index";

	}

	// 修改土地方法
	@RequestMapping("update.action")
	public String Update(HttpServletRequest request, @Valid Ssd_soil soil,
			BindingResult result, @RequestParam MultipartFile[] myfile,
			Model model, MultipartFile[] myFiles, MultipartFile word1,
			MultipartFile word2, MultipartFile word3) {

		String folder = soil.getSoil_id() + "words";// 声明文件文件夹名字,默认的id加我word
		String foldername = soil.getSoil_id() + "images";// 声明文件文件夹名字,默认的id加我image
		List<String> ad_url = new ArrayList<String>(); // 存放广告图片url
		if (myfile != null && myfile.length > 0) {
			for (MultipartFile ad_url1 : myfile) {
				if (checkFile(ad_url1.getOriginalFilename())) {

					MultipartFile file = ad_url1;
					String path = saveFile(request, file, "image", foldername);
					ad_url.add(path);
				} else {
					model.addAttribute("tishi", "请上传图片,谢谢");
					return "index";
				}
			}
		}
		List<String> detail_url = new ArrayList<String>(); // 存放广告图片url
		if (myFiles != null && myFiles.length > 0) {

			folder = soil.getSoil_id() + "image";
			for (MultipartFile ad_url1 : myFiles) {
				if (checkFile(ad_url1.getOriginalFilename())) { // 判断类型是否是图片,true表示是
					MultipartFile file = ad_url1;
					String path = saveFile(request, file, "image", foldername);
					detail_url.add(path);
				} else {
					model.addAttribute("tishi", "请上传图片,谢谢");
					return "index";
				}
			}

		}
		if (myfile.length > 0 || myFiles.length > 0) { // 只要满足其中一个条件就进入
			soil.setSoil_detail_folder(folder);// 把word文件夹的名称存入数据库
		}
		if (word1 != null || word2 != null || word3 != null) { // 只要满足其中一个条件就进入
			soil.setSoil_detail_folder(folder);// 把word文件夹的名称存入数据库
			soil.setSoil_report(saveFile(request, word1, "word", folder)); // 添加土壤报告url
			soil.setSoil_water_report(saveFile(request, word2, "word", folder));// 添加水质检疫报告
			soil.setSoil_type_report(saveFile(request, word3, "word", folder)); // 添加性质报告
		}

		soil.setSoil_ad_url(ad_url.toString()); // 添加广告图片
		soil.setSoil_detail_url(detail_url.toString()); // 添加详情图片
		soilService.UpdateSoil(soil);
		// 执行修改
		return "index";

	}

	// 删除文件的方法
	/**
	 * 1:拿到路径
	 * 
	 * 2:删除目录
	 */

	public void delete(File file, String folder, HttpServletRequest request) {

		String path = request.getSession().getServletContext().getRealPath(""); // 拿到服务器路径
		String lujin = path + "/" + folder; // 拿到文件夹路径
		File file2 = new File(lujin);
		if (file2.exists()) { // 判断是否存在
			file2.delete();// 删除
		}
	}

	/**
	 * 判断是否为允许的上传文件类型,true表示允许
	 * 
	 */
	public boolean checkFile(String fileName) {
		// 设置允许上传文件类型

		String suffixlist = "jpg,gif,png,ico,bmp,jpeg";
		// 获取文件类型
		String suffif = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		if (suffixlist.contains(suffif.trim().toLowerCase())) {
			return true;
		}
		return false;
	}
	
	
	
	  
	  //--------------------------------------------------------------------------- 
	  
	  //服务中心校验该土地库存是否足够存在，如果存在足够，则返回相应减掉相应的库存的结果。
	  
	  //土地交易接口查询用户信息接口，该接口用于用于查询用户用户提交订单，付款交易等功能详细信息
	  @RequestMapping("/trandeCommit.action")
	  public    @ResponseBody Map<String, String>  TrandeCommit(@RequestBody Map<String, String> map,HttpServletRequest request )throws Exception{
		  
		  return soilService.TrandeCommit(map, request);
	  }
	 
}
