package iona.pojo;

/**
 * WebSocket连接,前端发送消息给后端的Model类
 */
public class WebsocketClientMessage {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
