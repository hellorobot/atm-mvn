package com.dayuanit.atm.db;

import redis.clients.jedis.Jedis;

public class Redis {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.8.181", 6379);
		jedis.auth("123456"); 
		jedis.set("zzq", "zzq");
		
		String jedisval = jedis.get("zzq");
		System.out.println(jedisval);
	}
}
