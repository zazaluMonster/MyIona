package iona.pojo;


/**
 * WebSocket连接,前端发送消息给后端的Model类
 */
public class WebsocketClientMessage {
    private int type;
    private String name;

    //聊天室相关
    private ChatMessage message;

    //消息类别
    public static class TYPE{
        public static int DISCONNET = 1;
        public static int CONNET = 2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
