package com.wds.java.concurrency.chapter5.third;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * 1、创建FolderProcessor类，并继承RecursiveTask类，泛型化参数为List<String>
 * @author wds
 *
 */
public class FolderProcessor extends RecursiveTask<List<String>> {

	/*
	 * 2、定义序列版本号UID，这个属性是非常有必要的，因为这该类的父类RecursiveTask和ForkJoinTask类实现了Serializable接口。
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 3、定义私有String类型的属性path，该属性存放所要处理的文件夹的全路径
	 */
	private String path;
	
	/*
	 * 4、定义私有String类型属性extension，该属性保存将要查找的文件扩展名
	 */
	private String extension;
	
	/*
	 * 5、实现构造方法，并初始化属性
	 */
	public FolderProcessor(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	/*
	 * 6、实现coumpute()方法，返回一个和RecursiveTask参数化类型List<String>一样的对象
	 */
	@Override
	protected List<String> compute() {
		//7、定义一个List<String>对象，保存文件中的文件名
		List<String> list = new ArrayList<>();
		
		//8、定义FolderProcessor任务列表，存放即将对子文件夹处理的子任务
		List<FolderProcessor> tasks = new ArrayList<>();
		
		//9、获取文件夹的内容
		File file = new File(path);
		File content[] = file.listFiles();
		
		//10、文件夹中的每一个元素，如果是一个子文件夹，创建一个FolderProcessor对象，调用fork()方法异步执行
		if(content != null){
			for(int i = 0; i < content.length; i++){
				if(content[i].isDirectory()){
					FolderProcessor task = new FolderProcessor(content[i].getAbsolutePath(), extension);
					task.fork();
					tasks.add(task);
				//11、否则，调用checkFile()方法，检查当前文件的扩展名与待查找的扩展名是否相同，如果相同，将文件的全路径存入刚才定义的list中
				}else{
					if(checkFile(content[i].getName())){
						list.add(content[i].getAbsolutePath());
					}
				}
			}
			
			//12、如果FolderProcessor子任务的列表大小超过50个元素时，在循环中向控制台输出信息
			if(tasks.size() > 50){
				System.out.printf("%s: %d tasks run.\n", file.getAbsolutePath(), tasks.size());
			}
			
			//13、调用辅助方法addResultsFromTasks()，将由这个任务启动的子任务返回的结果添加到文件列表中，需要传递的参数为：字符列表，FolderProcessor子任务列表
			addResultsFromTasks(list, tasks);
		}
		
		//14、返回字符列表
		return list;
	}

	/*
	 * 15、实现addResultsFromTasks()方法，对每一个存储在任务列表中的任务，调用其join()方法，等待其结果和返回的结果，将其结果
	 * 通过调用addAll()方法添加到字符列表中
	 */
	private void addResultsFromTasks(List<String> list,
			List<FolderProcessor> tasks) {
		for(FolderProcessor item : tasks){
			list.addAll(item.join());
		}
	}

	/*
	 * 16、实现checkFile()方法，该方法是对比传递的参数值是否以所要查找的扩展后缀相，如果相同，返回true,否则为false
	 */
	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}

}
