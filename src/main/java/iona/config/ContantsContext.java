package iona.config;

public class ContantsContext {
    //由IonaInitRunner初始化的部分
    public static String BASE_URL;
    public static String CONTEXT_PATH;
    public static int PAGER_SIZE;


    //一些既定的常量数据,不会被改变,故写死
    public static String TRUE = "1";
    public static String FALSE = "0";
    public static int I_TRUE = 1;
    public static int I_FALSE = 0;
    public static String IOS_HEART = "ios-heart";
    public static String IOS_HEART_OUTLINE = "ios-heart-outline";
    public static String IOS_REPEAT = "ios-repeat";
    public static String MD_REPEAT = "md-repeat";
    public static String ZERO = "0";

    //Websocket相关
    //webSocket相关配置
    //链接地址
    public static String WEBSOCKETPATHPERFIX = "/ws-push";
    public static String WEBSOCKETPATH = "/sockJsEndPoint";
    //消息代理路径
    public static String WEBSOCKETBROADCASTPATH = "/topic";
    //前端发送给服务端请求地址
    public static final String FORETOSERVERPATH = "/welcome";
    //服务端生产地址,客户端订阅此地址以接收服务端生产的消息
    public static final String PRODUCERPATH = "/topic/getResponse";
    //点对点消息推送地址前缀
    public static final String P2PPUSHBASEPATH = "/user";
    //点对点消息推送地址后缀,最后的地址为/user/用户识别码/msg
    public static final String P2PPUSHPATH = "/msg";

    //通知消息模板
    public static String FIRST_REGISTER = "感谢注册,赶紧上传自己心仪的头像吧";
    public static String FIRST_REGISTER_URL = CONTEXT_PATH + "/main/userData";

}
