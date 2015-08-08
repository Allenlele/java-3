package com.wds.java.concurrency.chapter6.fourth;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 1
 * @author wds
 *
 */
public class Event implements Delayed {
	
	//2
	private Date startDate;
	
	//3
	public Event(Date startDate) {
		this.startDate = startDate;
	}

	//4
	@Override
	public int compareTo(Delayed o) {
		long result = this.getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		if(result < 0){
			return -1;
		}else if(result > 0){
			return 1;
		}
		return 0;
	}

	//5
	@Override
	public long getDelay(TimeUnit unit) {
		Date now = new Date();
		long diff = startDate.getTime() - now.getTime();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

}
