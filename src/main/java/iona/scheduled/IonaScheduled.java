package iona.scheduled;

import iona.logger.IonaLogger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IonaScheduled {
    /**
     * 每天11点进行系统清理
     */
    @Scheduled(cron = "0 0 11 * * ?")
    public void systemClean(){
        IonaLogger.info("iona clean");

    }
}
