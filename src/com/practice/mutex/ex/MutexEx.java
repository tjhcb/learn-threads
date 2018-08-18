package com.practice.mutex.ex;

import java.util.concurrent.Semaphore;

public class MutexEx {

	public static void main(String[] args) {
		Thread t1 = new Thread(new MyThread());
		Thread t2 = new Thread(new MyThread());
		Thread t3 = new Thread(new MyThread());
		
		t1.start();
		t2.start();
		t3.start();
	}

}

class MyThread implements Runnable {
	 
	private static int val;
	
	private static Semaphore lock = new Semaphore(1);
	
	@Override
	public void run() {
		try {
			lock.acquire();
			val++;
			System.out.println("val : " + val + " thread : " + Thread.currentThread());
			lock.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
