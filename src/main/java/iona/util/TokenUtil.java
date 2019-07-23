package iona.util;

import java.util.UUID;

public class TokenUtil {

    //模拟生成复杂token
    public static String getToken(String msg){
        UUID uuid = UUID.randomUUID();
        return uuid.toString() + "_" + msg;
    }
}
