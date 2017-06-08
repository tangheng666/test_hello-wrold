package com.ssd.tanghengutil.RedisUtil;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssd.po.Ssd_user;

import redis.clients.jedis.Jedis;
@Component("userloginCommonUtil")
public class UserloginCommonUtil {
 
	/**
	 * 初始化jedis对象
	 * @return
	 */
	 public Jedis init(){
		 Jedis conn= new Jedis("119.23.249.110", 6379);
			
			  conn.auth("suishendi");
			  conn.select(15);//命令用于切换到指定的数据库，数据库索引号 index 用数字值指定，以 0 作为起始索引值。
			 return conn; 
	 }
	    //去查询loginkey中的token值中的数据,
	    public String checkToken(Jedis conn, String token) {
	    	System.out.println(conn.ping());
	        String result  =  conn.hget("login:", token);//命令用于返回哈希表中指定字段的值。
	       
	        return result ;
	    }
	    //更新login:哈希表中的token键的数据
	    public void updateToken(Jedis conn,String token ,Ssd_user user ,String item,Long  historyCount ){
	    	long timestamp =System.currentTimeMillis()/1000;//生成一个毫秒数
	    	conn.hset("login:", token, user.getUserid());//更新login:哈希表中的中的token键的值    维持令牌和已登录用户之间的映射
	    	conn.zadd("recent:", timestamp, token);//记录令牌最后一次出现的时间
	    	if(item!=null){
	    		conn.zadd("viewed:"+token, timestamp, item);//记录用户浏览过的商品
	    		conn.zremrangeByRank("viewed:"+token, 0, historyCount);//移除旧的记录，只保留用户最近浏览过的25个商品
	    		conn.zincrby("viewed:", -1, item);  //命令对有序集合中指定成员的分数加上增量 increment
	    	}
	    	
	     
	    	 
	    }
	    //更新购物侧
	    public void addToCart(Jedis conn, String Token, String SoilId, Float count,Float price ,String title,String deadline ) {
	        if (count <= 0) {
	            conn.hdel("cart:" + Token, SoilId);
	        } else {
	        	Map<String, String> map = new HashMap<String, String>();
	        	map.put("area", ""+count);
	        	map.put("price", ""+price);
	        	map.put("title", title);
	        	map.put("deadline", deadline);
	            ObjectMapper objectMapper = new ObjectMapper();
	            try {
					conn.hset("cart:" + Token, SoilId, objectMapper.writeValueAsString(map));
				
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } 
	    }
	    //查询某登录用户的购物车信息  //先在value里面存土地标题、土地面积、土地价格
	    public  Map<String, String> queryToCart(Jedis conn,String Token ){
	    	 Map<String, String>map =  conn.hgetAll("cart:"+Token); 
	    	 
	    		return map ;
	    } 
	    //查询hash键的某个域的value值
	    public String queryToValue(Jedis conn, String token ,String itme ){
	         String result =  conn.hget("cart:"+token, itme);
	    
	     	return result ;
	    }
	    

}
