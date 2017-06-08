package com.ssd.common;

public class StringHelper {
	
	
	public static String[]  Break(String value){
		
		if(value !=null){
			String zhi=  value.substring(1, value.length()-1);
			String[] complete=  zhi.split(",");
			
			return complete;
		}
		
	return null;
		
	}
	

}
