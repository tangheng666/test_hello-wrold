package com.ssd.test.datasource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;




import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssd.mapper.Ssd_userMapper; 
import com.ssd.po.Ssd_tradelog;
import com.ssd.po.Ssd_user;
 /**
  * 测试数据源    以及sqlsessionfactory
  * @author giga
  *
  */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})  
public class TestDataSource {
 
	@Autowired
 private DataSource dataSource ;
	@Autowired
	private SqlSessionFactory sqlSessionFactory ;
	
	/*@Before
	public void setUp() throws Exception {
		
		ac =new FileSystemXmlApplicationContext("/WebRoot/WEB-INF/config/spring/applicationContext-dao.xml");
	}*/

	@Test
	public void test() throws Exception { 
	/*	Ssd_user user = new Ssd_user();
		user.setUsername("唐衡");
		user.setAddress1("湖南永州");
		user.setBirthday("2000-03-19");
		user.setEmail("2605253179@qq.com");
		user.setIcon("/WEB-INF/icons/qeqw.img");
		user.setIdentity_card("431126200003198478");
		user.setIntegral(10F);
		user.setPassword("123456789");
		user.setPhone("18566403223");
		user.setSex('男');
		System.out.println(user.toString());*/
		
	/*	Map<String, String> map = new HashMap<String, String>();
		System.out.println(map.size()==0);*/

		List<String> list= new ArrayList<String>();
		list.add("ab");
		list.add("bc");
		list.add("1221");
		
		System.out.println(list.toString().trim().replaceAll(",", "|").replace("[", "").replace("]", ""));
		
		
		//'18566403223', '', '1', '8866545656', NULL, 
		//'8948949656', '121331', '2', '17.2', '16999.2', '湖南', '永州市', '宁远县禾亭镇富村水清庙13组'

		Ssd_tradelog ssd_tradelog = new Ssd_tradelog();
		ssd_tradelog.setUser_id("18566403223");
		ssd_tradelog.setOrder_id("18746565645");
		ssd_tradelog.setState(1);
		ssd_tradelog.setCommit_date("20010326090420");
		ssd_tradelog.setComplete_date("20010326090520");
		ssd_tradelog.setField_id("8948949656");
		ssd_tradelog.setTitle("好土 地");
		ssd_tradelog.setType(2);
		ssd_tradelog.setArea(14.7F);
		ssd_tradelog.setPrice(17.3F);
		ssd_tradelog.setProvince("湖南");
		ssd_tradelog.setUrban_area("永州市 ");
		ssd_tradelog.setDetailed_address("宁远县禾亭镇富村水清庙13组 ");
		
		File file = new File("tree.unl");
		if (!file.exists()) {
			file.createNewFile();
		}
	//	Ssd_tradelog ssd_tradelog2 = ssd_tradelogMapper.Get_orderidOrderInfo(map.get("userID"));
		FileWriter fileWriter = new FileWriter(
				file.getAbsoluteFile());
		BufferedWriter writer = new BufferedWriter(fileWriter);
		writer.write(ssd_tradelog.toString());
		writer.close();
		
		//效果                         YYYYMMDD_${HOST}_PID：ab| bc| 1221
		/*	System.out.println(sqlSessionFactory.openSession());
		
		 SqlSession sqlSession =  sqlSessionFactory.openSession();
		 
	 Ssd_userMapper um =  sqlSession.getMapper(Ssd_userMapper.class);
	 
	 System.out.println(um.GetUser_count(null));
		 System.out.println(); 
		 
 	System.out.println(dataSource.getConnection());
 	
 	System.out.println("----------------------");
 	//测试原子类型
 	AtomicInteger atomicInteger = new AtomicInteger(999);
 	
 	System.out.println(atomicInteger.getAndIncrement());
		
 	
 	System.out.println(atomicInteger.getAndIncrement());
 	
 	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
 	System.out.println(df.format(new Date()));
 	
 	
 	System.out.println("-----------------------");
 	
 	
	AtomicInteger atomicInteger = new AtomicInteger(1000); 
	// 	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
	 	String userid = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +atomicInteger.getAndIncrement();
		
		System.out.println(userid);
		
	String userida = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) +atomicInteger.getAndIncrement();
		
		System.out.println(userida);
		
		
		System.out.println(new Date().toString());*/
	}

}
