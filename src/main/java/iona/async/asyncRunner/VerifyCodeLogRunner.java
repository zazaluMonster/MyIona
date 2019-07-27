package iona.async.asyncRunner;

import iona.logger.IonaLogger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 异步记录所有系统发出的验证码
 */
@Component
@Scope(value = "prototype")
public class VerifyCodeLogRunner extends BaseRunner {

    private String verifyCode;
    private String time;
    private String contract;


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public VerifyCodeLogRunner(String verifyCode, String time, String contract) {
        this.verifyCode = verifyCode;
        this.time = time;
        this.contract = contract;
    }

    @Override
    public void run() {
        IonaLogger.info("正在记录验证码: " +  "VerifyCodeLogRunner{" +
                "verifyCode='" + verifyCode + '\'' +
                ", time='" + time + '\'' +
                ", phone='" + contract + '\'' +
                '}');
        runnerQueue.runnerComplete();
    }


}
