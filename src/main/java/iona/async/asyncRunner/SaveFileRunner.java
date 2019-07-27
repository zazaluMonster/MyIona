package iona.async.asyncRunner;

import iona.logger.IonaLogger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;

/**
 * 专门用于存储文件的Runner
 */
@Component
//@Scope(value = "prototype",proxyMode = ScopedProxyMode.TARGET_CLASS)
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class SaveFileRunner extends BaseRunner{
    private String file;
    private String data;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public SaveFileRunner(String file, String data) {
        this.file = file;
        this.data = data;
    }

    @Override
    public void run() {
        byte[] bs = Base64Utils.decodeFromString(data);
        try {
            FileUtils.writeByteArrayToFile(new File(file), bs);
        } catch (IOException e) {
            e.printStackTrace();
            IonaLogger.info("保存图片失败!");
        }
        runnerQueue.runnerComplete();
    }
}
