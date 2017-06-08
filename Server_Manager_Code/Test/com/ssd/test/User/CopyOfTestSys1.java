package com.ssd.test.User;

public class CopyOfTestSys1 extends Thread {
	private Timer t;

	public CopyOfTestSys1(Timer t) {
		this.t = t;
	}

	public static void main(String[] args) {
		Timer t = new Timer();
		//Timer t1 = new Timer();
		Thread thread1 = new CopyOfTestSys1(t);
		Thread thread2 = new CopyOfTestSys1(t);
		thread1.start();
		thread2.start();
	}

	@Override
	public void run() {

		t.add(Thread.currentThread().getName());

	}

}

class Timer {
	private int number = 0;

	// synchronized {};
	public synchronized void add(String name) {

		number++;
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(number);

	}

}