package iona.util;


import iona.logger.IonaLogger;

import java.util.Random;

public class MyStringUtil {
    public static String getVerifyCode(){
        //生成6位验证码，先使用random获取0~899999的数，在统一加上100000，保证肯定是六位
        String verifyCode = String
                .valueOf(new Random().nextInt(899999) + 100000);
        IonaLogger.info("短信验证码生成：" + verifyCode);
        return verifyCode;
    }

    public static String getRandomImgVersion(){
        return "?version=" + Math.random();
    }
}
