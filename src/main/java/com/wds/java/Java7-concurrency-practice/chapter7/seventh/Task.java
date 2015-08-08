package com.wds.java.concurrency.chapter7.seventh;

/**
 * 9
 * @author wds
 *
 */
public class Task extends MyWorkerTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//10
	private int array[];
	//书中无此行代码
	private int start, end;
	
	//11
	public Task(String name, int array[], int start, int end) {
		super(name);
		this.array = array;
		this.start = start;
		this.end = end;
	}

	//12
	@Override
	protected void compute() {
		if(end -start > 100){
			int mid = (end + start) / 2;
			Task task1 = new Task(this.getName() + "1", array, start, mid);
			Task task2 = new Task(this.getName() + "2", array, mid, end);
			invokeAll(task1, task2);
		//13
		}else{
			for(int i = start; i < end; i++){
				array[i]++;
			}
			
			//14
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
