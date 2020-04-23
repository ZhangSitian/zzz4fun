package com.zzz.learn;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

public class RankDemo {


    public static void main(String[] args) {
        Jedis jedis = new JedisPool(new JedisPoolConfig(), "127.0.0.1", 6379).getResource();
        // rank List
        jedis.zadd("MoneyRankList", 1000000, "Jack Ma");// 马云
        jedis.zadd("MoneyRankList", 800000, "Wang JianLin");//王建林
        jedis.zadd("MoneyRankList", 900000, "Pony Ma");// 马化腾
        jedis.zadd("MoneyRankList", 700000, "Liu Qiangdong");//刘强东
        jedis.zadd("MoneyRankList", 600000, "Li Jiacheng");// 李嘉诚

        // 获取排行榜前三名
        System.out.println("Demo: Get Top 3:");
        Set<String> top3MoneySet = jedis.zrevrange("MoneyRankList", 1, 3);// 财富榜前三名
        int index = 0 ;
        for(String name : top3MoneySet){
            System.out.print(" || index: "+ ++index +" name: "+name + " money: "+ jedis.zscore("MoneyRankList", name));
        }

        // 获取某个成员的排名&score
        System.out.println("\nDemo: Get One of the member:");
        System.out.println(" || index: "+ jedis.zrank("MoneyRankList", "Wang JianLin") +" name: "+"Wang JianLin" + " money: "+ jedis.zscore("MoneyRankList", "Wang JianLin"));

        // 每次保留 Top 3 超过Top3的全部删除
        // 可以使用定时器 定时删除 或者加入后删除
//       zremrangebyrank(key, min, max)：删除名称为key的zset中rank >= min且rank <= max的所有元素
//       zremrangebyscore(key, min, max) ：删除名称为key的zset中score >= min且score <= max的所有元素
        // 可以定期
//      jedis.zremrangeByRank("MoneyRankList", 3, 100000000);
        jedis.zremrangeByRank("MoneyRankList", 3, -1);

        // 获取所有的排行榜
        System.out.println("\nDemo: All members:");
        Set<String> moneySet = jedis.zrevrange("MoneyRankList", 0, -1);// 财富榜前三名
        index = 0 ;
        for(String name : moneySet){
            System.out.print(" || index: "+ ++index +" name: "+name + " money: "+ jedis.zscore("MoneyRankList", name));
        }

    }

}
