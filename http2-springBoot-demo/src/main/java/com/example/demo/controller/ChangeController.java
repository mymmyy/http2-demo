package com.example.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.handler.MyProtocolHandler;

/**
 * 尝试通过http1.1升级成http2（暂未成功）
 * @author mym
 *
 */
@RestController
public class ChangeController {
	
	@GetMapping("/checkHttp")
	public void upgradeProtocol(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println(request.getHeader("Upgrade"));
		if (request.getHeader("Upgrade").equals("h2c")) {  
		    response.setStatus(HttpServletResponse.SC_SWITCHING_PROTOCOLS);  
		    response.setHeader("Connection", "Upgrade");  
		    response.setHeader("Upgrade", "h2c");  
		    request.upgrade(MyProtocolHandler.class);  
		    System.out.println("Request upgraded to MyProtocolHandler");  
		} 
		
	}

}
