package com.wds.java.concurrency.chapter8.first;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * 10、创建主类，实现main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		//11、创建MyLock对象lock
		MyLock lock = new MyLock();
		
		//12、创建5个线程数组
		Thread threads[] = new Thread[5];
		
		//13、创建5个线程执行Task对象
		for(int i = 0; i < 5; i++){
			Task task = new Task(lock);
			threads[i] = new Thread(task);
			threads[i].start();
		}
		
		//14、创建15个循环
		for(int i = 0; i < 15; i++){
			//15、控制台输出锁拥有者的名称
			System.out.printf("Main: Logging the Lock.\n");
			System.out.printf("************************\n");
			System.out.printf("Lock: Owner : %s\n",lock.getOwnerName());
			
			//16、显示锁中线程队列的数量及名称
			System.out.printf("Lock: Queued Threads: %s\n",lock.hasQueuedThreads());
			if (lock.hasQueuedThreads()) {
				System.out.printf("Lock: Queue Length: %d\n",lock.getQueueLength());
				System.out.printf("Lock: Queued Threads: ");
				Collection<Thread> lockedThreads = lock.getThreads();
				for (Thread lockedThread : lockedThreads) {
					System.out.printf("%s ", lockedThread.getName());
				}
				System.out.printf("\n");
			}
			
			//17、显示Lock对象的公平和状态信息
			System.out.printf("Lock: Fairness: %s\n",lock.isFair());
			System.out.printf("Lock: Locked: %s\n",lock.isLocked());
			System.out.printf("************************\n");
			
			//18、线程休眠1分钟并关闭循环
			TimeUnit.SECONDS.sleep(1);
		}
	}

}
