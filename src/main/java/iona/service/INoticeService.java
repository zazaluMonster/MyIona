package iona.service;


import iona.pojo.Notice;

import java.util.List;

public interface INoticeService extends BaseService<Notice> {
    void registerNotice(int crewId);
    List<Notice> getCurUserNewNotice(int crewId);
}
