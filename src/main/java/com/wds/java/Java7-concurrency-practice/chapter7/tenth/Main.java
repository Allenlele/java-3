package com.wds.java.concurrency.chapter7.tenth;

/**
 * 16
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) throws Exception{
		//17
		ParkingCounter counter = new ParkingCounter(5);
		
		//18
		Sensor1 sensor1=new Sensor1(counter);
		Sensor2 sensor2=new Sensor2(counter);
		Thread thread1=new Thread(sensor1);
		Thread thread2=new Thread(sensor2);
		thread1.start();
		thread2.start();
		
		//19
		thread1.join();
		thread2.join();
		
		//20
		System.out.printf("Main: Number of cars: %d\n", counter.get());
		
		//21
		System.out.printf("Main: End of the program.\n");
	}

}
