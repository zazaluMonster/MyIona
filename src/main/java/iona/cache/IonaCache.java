package iona.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * IonaCache是本系统操作Ehcache缓存实例的封装工具类
 */
@Component
public class IonaCache {

    @Autowired
    EhcacheManager ehcacheManager;

    /**
     * 添加缓存
     */
    public boolean addCache(String key,String value){
        String result = ehcacheManager.addCache(key,value);
        if(StringUtils.isEmpty(result)){
            throw new RuntimeException("Iona: 添加缓存异常");
        }else{
            return true;
        }
    }

    /**
     * 查询缓存
     */
    public String getCacheValue(String key){
        String result = ehcacheManager.getCacheValue(key);
        if(StringUtils.isEmpty(result)){
            ehcacheManager.removeCache(key);
            return null;
        }else{
            return result;
        }
    }

    /**
     * 清除缓存
     */
    public void removeCache(String key){
        ehcacheManager.removeCache(key);
    }
}
