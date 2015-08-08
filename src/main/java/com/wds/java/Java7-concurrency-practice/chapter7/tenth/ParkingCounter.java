package com.wds.java.concurrency.chapter7.tenth;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author wds
 *
 */
public class ParkingCounter extends AtomicInteger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//2
	private int maxNumber;
	
	//3
	public ParkingCounter(int maxNumber){
		set(0);
		this.maxNumber = maxNumber;
	}
	
	//4
	public boolean carIn(){
		for(;;){
			int value = get();
			
			//5
			if(value == maxNumber){
				System.out.printf("Parkingcounter: The parking lot is full.\n");
				return false;
			
			//6
			}else{
				int newValue = value + 1;
				boolean changed = compareAndSet(value, newValue);
				if(changed){
					System.out.printf("ParkingCounter: A car has entered.\n");
					return true;
				}
			}
		}
	}
	
	//7
	public boolean carOut(){
		for(;;){
			int value = get();
			if(value == 0){
				System.out.printf("ParkingCounter: The parking lot is empty.\n");
				return false;
			}else{
				int newValue = value - 1;
				boolean changed = compareAndSet(value, newValue);
				if(changed){
					System.out.printf("ParkingCounter: A car has gone out\n");
					return true;
				}
			}
		}
	}

}
