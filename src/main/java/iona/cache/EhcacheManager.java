package iona.cache;

import iona.logger.IonaLogger;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/**
 * 借助SpringBoot缓存机制实现的一个缓存帮助类,比如插入新缓存键值对，根据key获取缓存值，清除指定key的缓存
 *
 * 如需理解此类代码，请先了解SpringBoot的注解缓存使用机制
 */
@Component
@CacheConfig(cacheNames = "myIonaCache")
public class EhcacheManager {

    /**
     * 添加缓存键值对
     * @return
     */
    @CachePut(key = "#key",condition = "#result != null")
    public String addCache(String key, String value) {
        IonaLogger.info("正在手动操作缓存系统，正在手动插入缓存键值对，key=" + key + "，value=" + value);
        if(StringUtils.isEmpty(value)){
            IonaLogger.info("空字符串不支持缓存");
            return null;
        }


        return value;

    }

    /**
     * 根据key，获取缓存值
     *
     * 方法运行机制说明：
     * 若方法体执行，根据SpringBoot缓存注解逻辑，说明指定key不存在对应的value，故return ""
     *
     * 存在的缺陷，当key确实不存在的时候，由于方法体被执行，所以key=""会被存储到缓存中
     *
     * 解决方式：
     * 再封装一层，来进行控制，见{@link iona.cache.IonaCache}
     * @return
     */
    @Cacheable
    public String getCacheValue(String key) {
        IonaLogger.info("未找到key为:" + key + " 的缓存数据");
        return "";
    }

    /**
     * 根据key移除缓存
     * @param key
     */
    @CacheEvict
    public void removeCache(String key) {
        IonaLogger.info("正在清除key=" + key + "的缓存");
    }
}
