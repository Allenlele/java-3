package com.wds.java.concurrency.chapter7.tenth;

/**
 * 12
 * @author wds
 *
 */
public class Sensor2 implements Runnable {

	//13
	private ParkingCounter counter;
	
	//14
	public Sensor2(ParkingCounter counter) {
		this.counter = counter;
	}
	
	//15
	@Override
	public void run() {
		counter.carIn();
		counter.carOut();
		counter.carOut();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
		counter.carIn();
	}

}
