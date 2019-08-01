package iona.pojo;


import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 船员用户类
 */
@Component
public class Like implements Serializable {

    private int id;
    private int likerId;
    private int messageId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikerId() {
        return likerId;
    }

    public void setLikerId(int likerId) {
        this.likerId = likerId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
