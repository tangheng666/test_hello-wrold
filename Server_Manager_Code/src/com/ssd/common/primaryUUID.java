package com.ssd.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public  class  primaryUUID {
	//生成主键
	public static String  IdGenerate(){
		AtomicInteger hgjk= new AtomicInteger(1000);
		SimpleDateFormat   dateFormat=new SimpleDateFormat("yyyyMMddHHmmssms");
		String aString= dateFormat.format(new Date())+hgjk.getAndIncrement();//自增
		
		return aString;
	}
	
	//做为图片的名称
	public static String UUIDImage(){
		String imagename= UUID.randomUUID().toString(); //生成
		 return imagename.substring(0,8)+imagename.substring(9,13)+imagename.substring(14,18)
				 +imagename.substring(19,23)+imagename.substring(24); //把中间的‘-’截取掉
		
	}
	

}
