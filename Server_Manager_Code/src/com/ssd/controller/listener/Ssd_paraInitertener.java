package com.ssd.controller.listener;
 

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;  
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ssd.mapper.Ssd_paraMapper;
import com.ssd.po.Ssd_para;
import com.ssd.service.Ssd_paraService;
import com.ssd.util.Ssd_paraUtil;
 /**
  * 系统监听器， 去查询系统参数
  * @author giga
  *
  */
public class Ssd_paraInitertener implements ServletContextListener {

/*	ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
	
	
 	private SqlSessionFactory sqlSessionFactory  = (SqlSessionFactory) ac.getBean("sqlSessionFactory");*/
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());

		//获取权限service

	 
		Ssd_paraService  ssd_paraService =  (Ssd_paraService) ac.getBean("com.ssd.service.Ssd_paraService");
		String service_id = System.getenv("service_id");
		try { 
     
		Ssd_para ssd_para =	ssd_paraService.GetSsd_para(service_id, "1");
		Ssd_paraUtil.cart_num_lim = "-"+ssd_para.getCart_num_lim();   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
