package com.llc.cache;

import org.junit.Before;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RKeys;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.util.List;
import java.util.Map;

/**
 * @author lilichuan
 */
public class RedisUtil {

    private RedissonClient redissonClient;

    @Test
    public void test(){
        redissonClient.getBucket("test").set("test");
        redissonClient.getBucket("aaa").set("aaa");
        RKeys keys = redissonClient.getKeys();
        keys.getKeys().forEach(System.out::println);


        redissonClient.getMap("mapTest").put("test","test");

    }

    @Test
    public void test1(){

        redissonClient.getMap("mapTest1").putAll(Map.of(1,"1",2,"2",3,"3"));

    }

    @Test
    public void testList(){
        RList<Object> listTest = redissonClient.getList("listTest");
        listTest.add("1");
        listTest.addAll(List.of("2","3","4","5","6","7","8","9","10"));

    }

    @Test
    public void testGetList(){
        RList<String> listTest = redissonClient.getList("listTest");
        listTest.clear();
        List<String> objects = listTest.readAll();
        System.out.println(objects.size());
    }

    @Test
    public void testFilter(){
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter("bloom");

        bloomFilter.tryInit(1000000, 1);  //预测插入数量, 误判率

        bloomFilter.add("first");
        bloomFilter.add("second");
        bloomFilter.add("third");;

        System.out.println(bloomFilter.contains("second"));
        System.out.println(bloomFilter.contains("automan"));
        System.out.println(bloomFilter.count());

    }


    @Before
    public void createRedissonClient() {
        Config config = new Config();
        config.useSingleServer()
            .setAddress("redis://0.0.0.0:6379")
//            .setPassword("Redis123")
            .setDatabase(0).setDatabase(3);

        config.setCodec(new JsonJacksonCodec());

        redissonClient = Redisson.create(config);
    }

}
