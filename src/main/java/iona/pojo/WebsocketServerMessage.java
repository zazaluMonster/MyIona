package iona.pojo;

/**
 * Websocket服务端发送消息给前端的Model类
 */
public class WebsocketServerMessage {
    private String responseMessage;

    public WebsocketServerMessage(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
