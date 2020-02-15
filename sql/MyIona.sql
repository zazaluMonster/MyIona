CREATE DATABASE MyIona;

USE MyIona;




create table CREW
(
    id              int auto_increment comment '船员id',
    crewName        varchar(10)  not null comment '船员姓名',
    phoneNum        VARCHAR(20)  not null comment '电话号码',
    mail            VARCHAR(30)  not null comment '电子邮件',
    crewMessage     TEXT         null comment '船员个人简介',
    avatarUrl       varchar(100) not null comment '头像地址',
    passwordMd5     VARCHAR(32)  not null comment '密码的md5值',
    lastOfflineTime VARCHAR(16)  null comment '最后下线时间',
    createTime      VARCHAR(16)  not null comment '注册时间',
    filed1          VARCHAR(10)  null comment '空余字段1',
    constraint CREW_pk
        primary key (id)
)
    comment '船员表';

create unique index CREW_crewName_uindex
    on CREW (crewName);

create unique index CREW_phoneNum_uindex
    on CREW (phoneNum);

--

create table MESSAGE
(
    id         int auto_increment,
    html       VARCHAR(1000) not null,
    createTime VARCHAR(16)   not null,
    creator    int           not null,
    constraint MESSAGE_pk
        primary key (id)
)
    comment '伊文表';

create index MESSAGE_createTime_uindex
    on MESSAGE (createTime);

create index MESSAGE_creator_uindex
    on MESSAGE (creator);

alter table MESSAGE
    add retweetorId int null comment '转推者id';

alter table MESSAGE
    add retweetTime VARCHAR(16) null comment '转推时间';

alter table MESSAGE
    add retweetMessageId int null comment '原推文id';

alter table MESSAGE modify retweetorId int not null comment '转推者id';

--

create table FOLLOW
(
    id            int auto_increment,
    followerId    int         not null,
    followerName  VARCHAR(10) not null,
    followingId   int         not null comment '正在关注的人的id',
    followingName VARCHAR(10) not null,
    followTime    VARCHAR(16) not null comment '什么时候开始关注的',
    constraint FOLLOW_pk
        primary key (id)
)
    comment '关注表';

create index FOLLOW_followerId_uindex
    on FOLLOW (followerId);

create index FOLLOW_followerName_uindex
    on FOLLOW (followerName);

create index FOLLOW_followingId_uindex
    on FOLLOW (followingId);

create index FOLLOW_followingName_uindex
    on FOLLOW (followingName);

--

create table LIKE_RECORD
(
    id int auto_increment,
    likerId int not null comment '喜欢者用户id',
    messageId int not null comment '喜欢的Message',
    constraint LIKE_pk
        primary key (id)
)
    comment '喜欢表';

--

create table COMMENT
(
    id int auto_increment,
    commentatorId int not null comment '评论人id',
    html VARCHAR(1000) not null comment '评论内容',
    messageId int null comment '评论的是哪条伊文',
    fatherId int null comment '父评论Id',
    createTime VARCHAR(16) not null comment '评论时间',
    constraint COMMENT_pk
        primary key (id)
)
    comment '评论表';

create index COMMENT_commentatorId_index
    on COMMENT (commentatorId);

create index COMMENT_fatherId_index
    on COMMENT (fatherId);

create index COMMENT_messageId_index
    on COMMENT (messageId);

create index COMMENT_createTime_index
    on COMMENT (createTime);

--

create table NOTICE
(
    id int auto_increment,
    notifierId int not null comment '被通知人id',
    content VARCHAR(1000) not null comment '通知内容',
    createTime VARCHAR(16) not null comment '通知时间',
    url VARCHAR(1000) not null comment '跳转URL',
    constraint NOTICE_pk
        primary key (id)
)
    comment '通知表';

create index NOTICE_notifierId_uindex
    on NOTICE (notifierId);

alter table NOTICE
    add isRead int not null comment '是否已阅';


