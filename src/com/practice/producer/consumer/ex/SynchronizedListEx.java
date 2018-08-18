package com.practice.producer.consumer.ex;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Producer/Consumer problem using SynchronizedList in Java
 * 
 * @author tjhcb
 */
public class SynchronizedListEx {

	public static void main(String[] args) {
		List<Message> queue = Collections.synchronizedList(new LinkedList<Message>());
		
		Thread t1 = new Thread(new ListProducer(queue));
		Thread t2 = new Thread(new ListConsumer(queue));
		
		t1.start();
		t2.start();
	}
}


class ListProducer implements Runnable {
	
	List<Message> queue;
	
	public ListProducer(List<Message> queue) {
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
				Thread.sleep(510);
			}
			msg.setMsg("exit");
			queue.add(msg);		
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class ListConsumer implements Runnable {
	
	List<Message> queue;
	
	public ListConsumer(List<Message> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			Message msg;
			while(true){
				if(queue.size() > 0){
					if((msg = queue.remove(0)).getMsg() != "exit"){
						System.out.println("Consumed: " + msg.getMsg());
					} else{
						break;
					}
				} else{
					System.out.println("queue is empty");
				}
				Thread.sleep(500);
			}
			System.out.println(queue.toString());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
