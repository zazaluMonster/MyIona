package iona.controller;

import iona.async.RunnerQueue;
import iona.exception.IonaException;
import iona.modelView.FollowResponse;
import iona.pojo.Crew;
import iona.pojo.Follow;
import iona.pojo.Notice;
import iona.service.ICrewService;
import iona.service.IFollowService;
import iona.service.INoticeService;
import iona.util.DateUtil;
import iona.util.MyHttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/follow")
@ResponseBody
public class FollowController {

    @Autowired
    private IFollowService followService;
    @Autowired
    private INoticeService noticeService;
    @Autowired
    private ICrewService crewService;
    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 查询是否关注
     */
    @RequestMapping(value = "/isFollowing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FollowResponse isFollowing(@RequestBody Follow follow) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        Follow followData = followService.getByFollowIdAndFollowingId(follow.getFollowerId(), follow.getFollowingId());

        FollowResponse result = new FollowResponse(status, followData);
        return result;
    }

    /**
     * 添加关注
     */
    @RequestMapping(value = "/addFollow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FollowResponse addFollow(@RequestBody Follow follow) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        //TODO 粗糙写一下，不写新的service方法了
        Crew follower = crewService.getById(follow.getFollowerId());
        Crew following = crewService.getById(follow.getFollowingId());

        List<Follow> follows = new ArrayList<>();
        follow.setFollowerName(follower.getCrewName());
        follow.setFollowingName(following.getCrewName());
        follow.setFollowTime(DateUtil.getTimeString(new Date()));
        follows.add(follow);
        followService.inserts(follows);

        noticeService.produceNotice(following.getId(), Notice.NoticeType.FOLLOW,0, follower.getId());

        FollowResponse result = new FollowResponse(status);
        return result;
    }

    /**
     * 取消关注
     */
    @RequestMapping(value = "/cancelFollow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public FollowResponse cancelFollow(@RequestBody Follow follow) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        Follow curFollow = followService.getByFollowIdAndFollowingId(follow.getFollowerId(),follow.getFollowingId());
        followService.delete(curFollow.getId());

        FollowResponse result = new FollowResponse(status);
        return result;
    }


}
