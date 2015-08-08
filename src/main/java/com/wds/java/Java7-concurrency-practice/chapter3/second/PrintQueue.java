package com.wds.java.concurrency.chapter3.second;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	private Boolean freePrinters[];
	private Lock lockPrinters;
	private final Semaphore semaphore;
	
	public PrintQueue() {
		semaphore = new Semaphore(3);
		freePrinters = new Boolean[3];
		for(int i = 0; i < 3; i++){
			freePrinters[i] = true;
		}
		lockPrinters = new ReentrantLock();
	}
	
	//4、修改printJob()方法，接收一个Object类型的参数document
	public void printJob(Object document){
		//5、首先，调用acquire()方法获取对信号量的访问，由于该方法会抛出InterruptedException异常
		//因此需要代码处理它
		try {
			semaphore.acquire();

			//6、使用getPrinter()获得打印机设置的打印工作的值
			int assignedPrinter = getPrinter();
			
			//7、之后，模拟打印功能，并等待随机时间
			long duration = (long)(Math.random() * 10);
			System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",Thread.currentThread().getName(), assignedPrinter,duration);
			TimeUnit.SECONDS.sleep(duration);
			
			//8、最后调用realeas()释放信号量并标识打印机可用，将下标为assignedPrinter的freePrinters元素设置为true
			freePrinters[assignedPrinter] = true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			semaphore.release();
		}
	}

	//9、实现getPrinter()方法，私有方法，无参数
	private int getPrinter() {
		//10、首先，定义int变量，存放打印机的下标
		int ret = -1;
		
		//11、之后获取lockPrinters对象的访问
		try {
			lockPrinters.lock();
			
			//12、之后，找到freePrinters数组中第一个为true的元素，保存下标到变量中，并将值修改为false，因为这个打印机正忙着呢
			for (int i = 0; i < freePrinters.length; i++) {
				if(freePrinters[i]){
					ret = i;
					freePrinters[i] = false;
					break;
				}
			}
		//13、最后，释放lockPrinters对象，返回值为true的下标
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			lockPrinters.unlock();
		}
		return ret;
	}
}
