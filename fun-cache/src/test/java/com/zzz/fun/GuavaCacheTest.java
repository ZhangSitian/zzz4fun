package com.zzz.fun;

import com.google.common.cache.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheTest {

    private static final String DEFAULT_VALUE = "Hello Guava Cache";

    @Test
    public void testHello() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("word", DEFAULT_VALUE);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("word"));
    }

    // 加载规则一致时使用
    @Test
    public void testLoadingCache() throws ExecutionException {
        LoadingCache<String, Object> cache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, Object>() {
                    public Object load(String key) {
                        return key;
                    }
                });
        Assert.assertSame(cache.get("234567"), cache.get("234567"));
    }

    // 加载规则一致时使用
    @Test
    public void testCallable() throws ExecutionException {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build();
        String value = cache.get("key", () -> {
            System.out.println("load"); //加载数据线程执行标志
            return DEFAULT_VALUE;
        });
        Assert.assertSame(DEFAULT_VALUE, value);
    }


    @Test
    public void testSize() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                // 设置缓存大小
                .maximumSize(2)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", DEFAULT_VALUE);
        cache.put("key3", DEFAULT_VALUE);
        Assert.assertNull(cache.getIfPresent("key1"));
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key2"));
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key3"));
    }

    @Test
    public void testExpireAfterWrite() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1", DEFAULT_VALUE);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertNull(cache.getIfPresent("key1"));
    }

    @Test
    public void testExpireAfterAccess() throws InterruptedException {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1", DEFAULT_VALUE);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(1);
        Assert.assertEquals(DEFAULT_VALUE, cache.getIfPresent("key1"));
        TimeUnit.SECONDS.sleep(3);
        Assert.assertNull(cache.getIfPresent("key1"));
    }


    @Test
    public void testWeakValues() {
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .weakValues()
                .build();
        Object value = new Object();
        cache.put("key1", value);
        //原对象不再有强引用
        value = null;
        Assert.assertNotNull(cache.getIfPresent("key1"));
        System.gc();
        Assert.assertNull(cache.getIfPresent("key1"));
    }


    @Test
    public void testInvalidate() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("key1", DEFAULT_VALUE);
        cache.put("key2", DEFAULT_VALUE);
        cache.put("key3", DEFAULT_VALUE);
        //批量清除list中全部key对应的记录
        cache.invalidate("key2");
        Assert.assertNotNull(cache.getIfPresent("key1"));
        Assert.assertNull(cache.getIfPresent("key2"));
        Assert.assertNotNull(cache.getIfPresent("key3"));
    }

    @Test
    public void testInvalidatePart() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("key1", DEFAULT_VALUE);
        cache.put("key2", DEFAULT_VALUE);
        cache.put("key3", DEFAULT_VALUE);

        List<String> list = new ArrayList<>();
        list.add("key1");
        list.add("key2");
        //批量清除list中全部key对应的记录
        cache.invalidateAll(list);
        Assert.assertNull(cache.getIfPresent("key1"));
        Assert.assertNull(cache.getIfPresent("key2"));
        Assert.assertNotNull(cache.getIfPresent("key3"));
    }

    @Test
    public void testInvalidateAll() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("key1", DEFAULT_VALUE);
        cache.put("key2", DEFAULT_VALUE);
        cache.put("key3", DEFAULT_VALUE);
        //批量清除list中全部key对应的记录
        cache.invalidateAll();
        Assert.assertNull(cache.getIfPresent("key1"));
        Assert.assertNull(cache.getIfPresent("key2"));
        Assert.assertNull(cache.getIfPresent("key3"));
    }

    @Test
    public void testRemovalListener() {
        RemovalListener<String, String> listener = notification ->
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        cache.put("key4","value4");
        cache.put("key5","value5");
        cache.put("key6","value6");
        cache.put("key7","value7");
        cache.put("key8","value8");
    }

    @Test
    public void testRecordStats() {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                //开启统计信息开关
                .recordStats()
                .build();
        cache.put("key1","value1");
        cache.put("key2","value2");
        cache.put("key3","value3");
        cache.put("key4","value4");

        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");
        // 获取统计信息
        System.out.println(cache.stats());
    }



}
