package iona.pojo;

import iona.config.ContantsContext;
import iona.util.MyStringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Component
public class Message implements Serializable {
    private int id;
    private String html;
    private String createTime;
    private int creator;

    //关联数据
    private String creatorName;
    private String avatarUrl;
    private int curUserId;//当前查阅者id

    //喜欢
    private int likeRecordId;
    private String iconType;//用于前端初始化展示图标
    private String likeNums;//该消息的总喜欢量

    //转推
    private int retweetorId;
    private String retweetTime;
    private int retweetMessageId;
    private String retweetIconType;
    private String retweetNums;
    private String retweetorName;
    private int doCurUserRetweet;


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

    public int getCurUserId() {
        return curUserId;
    }

    public void setCurUserId(int curUserId) {
        this.curUserId = curUserId;
    }

    public int getLikeRecordId() {
        return likeRecordId;
    }

    public void setLikeRecordId(int likeRecordId) {
        if (likeRecordId > 0) {
            this.setIconType(ContantsContext.IOS_HEART);
        } else {
            this.setIconType(ContantsContext.IOS_HEART_OUTLINE);
        }
        this.likeRecordId = likeRecordId;
    }

    public String getIconType() {
        return iconType;
    }

    public void setIconType(String iconType) {
        this.iconType = iconType;
    }

    public String getLikeNums() {
        return likeNums;
    }

    public void setLikeNums(String likeNums) {
        if (StringUtils.isEmpty(likeNums)) {
            this.likeNums = likeNums;
        } else {
            if (likeNums.equals(ContantsContext.ZERO)) {
                this.likeNums = "";
            } else {
                this.likeNums = likeNums;
            }
        }

    }

    public int getRetweetorId() {
        return retweetorId;
    }

    public void setRetweetorId(int retweetorId) {
        this.retweetorId = retweetorId;
    }

    public String getRetweetTime() {
        return retweetTime;
    }

    public void setRetweetTime(String retweetTime) {
        this.retweetTime = retweetTime;
    }

    public int getRetweetMessageId() {
        return retweetMessageId;
    }

    public void setRetweetMessageId(int retweetMessageId) {
        //if(retweetMessageId > 0){
        //    this.setRetweetIconType(ContantsContext.MD_REPEAT);
        //} else {
        //    this.setRetweetIconType(ContantsContext.IOS_REPEAT);
        //}
        this.retweetMessageId = retweetMessageId;
    }

    public String getRetweetIconType() {
        return retweetIconType;
    }

    public void setRetweetIconType(String retweetIconType) {
        this.retweetIconType = retweetIconType;
    }

    public String getRetweetNums() {
        return retweetNums;
    }

    public void setRetweetNums(String retweetNums) {
        if (StringUtils.isEmpty(retweetNums)) {
            this.retweetNums = retweetNums;
        } else {
            if (retweetNums.equals(ContantsContext.ZERO)) {
                this.retweetNums = "";
            } else {
                this.retweetNums = retweetNums;
            }
        }

    }

    public String getRetweetorName() {
        return retweetorName;
    }

    public void setRetweetorName(String retweetorName) {
        this.retweetorName = retweetorName;
    }

    public int getDoCurUserRetweet() {
        return doCurUserRetweet;
    }
    public void setDoCurUserRetweet(int doCurUserRetweet) {
        if(doCurUserRetweet > 0){
            this.setRetweetIconType(ContantsContext.MD_REPEAT);
        } else {
            this.setRetweetIconType(ContantsContext.IOS_REPEAT);
        }
        this.doCurUserRetweet = doCurUserRetweet;
    }
}
