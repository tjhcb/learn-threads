package com.practice.producer.consumer.ex;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataProducerCosumer {
	
	private static LinkedList<String> queue = new LinkedList<>();
	private static List<String> list = Collections.synchronizedList(new LinkedList<String>());
	private final int MAX_CAPACITY = 10;
	private final int MIN_CAPACITY = 0;
	private int i;
	
	/*public void produce() throws InterruptedException{
		while(true){
			synchronized (this) {
			if(queue.size() == MAX_CAPACITY){
				System.out.println("producer waiting");
				wait();
			} else {
			queue.add(""+ ++i);
			if(i == 11){
				queue.add(null);
				System.out.println("Poison Pill");
				break;
			}
			notify();
			Thread.sleep(1000);
			System.out.println("Data produced : " + queue.toString());
			}
		}
		}
	}
	
	public void consume() throws InterruptedException{
		String value = null;
		while(true){
			synchronized (this) {
			if(queue.size() == MIN_CAPACITY){
				System.out.println("consumer waiting");
				wait();
			} else{
			value = queue.removeFirst();
			if(value == null){
				System.out.println("Poison Pill");
				break;
			}
			notify();
			System.out.println("Data consumed : " + value);}
			
			Thread.sleep(500);
		}
		}
	}*/
	
	/*Lock lock = new ReentrantLock();
	Condition fullCondition = lock.newCondition();
	Condition emptyCondition = lock.newCondition();
	
	public void produce() throws InterruptedException{
		while(true){
			lock.lock();
			if(queue.size() == MAX_CAPACITY){
				System.out.println("producer waiting");
				fullCondition.await(500, TimeUnit.MILLISECONDS);
			} else {
				queue.add(""+ ++i);
				fullCondition.signalAll();
				Thread.sleep(1000);
				System.out.println("Data produced : " + queue.toString());
			}
			lock.unlock();
		}
	}
	
	public void consume() throws InterruptedException{
		String value = null;
		while(true){
			lock.lock();
			if(queue.size() == MIN_CAPACITY){
				System.out.println("consumer waiting");
				emptyCondition.await(200, TimeUnit.MILLISECONDS);
			} else{
				value = queue.removeFirst();
				emptyCondition.signalAll();
				System.out.println("Data consumed : " + value);
			}
			Thread.sleep(400);
			lock.unlock();
		}
	}*/
	
	public void produce() throws InterruptedException{
		while(i<20){
			list.add(""+ ++i);
			Thread.sleep(500);
			System.out.println("produce: " + list.toString());
		}
	}
	
	public void consume() throws InterruptedException{
		System.out.println(list.size());
		while(list.size() > 0){
			System.out.println(list.size());
			list.remove(0);
			Thread.sleep(600);
		}
		System.out.println("consume: " + list.toString());
	}
}