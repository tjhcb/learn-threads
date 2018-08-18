package com.practice.cyclic.barrier.ex;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierEx {

	public static void main(String[] args) {
		Runnable afterCB = () -> {
			System.out.println("Post Barrier Action");
		};
		
		CyclicBarrier cb = new CyclicBarrier(3, afterCB);
		Thread t1 = new Thread(new MyThread(cb));
		Thread t2 = new Thread(new MyThread(cb));
		Thread t3 = new Thread(new MyThread(cb));
		
		t1.start();
		t2.start();
		t3.start();
	}
}

class MyThread implements Runnable {
	
	private CyclicBarrier cyclicBarrier;
	
	public MyThread(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}
	
	@Override
	public void run() {
		System.out.println("Before Barrier");
		try {
			cyclicBarrier.await();
			System.out.println("After Barrier");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}