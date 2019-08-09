package iona.commandLineRunner;

import iona.cache.IonaCache;
import iona.config.ContantsContext;
import iona.logger.IonaLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IonaInitRunner implements CommandLineRunner {

    @Autowired
    IonaCache ionaCache;
    @Value("${iona.baseurl}")
    public String baseURl;
    @Value("${iona.pageSize}")
    public int pageSize;
    @Value("${server.servlet.context-path}")
    public String contextPath;

    @Override
    public void run(String... args) throws Exception {
        IonaLogger.info("缓存预存备用数据中");
        ionaCache.addCache("_author","zazalu");
        ionaCache.addCache("_iona","zazalu's daughter");

        String author = ionaCache.getCacheValue("_author");
        String iona = ionaCache.getCacheValue("_iona");

        IonaLogger.info("本系统作者为:" + author);
        IonaLogger.info("iona系统是什么:" + iona);

        ContantsContext.BASE_URL = baseURl;
        ContantsContext.PAGER_SIZE = pageSize;
        ContantsContext.CONTEXT_PATH = contextPath;
        ContantsContext.FIRST_REGISTER_URL = ContantsContext.CONTEXT_PATH + "/main/userData";
        ContantsContext.COMMENT_PREFIX_URL = ContantsContext.CONTEXT_PATH + "/main/messageDetail";
        ContantsContext.FOLLOW_PREFIX_URL = ContantsContext.CONTEXT_PATH + "/main/userCard";
        ContantsContext.LIKE_PREFIX_URL = ContantsContext.CONTEXT_PATH + "/main/messageDetail";
        ContantsContext.NEW_MESSAGE_PREFIX_URL = ContantsContext.CONTEXT_PATH + "/main/messageDetail";
    }
}
