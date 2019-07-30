package iona.pojo;

import iona.config.ContantsContext;
import iona.util.MyStringUtil;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Message implements Serializable {
    private int id;
    private String html;
    private String createTime;
    private int creator;
    private String creatorName;
    private String avatarUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = ContantsContext.BASE_URL + avatarUrl + MyStringUtil.getRandomImgVersion();
    }
}
