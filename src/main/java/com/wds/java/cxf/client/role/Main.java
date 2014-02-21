package com.wds.java.cxf.client.role;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wds.java.cxf.client.role.code.Entry;
import com.wds.java.cxf.client.role.code.ISecurityService;
import com.wds.java.cxf.client.role.code.MappingUserValue;
import com.wds.java.cxf.client.role.code.User;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/conf/cxf/spring-cxf-client.xml");
		ISecurityService service = (ISecurityService) context.getBean("securityService");
		MappingUserValue value = service.getAuthority();
		for (Entry entry : value.getEntries()) {
			System.out.print(entry.getKey() + "：");
			for (User user : entry.getValue()) {
				System.out.println(" name:" + user.getName() + " code:" + user.getCode());
			}
		}
	}

}
