package com.wds.java.cxf.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ClientHeaderInterceptor extends AbstractPhaseInterceptor<SoapMessage>{

	private String username;
	private String password;
	
	public ClientHeaderInterceptor(String phase) {
		super(Phase.PREPARE_SEND);//在准备发送SOAP消息时，调用此拦截器
	}
	
	public ClientHeaderInterceptor(String username, String password) {
		this("");
		this.username = username;
		this.password = password;
	}

	@Override
	public void handleMessage(SoapMessage msg) throws Fault {
		List<Header> headers = msg.getHeaders();
		Document doc = DOMUtils.createDocument();
		Element authEle = doc.createElement("header");
		Element usernameEle = doc.createElement("username");
		Element passwordEle = doc.createElement("password");
		
		usernameEle.setTextContent(username);
		passwordEle.setTextContent(password);
		
		authEle.appendChild(usernameEle);
		
		authEle.appendChild(passwordEle);
		
		Header header = new Header(new QName("authHeaer"), authEle);
		
		headers.add(header);
		
		authEle = doc.createElement("headerTwo");
		usernameEle = doc.createElement("username");
		passwordEle = doc.createElement("password");
		
		usernameEle.setTextContent(username);
		passwordEle.setTextContent(password);
		
		authEle.appendChild(usernameEle);
		
		authEle.appendChild(passwordEle);
		
		header = new Header(new QName("2authHeaer"), authEle);
		
		headers.add(header);
	}

}
