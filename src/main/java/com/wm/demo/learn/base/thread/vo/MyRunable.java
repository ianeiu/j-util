package com.wm.demo.learn.base.thread.vo;

public class MyRunable implements Runnable {

	public MyRunable() {
		
	}
	
	
	@Override
	public void run() {
		for (int x = 0; x < 100; x++) {
			// 由于实现接口的方式就不能直接使用Thread类的方法了,但是可以间接的使用
			System.out.println(Thread.currentThread().getName() + ":" + x);
		}
	}

}
