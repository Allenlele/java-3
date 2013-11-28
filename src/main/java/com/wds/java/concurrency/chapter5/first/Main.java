package com.wds.java.concurrency.chapter5.first;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * 18、实现主类及main()方法
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//19、使用ProductListGenerator类创建10000个产品
		ProductListGenerator generator = new ProductListGenerator();
		
		//20、创建Task对象，更新产品列表中的所有产品，first参数为0,last为10000(prodcuts.size())
		List<Product> products = generator.generate(10000);
		Task task = new Task(products, 0, products.size(), 0.20);
		
		//21、使用无参构造方法创建ForkJoinPool对象
		ForkJoinPool pool = new ForkJoinPool();
		
		//22、执行任务
		pool.execute(task);
		
		//23、下面这块代码的功能是每隔5毫秒向控制台输出线程池的变化信息、参数值，直到任务完成结束
		do{
			System.out.printf("Main: Thread Count: %d\n", pool.getActiveThreadCount());
			System.out.printf("Main: Thread Steal: %d\n", pool.getStealCount());
			System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}while(!task.isDone());
		
		//24、调用shutdown()方法关闭池
		pool.shutdown();
		
		//25、调用isCompletedNormally()方法检查任务是否无错误的完成，如果无向控制台输入完成信息
		if(task.isCompletedNormally()){
			System.out.printf("Main: The process has completed normally.\n");
		}
		
		//26、经过累加之后，产品的价格预计为12，输出所有产品价格不同于12的产品信息并正确的检查它们
		for(int i = 0; i < products.size(); i++){
			Product product = products.get(i);
			if(product.getPrice() != 12){
				System.out.printf("Product %s: %f\n", product.getName(), product.getPrice());
			}
		}
		
		//27、输出信息以示程序运行结束
		System.out.printf("Main: End of the program.\n");
	}

}
