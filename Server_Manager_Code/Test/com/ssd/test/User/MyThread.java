package com.ssd.test.User;

public class MyThread extends Thread {

	     String name = null;
	      int ticket = 0;
	    public MyThread(String name){
	        this.name = name;
	    }
	    public  synchronized void run(){
	    	 
	    	
	        for (int i = 0; i < 5; i++) {
	            System.out.println(Thread.currentThread().getName()+this.name+" ticket:"+ticket++);
	        }
	       
	    }
	    
	   
	
}
