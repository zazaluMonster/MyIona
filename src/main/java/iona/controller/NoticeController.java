package iona.controller;

import iona.async.RunnerQueue;
import iona.exception.IonaException;
import iona.modelView.NoticeResponse;
import iona.pojo.Notice;
import iona.service.INoticeService;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
@ResponseBody
public class NoticeController {

    @Autowired
    private INoticeService noticeService;
    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 获取当前用户的未读消息
     */
    @RequestMapping(value = "/getNewNotice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoticeResponse getNewNotice(@RequestBody Notice notice) {
        MyHttpStatus status = MyHttpStatus.OK;

        List<Notice> notices = noticeService.getCurUserNewNotice(notice.getNotifierId());

        NoticeResponse result = new NoticeResponse(status,notices);
        return result;
    }

    /**
     * 更新为已读
     */
    @RequestMapping(value = "/readNotice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public NoticeResponse readNotice(@RequestBody Notice notice) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        noticeService.readNotice(notice.getId());

        NoticeResponse result = new NoticeResponse(status);
        return result;
    }
}
