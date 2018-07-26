package com.example.demo.handler;

import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.WebConnection;

public class MyProtocolHandler implements HttpUpgradeHandler {

	@Override
	public void init(WebConnection wc) {
		// TODO Auto-generated method stub
		System.out.println("init...");
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destroy...");
		
	}

}
