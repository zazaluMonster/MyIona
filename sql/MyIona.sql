CREATE DATABASE MyIona;

USE MyIona;

create table CREW
(
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
    id int auto_increment,
    html VARCHAR(1000) not null,
    createTime VARCHAR(16) not null,
    creator int not null,
    constraint MESSAGE_pk
        primary key (id)
)
    comment '伊文表';

create unique index MESSAGE_createTime_uindex
    on MESSAGE (createTime);

create unique index MESSAGE_creator_uindex
    on MESSAGE (creator);


