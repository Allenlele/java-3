package com.wds.java.concurrency.chapter3.fifth;

import java.util.concurrent.Phaser;

public class Main {

	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);
		
		FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
		FileSearch apps= new FileSearch("C:\\Program Files","log",phaser);
		FileSearch documents= new FileSearch("C:\\Documents And Settings","log",phaser);
		
		Thread systemThread = new Thread(system, "system");
		systemThread.start();
		
		Thread appsThread = new Thread(apps, "apps");
		appsThread.start();
		
		Thread documentThread = new Thread(documents, "documents");
		documentThread.start();
		
		try {
			systemThread.join();
			appsThread.join();
			documentThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Terminated: " + phaser.isTerminated());
	}

}
