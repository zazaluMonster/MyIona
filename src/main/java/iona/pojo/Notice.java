package iona.pojo;


import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 通知表
 */
@Component
public class Notice implements Serializable {

    private int id;
    private int notifierId;
    private String content;
    private String url;
    private String createTime;
    private int isRead;

    /**
     * 注册类型
     */
    public static class NoticeType{
        public static final int REGISTER = 1;
        public static final int COMMENT = 2;
        public static final int FOLLOW = 3;
        public static final int LIKE = 4;
        public static final int NEW_MESSAGE = 5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotifierId() {
        return notifierId;
    }

    public void setNotifierId(int notifierId) {
        this.notifierId = notifierId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }
}
