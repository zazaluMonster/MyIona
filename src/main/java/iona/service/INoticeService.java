package iona.service;


import iona.exception.IonaException;
import iona.pojo.Notice;

import java.util.List;

public interface INoticeService extends BaseService<Notice> {
    void produceNotice(int crewId,int noticeType, int messageId, int followerId) throws IonaException;
    List<Notice> getCurUserNewNotice(int crewId);
    void readNotice(int id) throws IonaException;
    Notice getById(int id) throws IonaException;

}
