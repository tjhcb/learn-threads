package com.practice.fork.join.ex;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinEx extends RecursiveTask<Integer> {

	private Integer input;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool pool = new ForkJoinPool();
		ForkJoinEx calc = new ForkJoinEx(4);
		ForkJoinTask<Integer> result = pool.submit(calc);
		while(!result.isDone()){
//			Thread.sleep(1000);
		}
		System.out.println(result.get());
	}

	public ForkJoinEx(Integer input) {
		this.input = input;
	}
	
	@Override
	protected Integer compute() {
		int i =0;
		if(input <= 1){
			return input;
		}
		
		ForkJoinEx instance = new ForkJoinEx(input -1);
		
		instance.fork();
		
		i = input * input + instance.join();
		return i;
	}
}
