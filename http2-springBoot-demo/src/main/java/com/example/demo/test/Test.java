package com.example.demo.test;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test {

	public static void main(String[] args) {
//		testSingle();
		testCluster();
	}
	
	public static void testCluster() {
	    JedisPoolConfig poolConfig = new JedisPoolConfig();
	    // 最大连接数
	    poolConfig.setMaxTotal(1);
	    // 最大空闲数
	    poolConfig.setMaxIdle(1);
	    // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
	    // Could not get a resource from the pool
	    poolConfig.setMaxWaitMillis(1000);
	    Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
	    nodes.add(new HostAndPort("192.168.1.13", 6379));
	    nodes.add(new HostAndPort("192.168.1.13", 6380));
	    nodes.add(new HostAndPort("192.168.1.13", 6381));
	    JedisCluster cluster = new JedisCluster(nodes, poolConfig);
	    String name = cluster.get("name");
	    System.out.println(name);
	    cluster.set("age", "18");
	    System.out.println(cluster.get("age"));
	    try {
	        cluster.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void testSingle() {
	    JedisPoolConfig poolConfig = new JedisPoolConfig();
	    // 最大连接数
	    poolConfig.setMaxTotal(2);
	    // 最大空闲数
	    poolConfig.setMaxIdle(2);
	    // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
	    // Could not get a resource from the pool
	    poolConfig.setMaxWaitMillis(1000);
	    JedisPool pool = new JedisPool(poolConfig, "192.168.1.13", 6379);
	    Jedis jedis = null;
	    try {
	        for (int i = 0; i < 5; i++) {
	            jedis = pool.getResource();
	            jedis.set("foo" + i, "bar" + i);
	            System.out.println("第" + (i + 1) + "个连接, 得到的值为" + jedis.get("foo" + i));
	            // 用完一定要释放连接
	            jedis.close();
	        }
	    } finally {
	        pool.close();
	    }
	}
	
}
