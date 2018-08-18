package com.practice.countdown.latch.ex;

import java.util.concurrent.CountDownLatch;


public class CountDownLatchEx {

	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		Thread t1 = new Thread(new MyThread(latch));
		Thread t2 = new Thread(new MyThread(latch));
		Thread t3 = new Thread(new MyThread(latch));
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread implements Runnable {
	private CountDownLatch latch;
	
	public MyThread(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		System.out.println("Before countdown");
		latch.countDown();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("After countdown");
	}
}