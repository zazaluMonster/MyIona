package iona.pojo;

import iona.config.ContantsContext;
import iona.util.MyStringUtil;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Comment implements Serializable {

    private int id;
    private int commentatorId;
    private String html;
    private int messageId;
    private int fatherId;
    private String createTime;

    //关联数据
    private String crewName;
    private String avatarUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(int commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = ContantsContext.BASE_URL + avatarUrl + MyStringUtil.getRandomImgVersion();
    }
}
