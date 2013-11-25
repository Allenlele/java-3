package com.wds.java.concurrency.chapter1.fourth;

import java.io.File;

/**
 * 1、创建FileSearch类并实现Runnable接口
 * @author wds
 *
 */
public class FileSearch implements Runnable {
	
	/*
	 * 2、声明两个私有变量，一个为将要查找的文件名，另一个为初始化目录
	 * 实现构造方法，并初始这两个私有变量
	 */
	private String initPath;
	private String fileName;
	public FileSearch(String initPath, String fileName) {
		this.initPath = initPath;
		this.fileName = fileName;
	}

	/**
	 * 3、实现run方法，检查fileName是否为目录，如果是调用processDirectory()方法，该方法
	 * 会抛出InterruptedException异常，因为我们要捕获它
	 */
	@Override
	public void run() {
		File file = new File(initPath);
		if(file.isDirectory()){
			try {
				directoryProcess(file);
			} catch (InterruptedException e) {
				System.out.printf("%s: The search has been interruptd", Thread.currentThread().getName());
			}
		}
	}

	/**
	 * 4、实现directoryProcess()方法，该方法获取文件及子目录并处理它们，
	 * 对于每个目录，将以当前目录为参数递归调用该方法，对于每个文件，将调用fileProcess()方法，
	 * 在处理过所有文件及文件夹之后，将检查线程是否已经被中断，如果是抛出InterruptedException异常
	 */
	private void directoryProcess(File file) throws InterruptedException {
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
		
		if(Thread.interrupted()){
			throw new InterruptedException();
		}
	}

	/**
	 * 5、实现fileProcess()方法（原书中为processFile()）,该方法将
	 * file的文件名与待查找的文件名对比，如果相同，在控制台输出信息。对比之后，线程检查是否已经中断，
	 * 如果中断，抛出InterruptedException异常
	 */
	private void fileProcess(File file) throws InterruptedException {
		if(file.getName().equals(fileName)){
			System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getName());
		}else{
			System.out.println(file.getName());
		}
		
		if(Thread.interrupted()){
			throw new InterruptedException();
		}
	}

}
