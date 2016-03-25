package org.xiaoqiaotq;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadOrder {
	public static void main(String[] args) {
		int threads=7;
		final Container container=new Container(threads);
		for (int i = 0; i < threads; i++) {
			final int index=i;
			new Thread(new Runnable() {
				public void run() {
//					System.err.println((char)('A'+index));
					try {
						container.write((char)('A'+index));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			},"thread"+i).start();
		}
	}
}
class Container{
	private int index=0;
	private Lock lock=new ReentrantLock();
	private Map<Integer,Condition> map=new HashMap();
	private int threads;
	
	public Container(int threads) {
		this.threads=threads;
		for (int i = 0; i < threads; i++) {
			map.put(i, lock.newCondition());
		}
	}

	public void write(char ch) throws InterruptedException{
		lock.lock();
		try{
			Condition condition=map.get(ch-'A');
			while(ch!=index+'A'){
				System.err.println(Thread.currentThread().getName()+" is awaiting ");
				condition.await();
			}
			System.err.println(ch);
			index++;
			if(index<threads){
				System.err.println("try to signal thread"+index+" ");
				map.get(index).signal();;
			}
		}
		finally{
			lock.unlock();
		}
	}
}