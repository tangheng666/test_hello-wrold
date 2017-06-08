package com.ssd.test.User;

public class Main {

	public static void main(String[] args) {

		MyThread mThread1 = new MyThread("唐衡");
		MyThread mThread2 = new MyThread("尹赞");
		MyThread mThread3 = new MyThread("小狗");
		mThread1.start();
		mThread2.start();
		mThread3.start();
	}
}
