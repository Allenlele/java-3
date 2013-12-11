package com.wds.java.concurrency.chapter6.fifth;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 10
 * @author wds
 *
 */
public class Main {

	public static void main(String[] args) {
		
		//11
		ConcurrentSkipListMap<String, Contact> map;
		map = new ConcurrentSkipListMap<>();
		
		//12
		Thread threads[] = new Thread[25];
		int counter = 0;
		
		//13
		for(char i = 'A'; i < 'Z'; i++){
			Task task = new Task(map, String.valueOf(i));
			threads[counter] = new Thread(task);
			threads[counter].start();
			counter++;
		}
		
		//14
		for(int i = 0; i < 25; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//15
		System.out.printf("Main: Size of the map: %d\n", map.size());
		
		Map.Entry<String, Contact> element = map.firstEntry();
		Contact contact = element.getValue();
		System.out.printf("Main: First Entry: %s: %s\n", contact.getName(), contact.getPhone());
		
		//16
		element = map.lastEntry();
		contact = element.getValue();
		System.out.printf("Main: Last Entry: %s: %s\n", contact.getName(), contact.getPhone());

		//17
		System.out.printf("Main: Submap from A1996 to B1002:\n");
		ConcurrentNavigableMap<String, Contact> submap = map.subMap("A1996", "B1002");
		do {
			element = submap.pollFirstEntry();
			if(element != null){
				contact = element.getValue();
				System.out.printf("%s: %s\n", contact.getName(), contact.getPhone());
			}
		} while (element != null);
	}

}
