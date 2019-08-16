package iona.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * redis缓存简单封装类
 */
@Component
public class ValueIonaRedisCache {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
    }

    public Object get(String key){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    public Integer getNums(String key){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        Integer nums = (Integer) valueOperations.get(key);
        if(nums == null){
            nums = 0;
        }
        return nums;
    }
    public void incr(String key){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.increment(key);
    }

}
