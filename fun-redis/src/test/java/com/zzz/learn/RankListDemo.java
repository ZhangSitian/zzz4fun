package com.zzz.learn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class RankListDemo {

    private static Jedis jedis = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379).getResource();

    public static void main(String[] args) {
        jedis.del("MoneyRankList");
        // add rank List
        jedis.zadd("MoneyRankList", 800000, "王建林");//王建林
        jedis.zadd("MoneyRankList", 600000, "李嘉诚");// 李嘉诚
        jedis.zadd("MoneyRankList", 700000, "刘强东");//刘强东
        jedis.zadd("MoneyRankList", 900000, "马化腾");// 马化腾
        jedis.zadd("MoneyRankList", 1000000, "马云");// 马云
        showMembers("Get Top 3:", 0, 2);
        // 获取某个成员的排名&score
        System.out.println("Get One of the member:");
        showMember("王建林");
        showMembers("show all members", 0, -1);
        // 删除下标大于等于3的
        System.out.println("delete out of top 3 members:");
        jedis.zremrangeByRank("MoneyRankList", 0, -4);
        // 留下的是前三名
        showMembers("leave Top 3:", 0, -1);
        // 添加人员
        System.out.println("add members:");
        jedis.zadd("MoneyRankList", 1000, "zzz");
        showMembers("all:", 0, -1);
        // 加分
        System.out.println("add score:");
        jedis.zincrby("MoneyRankList", 1000, "zzz");
        showMembers("all:", 0, -1);
    }

    // 获取排行榜
    private static void showMembers(String prompt, int start, int end) {
        System.out.println(prompt);
        Set<String> moneySet = jedis.zrevrange("MoneyRankList", start, end);
        for (String name : moneySet) {
            showMember(name);
        }
    }

    private static void showMember(String name) {
        long num = jedis.zrevrank("MoneyRankList", name) + 1;
        long rank = jedis.zrank("MoneyRankList", name);
        double score = jedis.zscore("MoneyRankList", name);
        System.out.println(" num: " + num + " name: " + name + " money: " + score + " rank:" + rank);
    }
}
