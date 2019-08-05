package iona.modelView;


import iona.pojo.Message;
import iona.util.MyHttpStatus;

import java.util.List;

public class MessageResponse extends BaseModelView {
    private List<Message> messages;
    private String currentTime;
    private Message messageDetail;

    public MessageResponse(MyHttpStatus status) {
        super(status);
    }

    public MessageResponse(MyHttpStatus status, String msg) {
        super(status, msg);
    }

    public MessageResponse(MyHttpStatus status, List<Message> messages, String currentTime) {
        super(status);
        this.messages = messages;
        this.currentTime = currentTime;
    }

    public MessageResponse(MyHttpStatus status, Message messageDetail) {
        super(status);
        this.messageDetail = messageDetail;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public Message getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(Message messageDetail) {
        this.messageDetail = messageDetail;
    }
}
