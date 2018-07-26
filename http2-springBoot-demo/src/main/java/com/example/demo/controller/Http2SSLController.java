package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http2 ssl connect
 * @author mym
 *
 */
@RestController
public class Http2SSLController {
	
	final String testGifPath = "/test.gif";
	final String testPngPath = "/test.png";

    /**
     * curl -Ik --http2 https://localhost:8443/hello
     * @return
     */
    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }
    
    @GetMapping("/testGet")
    public String testGet(HttpServletRequest request,String param){
    	String returnStr = request.getRequestURI()+" 请求 ["+request.getRemoteAddr()+"] 的传入参数为:"+param;
    	return returnStr;
    }
    
    
    @GetMapping("/testServerPush")
    public void http2ServerPush(String param, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PushBuilder pushBuilder = request.newPushBuilder();
        if(pushBuilder == null) {
        	System.out.println("服务器推送不可用!");
        	return;
        }
        pushBuilder
                .path("/test.png")
                .addHeader("content-type", "image/png")
                .push();
        try(PrintWriter respWriter = response.getWriter()){
            respWriter.write("<html>" +
                            "<img src='/test.png'>" +
                    "</html>");
        }
//        PushBuilder pushBuilder = request.newPushBuilder();
//        pushBuilder
//	        .path(testGifPath)	
//        	.addHeader("content-type", "image/gif")
//        	.push();
//        
//        ;
//    	String returnStr = request.getRequestURI()+" 请求 ["+request.getRemoteAddr()+"] 的传入参数为:"+param + "  server 同时成功 push！";
//    	return returnStr;
    }
   
    @GetMapping(value = "/test.png")
    public void download(HttpServletResponse response) throws IOException {
    	System.out.println("prepare to server push!");
        InputStream data = new FileInputStream(new File("test.png"));
        response.setHeader("content-type", "image/png");
        FileCopyUtils.copy(data,response.getOutputStream());
    }
    
//===========================================================================    
    
    @GetMapping("/testTextPush")
    public void http2TestTextPush(String param, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	String pathStr = "/textGet";
        PushBuilder pushBuilder = request.newPushBuilder();
        if(pushBuilder == null) {
        	System.out.println("服务器推送不可用!");
        	return;
        }
        pushBuilder
	        .path(pathStr)	
        	.addHeader("content-type", "text/html")
        	.addHeader("charset", "UTF-8")
        	.push();
        
        ;
//    	String returnStr = request.getRequestURI()+" 请求 ["+request.getRemoteAddr()+"] 的传入参数为:"+param + "本次server push 的 path 为:"+pathStr;
//    	return returnStr;
        String returnStr = "this is server -  push return info !!";
        try(PrintWriter respWriter = response.getWriter()){
            respWriter.write("<html>" +
                            "<h1>"+returnStr+"</h1>" +
                    "</html>");
    	}
    }
    
    
    @GetMapping("/textGet")
    public void textReturn(HttpServletResponse response) throws IOException {
    	System.out.println("prepare to server push!");
    	response.setHeader("content-type", "text/html");
    	response.addHeader("charset", "UTF-8");
    	response.getOutputStream().flush();
    }

}    



