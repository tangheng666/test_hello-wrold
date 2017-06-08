package com.ssd.test.User;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.core.format.DataFormatDetector;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ssd.mapper.Ssd_soilMapper;
import com.ssd.mapper.Ssd_userMapper;
import com.ssd.po.Ssd_user;
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})  
public class AddUser {
 
	@Autowired
 private DataSource dataSource ;
	@Autowired
	private SqlSessionFactory sqlSessionFactory ;


	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void test() throws Exception { 

		 SqlSession sqlSession =  sqlSessionFactory.openSession();
	/*	 
	 Ssd_userMapper um =  sqlSession.getMapper(Ssd_userMapper.class);
	 
	 
	 Ssd_user user = new Ssd_user() ;
	 user.setUserid("1361261216");
	 user.setAddress1("湖南永州");
	 user.setBirthday("2000-03-19");
	 user.setEmail("2605253179@qq.com");
	 user.setIcon("/WEB-INF/icons/qeqw.img");
	 user.setIdentity_card("431126200003198478");
	 user.setIntegral(10F);
	 user.setPassword("123456789");
	 user.setPhone("18566403223");
	 user.setSex('男');
	
	 um.add_user(user);
	 sqlSession.rollback();*/
		 
/*		 Ssd_soilMapper mapper  = sqlSession.getMapper(Ssd_soilMapper.class);
		 System.out.println(mapper.toString());*/
		 
		 String aa = "12";
		 System.out.println(Long.valueOf(aa));
	// System.out.println(aa);
	}

}
