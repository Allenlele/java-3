package com.wds.java.concurrency.chapter3.seventh;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 8、实现消费者，创建Consumer类，实现Runnable接口
 * @author wds
 *
 */
public class Consumer implements Runnable {
	//9、创建List<String>对象buffer，用于和生产者交换的数据结构
	private List<String> buffer;
	//10、定义Exchanger<List<String>>对象exchanger，用于同步生产者、消费者的exchanger对象
	private final Exchanger<List<String>> exchanger;
	//11、实现构造方法并初始化两个属性
	public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	//12、实现run()方法，创建一个10次循环
	@Override
	public void run() {
		int cycle = 1;
		for(int i = 0; i < 10; i++){
			System.out.printf("Consumer: Cycle %d\n", cycle);
			//13、每一次循环中，在开始就调用exchanger()方法与生产者同步，消费者需要消费数据，由于
			//该方法会抛出InterruptedException异常，需要代码处理它
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//14、输出生产者发送的10个字符串到控制台，并将其从缓冲区中删除，直余为空
			System.out.println("Consumer: " + buffer.size());
			for(int j = 0; j < 10; j++){
				String message = buffer.get(0);
				System.out.println("Consumer: " + message);
				buffer.remove(0);
			}
			
			cycle++;
		}
	}

}
