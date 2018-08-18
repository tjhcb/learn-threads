package com.practice.producer.consumer.ex;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Producer/Consumer problem using BlockingQueue in Java
 * 
 * @author tjhcb
 */
public class BlockingQueueEx {
	
	public static void main(String[] args) {
		BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(10);
		
		Thread t1 = new Thread(new Producer(queue));
		Thread t2 = new Thread(new Consumer(queue));
		
		t1.start();
		t2.start();
	}

}

class Producer implements Runnable {
	
	BlockingQueue<Message> queue;
	
	public Producer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		Message msg = new Message();
		try {
			for(int i=0; i<=20; i++){
				msg.setMsg(""+i);
				queue.add(msg);
				System.out.println("Produced : " + msg.getMsg());
				Thread.sleep(500);
			}
			msg.setMsg("exit");
			queue.add(msg);		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Consumer implements Runnable {
	
	BlockingQueue<Message> queue;
	
	public Consumer(BlockingQueue<Message> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			Message msg;
			while((msg = queue.take()).getMsg() != "exit"){
				System.out.println("Consumed: " + msg.getMsg());
				Thread.sleep(500);
			}
			System.out.println(queue.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}