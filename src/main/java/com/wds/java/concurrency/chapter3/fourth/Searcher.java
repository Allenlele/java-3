package com.wds.java.concurrency.chapter3.fourth;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 13、现在已经拥有两个额外的类，该是时候实现线程，首先实现Searhcer类，该类将在拥有随机数的矩阵中的确定一行
 * 查找一个数字，创建Searcher类并实现Runnable接口
 * @author wds
 *
 */
public class Searcher implements Runnable {
	//14、定义属性firstRow和lastRow，这两个属性决定将要查找的子集合的行数
	private int firstRow;
	private int lastRow;
	//15、定义矩阵属性mock
	private MatrixMock mock;
	//16、定义Result属性result
	private Result result;
	//17、定义int属性number，保存待查找的数字
	private int number;
	//18、定义CyclicBarrier对象barrier
	private final CyclicBarrier barrier;

	//19、实现构造方法并初始化上面定义的属性
	public Searcher(int firstRow, int lastRow, MatrixMock mock, Result result,
			int number, CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.mock = mock;
		this.result = result;
		this.number = number;
		this.barrier = barrier;
	}

	//20、实现run()方法，查找数字，使用一个内部计数器counter，保存每一行中出现待查找的数字的次数
	@Override
	public void run() {
		int counter;
		//21、控制台输出信息分配的行的范围
		System.out.printf("%s: Processing lines from %d to %d.\n",Thread.currentThread().getName(),firstRow,lastRow);
		//22、处理分配给线程的所有行，每一行，计算机待查找数出现的次数，并保存到行数与Result中对应的位置
		for(int i = firstRow; i < lastRow; i++){
			int row[] = mock.getRow(i);
			counter = 0;
			for(int j = 0; j < row.length; j++){
				if(row[j] == number){
					counter++;
				}
			}
			result.setData(i, counter);
		}
		//23、控制台输出消息表明对象已经完成查找
		System.out.printf("%s: Lines processed.\n",Thread.currentThread().getName());
		//24、调用CyclicBarrier类的await()方法，添加必要的代码处理该方法抛出的InterruptedException和BrokenBarrierException异常
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
