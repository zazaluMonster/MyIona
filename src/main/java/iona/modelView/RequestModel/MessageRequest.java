package iona.modelView.RequestModel;

import iona.pojo.Message;
import iona.pojo.Pager;

public class MessageRequest {
    private Message message;
    private Pager pager;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
