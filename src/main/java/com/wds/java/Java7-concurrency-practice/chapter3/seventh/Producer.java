package com.wds.java.concurrency.chapter3.seventh;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 1、首先，实现生产者，创建Producer类，实现Runnable接口
 * @author wds
 *
 */
public class Producer implements Runnable {
	//2、定义List<String>对象buffer，用于生产者和消费者交换的数据结构
	private List<String> buffer;
	//3、定义Exchanger<List<String>>对象exchanger，用于同步生产者和消费者的exchanger对象
	private final Exchanger<List<String>> exchanger;
	//4、实现构造方法，并初始化这两个参数
	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	//5、实现run()方法，10个循环
	@Override
	public void run() {
		int cycle = 1;
		for(int i = 0; i < 10; i++){
			System.out.printf("Producer: Cycle %d\n", cycle);
			//6、每个循环中，添加10个字符串到缓冲区
			for(int j = 0; j < 10; j++){
				String message = "Event " + ((i*10) +j);
				System.out.printf("Producer: %s\n", message);
				buffer.add(message);
			}
			//7、调用exchange方法，与消费者交换数据，由于该方法会抛出InterruptedException异常，
			//需要一些代码处理它
			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.printf("Producer: %d\n", buffer.size());
			cycle++;
		}
	}

}
