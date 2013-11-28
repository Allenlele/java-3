package com.wds.java.concurrency.chapter5.first;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * 8、创建Task类，继承RecursiveAction类
 * @author wds
 *
 */
public class Task extends RecursiveAction {
	

	/*
	 * 9、声明这个类的系列版本号UID，这个属性是有必要的，因为父类RecursiveAction、ForkJoinTask类实现了Serializable接口
	 */
	private static final long serialVersionUID = -8061165630770780257L;
	
	/*
	 * 10、声明products属性
	 */
	private List<Product> products;
	
	/*
	 * 11、声明两个私有的int属性，一个为first一个为last，这两个属性决定了处理产品的代码块
	 */
	private int first;
	private int last;
	
	/*
	 * 12、声明私有double属性，名为increment，存放产品价格的累加结果
	 */
	private double increment;

	/*
	 * 13、实现构造方法，并初始类的所有属性
	 */
	public Task(List<Product> products, int first, int last, double increment) {
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	/*
	 * 14、实现compute()方法，实现任务的逻辑 
	 */
	@Override
	protected void compute() {
		/*
		 * 15、如果last和first这两个属性之间的值小于10时（小于10时任务不修改更新价格），增加产品价格
		 */
		if(last-first < 10){
			updatePrices();
		/*
		 * 16、如果last和first这两个属性值之差大于或等10时，创建两个Task对象，其中一个前半部分的产品，
		 * 另一个处理后半部分，并调用invokeAll()方法使任务运行在ForkJoinPool
		 */
		}else{
			int middle = (last + first) / 2;
			System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
			Task t1 = new Task(products, first, middle + 1, increment);
			Task t2 = new Task(products, middle + 1, last, increment);
			invokeAll(t1, t2);
		}
	}

	/*
	 * 17、实现updatePrices()方法，该方法会更新下标在first和last之间的产品价格
	 */
	private void updatePrices() {
		for(int i = first; i < last; i++){
			Product product = products.get(i);
			product.setPrice(product.getPrice() * (1 + increment));
		}
	}

}
