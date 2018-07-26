package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

/**
 * 项目搭建测试
 * @author mym
 *
 */
@Controller("/test")
public class TestController {

	
	@RequestMapping("/hello")
	@ResponseBody
	public String test() {
		
		return "hello";
	}
	
}
