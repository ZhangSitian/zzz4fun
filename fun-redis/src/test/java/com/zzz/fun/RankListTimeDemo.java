package com.zzz.fun;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RankListTimeDemo {

    private static Jedis jedis = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379).getResource();
    private static final String KEY = "MoneyRankList";

    @Test
    public void testPickTime() throws InterruptedException {
        jedis.del(KEY);
        // add rank List
        long now = System.currentTimeMillis();
        System.out.println("currentTime:" + DateUtil.formatDateTime(new Date(now)));
        jedis.zadd(KEY, now + RandomUtil.randomLong(10) * 10000, "a");
        jedis.zadd(KEY, now + RandomUtil.randomLong(10) * 10000, "b");
        jedis.zadd(KEY, now + RandomUtil.randomLong(10) * 10000, "c");
        jedis.zadd(KEY, now + RandomUtil.randomLong(10) * 10000, "d");
        jedis.zadd(KEY, now + RandomUtil.randomLong(10) * 10000, "e");
        for (int i = 0; i < 10; i++) {
            Set<String> moneySet = jedis.zrangeByScore(KEY, System.currentTimeMillis(), Long.MAX_VALUE);
            for (String name : moneySet) {
                showMember(name);
            }
            TimeUnit.SECONDS.sleep(10L);
            System.out.println("currentTime:" + DateUtil.formatDateTime(new Date()));
        }
    }


    @Test
    public void deleteByTime() throws InterruptedException {
        jedis.del(KEY);
        // add rank List
        long now = System.currentTimeMillis();
        System.out.println("currentTime:" + DateUtil.formatDateTime(new Date(now)));
        jedis.zadd(KEY, now + RandomUtil.randomLong(20) * 1000, "a");
        jedis.zadd(KEY, now + RandomUtil.randomLong(20) * 1000, "b");
        jedis.zadd(KEY, now + RandomUtil.randomLong(20) * 1000, "c");
        jedis.zadd(KEY, now + RandomUtil.randomLong(20) * 1000, "d");
        jedis.zadd(KEY, now + RandomUtil.randomLong(20) * 1000, "e");
        Set<String> moneySet = jedis.zrangeByScore(KEY, System.currentTimeMillis(), Long.MAX_VALUE);
        for (String name : moneySet) {
            showMember(name);
        }
        TimeUnit.SECONDS.sleep(10L);
        System.out.println("currentTime:" + DateUtil.formatDateTime(new Date()));
        jedis.zremrangeByScore(KEY,0L,System.currentTimeMillis());
        moneySet = jedis.zrangeByScore(KEY, System.currentTimeMillis(), Long.MAX_VALUE);
        for (String name : moneySet) {
            showMember(name);
        }
    }

    @Test
    public void coverTime() {
        jedis.del(KEY);
        // add rank List
        long now = System.currentTimeMillis();
        System.out.println("currentTime:" + DateUtil.formatDateTime(new Date(now)));
        jedis.zadd(KEY, 2342256782222L, "a");
        Set<String> moneySet = jedis.zrangeByScore(KEY, System.currentTimeMillis(), Long.MAX_VALUE);
        System.out.println(moneySet);
        for (String name : moneySet) {
            showMember(name);
        }
        jedis.zadd(KEY, 2322226782222L, "a");
        System.out.println("currentTime:" + DateUtil.formatDateTime(new Date()));
        moneySet = jedis.zrangeByScore(KEY, System.currentTimeMillis(), Long.MAX_VALUE);
        for (String name : moneySet) {
            showMember(name);
        }
    }

    private static void showMember(String tag) {
        long num = jedis.zrevrank(KEY, tag) + 1;
        long rank = jedis.zrank(KEY, tag);
        long score = jedis.zscore(KEY, tag).longValue();
        System.out.println(" num: " + num + " name: " + tag + " time: " + DateUtil.formatDateTime(new Date(score)) + " rank:" + rank);
    }


}
