package com.practice.producer.consumer.ex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	static DataProducerCosumer data = new DataProducerCosumer();
	
	public static void main(String[] args) throws InterruptedException {
		Thread produce = new Thread(() -> {
				try {
					data.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		});
		
		Thread consume = new Thread(() -> {
				try {
					data.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		});
		
		ExecutorService exs = Executors.newFixedThreadPool(2);
		exs.execute(produce);
		exs.execute(consume);
		/*produce.start();
		consume.start();
		
		produce.join();
		consume.join();*/
		
		
	}
}
