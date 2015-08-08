package com.wds.java.concurrency.chapter8.fifth;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 2、创建类MyLogger
 * @author wds
 *
 */
public class MyLogger {
	//3、声明静态Handler属性handler
	private static Handler handler;
	
	//4、实现静态方法getLogger()，创建Logger对象，使用它输出日志信息，接收一个名为name的String类型参数
	public static Logger getLogger(String name){
		//5、使用Logger类的getLogger()方法获取java.util.logging.Logger对象，方法接收一个name参数
		Logger logger = Logger.getLogger(name);
		
		//6、建立日志级别，使用setLevel()方法输出所有日志信息
		logger.setLevel(Level.ALL);
		
		//7、如果handler属性为null值，创建一个新的FileHandler对象 ，将日志信息输出到recipe8.log中，
		//使用setFormatter()方法为handler指定一个formatter
		try {
			if(handler == null){
				handler = new FileHandler("recipe8.log");
				Formatter format = new MyFormatter();
				handler.setFormatter(format);
			}
			
			//8、如果Logger对象木有与之关联的handler，使用addHandler()方法为其指定
			if(logger.getHandlers().length == 0){
				logger.addHandler(handler);
			}
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
		
		//9、返回创建的Logger对象
		return logger;
	}
}
