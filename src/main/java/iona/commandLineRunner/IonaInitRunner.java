package iona.commandLineRunner;

import iona.cache.EhcacheManager;
import iona.cache.IonaCache;
import iona.logger.IonaLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IonaInitRunner implements CommandLineRunner {

    @Autowired
    IonaCache ionaCache;

    @Override
    public void run(String... args) throws Exception {
        IonaLogger.info("缓存预存备用数据中");
        ionaCache.addCache("_author","zazalu");
        ionaCache.addCache("_iona","zazalu's daughter");

        String author = ionaCache.getCacheValue("_author");
        String iona = ionaCache.getCacheValue("_iona");

        IonaLogger.info("本系统作者为:" + author);
        IonaLogger.info("iona系统是什么:" + iona);
    }
}
