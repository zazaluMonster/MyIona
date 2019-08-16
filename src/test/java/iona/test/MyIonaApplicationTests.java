package iona.test;

import iona.cache.IonaCache;
import iona.pojo.Crew;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyIonaApplicationTests {
    @Autowired
    IonaCache ionaCache;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedis() {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        System.out.println(operations.increment("www"));
    }

}
