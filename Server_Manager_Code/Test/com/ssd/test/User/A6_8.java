package com.ssd.test.User;

public class A6_8 {
	public static void main(String[] args) {
		Xc8 mp = new Xc8();

		// 使用同一个对象创建了两个线程，并start
		Thread cz1 = new Thread(mp);
		Thread cz2 = new Thread(mp);
		cz1.start();
		cz2.start();
	}
}

class Xc8 implements Runnable {
	// 此处不需要static
	private int piao = 100;

	// 可以使用本类对象（this）作为锁，没必要new一个对象
	// String aa = new String("1");
	public   void run() {
		while (true) {
			// 为了容易看出输出时效果，此处sleep 10<a
			// href="https://www.baidu.com/s?wd=%E6%AF%AB%E7%A7%92&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1Y3PjNWnWbduWmvnvcLrHFb0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EP1RzPWbYnj64rHndPWbYnj6Y"
			// target="_blank" class="baidu-highlight">毫秒</a>
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/* (this) {*/
				if (piao > 0) {
					System.out.println(Thread.currentThread().getName()
							+ "正在卖第" + (101 - piao) + "张票");
					--piao;
				} else {
					break;
				}
			//}
		}
	}
}