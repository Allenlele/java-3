package com.wds.java.concurrency.chapter3.fifth;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 1、创建FileSearch类，实现Runnable接口，该类实现在指定的文件夹及子文件夹中查找指定扩展名的文件操作，并且修改时间为24小时内
 * @author wds
 *
 */
public class FileSearch implements Runnable {
	//2、定义String属性，保存查找操作开始的文件夹路径
	private String initPath;
	//3、定义另一个String属性，保存将要查找的文件名后缀
	private String end;
	//4、定义List属性，保存已经查找到的文件全路径
	private List<String> results;
	//5、最后，定义Phaser对象，控制任务不同阶段的同步
	private Phaser phaser;
	//6、实现构造方法，初始化类的属性，接受初始化文件夹的路径、文件的扩展名有phaser
	public FileSearch(String initPath, String end, Phaser phaser) {
		this.initPath = initPath;
		this.end = end;
		this.results = new ArrayList<>();
		this.phaser = phaser;
	}
	
	//7、现在，实现一个辅助的方法，由run()方法调用，第一个就是directoryProcess()方法，接受一个File对象作为参数，
	//处理所有文件及文件夹。对于第一个文件夹，方法将递归调用，并将该文件夹作为参数传递。对于第一个文件，方法调用fileProcess()方法
	private void directoryProcess(File file) {
		File list[] = file.listFiles();
		if(list != null){
			for(int i = 0; i < list.length; i++){
				if(list[i].isDirectory()){
					directoryProcess(list[i]);
				}else{
					fileProcess(list[i]);
				}
			}
		}
	}

	//8、实现fileProcess()方法，接受一个File对象作为参数，检查文件后缀名是否等于将要查找的，
	//如果相同，将文件的全路径添加到列表result
	private void fileProcess(File file) {
		if(file.getName().endsWith(end)){
			results.add(file.getAbsolutePath());
		}
	}
	
	//9、实现filterResults()方法，不接受任何参数，过滤在第一个阶段获取的文件列表，删除修改时间超过一个小时的文件，
	//首先创建一个新的空的列表，获取时间
	private void filterResults(){
		List<String> newResults = new ArrayList<>();
		long actualDate = new Date().getTime();
		//10、之后，遍历列表中的每个元素，对列表中的第一个路径，创建一个File对象，通过 个对获取时间
		for(int i = 0; i < results.size(); i++){
			File file = new File(results.get(i));
			long fileDate = file.lastModified();
			//11、之后文件时间与实际时间，如果之差小于一天，将文件的全路径添加到新的列表中
			if(actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)){
				newResults.add(results.get(i));
			}
		}
		//12、修改旧的result为新的result
		results = newResults;
	}
	
	//13、实现checkResult()方法，该方法将在第一、二阶段结束时调用，检查list是否为空，不接受任何参数
	private boolean checkResults(){
		//14、首先，检查列表大小，如果为0，对象在控制台输出信息表明此时的情况，之后，调用Phaser对象的arriveAndDeregister()方法
		//通过对象线程已经完成阶段，并离开分阶段的操作
		if(results.isEmpty()){
			System.out.printf("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),phaser.getPhase());
			System.out.printf("%s: Phase %d: End.\n",Thread.currentThread().getName(),phaser.getPhase());
			phaser.arriveAndDeregister();
			return false;
		//15、否则，如果列表中有元素，对象就在控制台输出此时的情况，之后，调用Phaser对象的arriveAndAwaitAdvance()，
		//通知对象线程已经完成的阶段，线程将阻塞，直到所有参与的分阶段操作线程完成实际的阶段
		}else{
			System.out.printf("%s: Phase %d: %d results.\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
			phaser.arriveAndAwaitAdvance();
			return true;
		}
	}
	
	//16、实现showInfo()方法，控制台输出结果列表的元素
	private void showInfo(){
		for(int i = 0; i < results.size(); i++){
			File file = new File(results.get(i));
			System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsolutePath());
		}
		phaser.arriveAndAwaitAdvance();
	}

	//17、实现run()方法，使用上述描述的辅助方法执行操作，Phaser对象控制两个阶段的变更，首先调用Phaser类的arriveAndAwaitAdvance()方法，
	//当所有线程都已经抵达时才开始查询
	@Override
	public void run() {
		phaser.arriveAndAwaitAdvance();
		//18、控制台输出信息表明查询任务开始
		System.out.printf("%s: Starting.\n", Thread.currentThread().getName());
		//19、检查initPath属性，并调用directoryProcess()方法在其文件夹或子文件夹中查找指定的扩展名的文件。
		File file = new File(initPath);
		if(file.isDirectory()){
			directoryProcess(file);
		}
		//20、调用checkResults()方法检查列表中是否有结果，如果木有，以return关键字结束执行
		if(!checkResults()){
			return;
		}
		//21、调用filterResults()方法过滤列表
		filterResults();
		//22、再次调用checkResults()方法检查，如果无结果，以return关键字结束执行
		if(!checkResults()){
			return;
		}
		//23、调用showInfo()方法控制台输出最终的列表结果，注销线程，最后输出信息表明线程执行结束
		showInfo();
		phaser.arriveAndDeregister();
		System.out.printf("%s Work complete.ed\n", Thread.currentThread().getName());
	}

}
