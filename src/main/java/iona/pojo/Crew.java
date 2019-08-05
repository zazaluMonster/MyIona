package iona.pojo;


import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 船员用户类
 */
@Component
public class Crew implements Serializable {
    /*
    id int auto_increment comment '船员id',
    crewName varchar(10) not null comment '船员姓名',
    phoneNum VARCHAR(20) not null comment '电话号码',
    mail VARCHAR(30) not null comment '电子邮件',
    crewMessage TEXT null comment '船员个人简介',
    avatarUrl varchar(100) not null comment '头像地址',
    passwordMd5 VARCHAR(32) not null comment '密码的md5值',
    lastOfflineTime VARCHAR(16) null comment '最后下线时间',
    createTime VARCHAR(16) not null comment '注册时间',
    filed1 VARCHAR(10) null comment '空余字段1',
     */
    private int id;
    private String crewName;
    private String phoneNum;
    private String mail;
    private String crewMessage;
    private String avatarUrl;
    private String passwordMd5;
    private String lastOfflineTime;
    private String createTime;
    private String filed1;

    private int isFollow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCrewMessage() {
        return crewMessage;
    }

    public void setCrewMessage(String crewMessage) {
        this.crewMessage = crewMessage;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPasswordMd5() {
        return passwordMd5;
    }

    public void setPasswordMd5(String passwordMd5) {
        this.passwordMd5 = passwordMd5;
    }

    public String getLastOfflineTime() {
        return lastOfflineTime;
    }

    public void setLastOfflineTime(String lastOfflineTime) {
        this.lastOfflineTime = lastOfflineTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFiled1() {
        return filed1;
    }

    public void setFiled1(String filed1) {
        this.filed1 = filed1;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }
}
