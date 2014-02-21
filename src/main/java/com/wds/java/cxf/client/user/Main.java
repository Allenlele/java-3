package com.wds.java.cxf.client.user;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wds.java.cxf.client.user.code.IUserService;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/cxf/spring-cxf-client.xml");
		IUserService service = (IUserService) context.getBean("userServiceTwo");
		List<String> userNames = service.getUserName();
		
		service = (IUserService) context.getBean("userServiceOne");
		List<String> userNamesOne = service.getUserName();
		
		for (String string : userNames) {
			System.out.println(string);
		}
		
		for (String string : userNamesOne) {
			System.out.println(string);
		}
	}

}
