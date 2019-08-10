package iona.pojo;

import java.util.List;

/**
 * Websocket服务端发送消息给前端的Model类
 */
public class WebsocketServerMessage {
    private String responseMessage;

    //聊天室相关
    private ChatMessage chatMessage;
    private String userList;
    private List<ChatMessage> chatMessages;

    public WebsocketServerMessage(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public WebsocketServerMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public WebsocketServerMessage(ChatMessage chatMessage, String userList) {
        this.chatMessage = chatMessage;
        this.userList = userList;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public ChatMessage getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }

    public String getUserList() {
        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }
}
