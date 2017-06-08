package com.ssd.test.ssd_tradelog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

import com.ssd.po.Ssd_tradelog;
import com.ssd.tanghengutil.RedisUtil.UserloginCommonUtil;
 
public class Test_Ssd_tradelog {
	
 

	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void test() throws Exception { 

		
	/*	String phone = "18566403223" ;
	  System.out.println(phone.substring(phone.length()-2));*/
		
		
		
 /*	UserloginCommonUtil commonUtil = new UserloginCommonUtil();*/
 /*	commonUtil.addToCart(commonUtil.init(), "18566403223", "1", 3F,4F,"好土地");*/
		
	/*	commonUtil.addToCart(commonUtil.init(), "18566403223", "1", 3);
		commonUtil.addToCart(commonUtil.init(), "18566403223", "1", 4);*/
/* 	System.out.println();
 	Jedis jedis = commonUtil.init();
 	System.out.println(jedis.ping());
 	System.out.println(commonUtil.checkToken(commonUtil.init(), "02995768-be6f-4f38-b067-259723e8f70b"));
		//System.out.println(UserloginCommonUtil.queryToValue(UserloginCommonUtil.init(), "18566403223", "1"));
		 Map<String, String> map =  (Map<String, String>) commonUtil.queryToCart(commonUtil.init(), "18566403223");
		 System.out.println(map.keySet().toString());
		 System.out.println(map.values().toString()); */
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
		
		Ssd_tradelog ssd_tradelog1 = new Ssd_tradelog();
		ssd_tradelog.setUser_id("18566403233");
		ssd_tradelog.setOrder_id("18746565615");
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
		
		List<Ssd_tradelog> list=  new ArrayList<Ssd_tradelog>() ;
		list.add(ssd_tradelog);
		list.add(ssd_tradelog1);
	  
		for (Ssd_tradelog ssd_tradelog211 : list) {
			ssd_tradelog211.setUser_id("1211212112");
		}
		
		
 	System.out.println(list.toString());

 /*	  SqlSession sqlSession =  sqlSessionFactory.openSession();
 	  Ssd_paraMapper mapper  = sqlSession.getMapper(Ssd_paraMapper.class);
 	  Ssd_para ssd_para = mapper.GetSsd_para("101", "1");
 	  System.out.println(ssd_para.toString());*/
		
		
	/*	Properties properties = System.getenv();  
		Iterator it =  properties.entrySet().iterator();  
		while(it.hasNext())  
		{  
		    Entry entry = (Entry)it.next();  
		    System.out.print(entry.getKey()+"=");  
		    System.out.println(entry.getValue());  
		}  */
	/*	Map map = System.getenv();  
		Iterator it = map.entrySet().iterator();  
		while(it.hasNext())  
		{  
		    Entry entry = (Entry)it.next();  
		    System.out.print(entry.getKey()+"=");  
		    System.out.println(entry.getValue());  
		}  */
//	     Ssd_tradelogMapper mapper = sqlSession.getMapper(Ssd_tradelogMapper.class);
//	     //查询用户的所有订单信息
//	   List<Ssd_tradelog> list =  mapper.Get_useridOrderInfo("18566403223", 1);
//		for (Ssd_tradelog ssd_tradelog : list) {
//			System.out.println(ssd_tradelog.toString());
//		}
//	  System.out.println(count);
	 /* for (Ssd_tradelog ssd_tradelog : list) {
		System.out.println(ssd_tradelog.toString());
	}*/
     
	  
	// System.out.println(aa);
	}


}
