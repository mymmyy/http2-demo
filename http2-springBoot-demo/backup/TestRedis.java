package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.JedisCluster;

@Controller
public class TestRedis {


    /*@Autowired
    private JedisCluster jedisCluster;


    @GetMapping("/testRedis")
    @ResponseBody
    public String findRedis() {
        jedisCluster.set("username", "hello mym");
        return jedisCluster.get("username");
    }*/

	
}
