package com.practice.producer.consumer.ex;

public class Test {

	static DataProducerCosumer data = new DataProducerCosumer();
	
	public static void main(String[] args) throws InterruptedException {
		Thread produce = new Thread(new Runnable() {
			public void run() {
				try {
					data.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread consume = new Thread(new Runnable() {
			public void run() {
				try {
					data.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		produce.start();
		consume.start();
		
		produce.join();
		consume.join();
		
		
	}
}
