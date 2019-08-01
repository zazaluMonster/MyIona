package iona.controller;

import iona.async.RunnerQueue;
import iona.exception.IonaException;
import iona.modelView.FollowResponse;
import iona.modelView.LikeResponse;
import iona.pojo.Crew;
import iona.pojo.Follow;
import iona.pojo.Like;
import iona.service.ICrewService;
import iona.service.IFollowService;
import iona.service.ILikeService;
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
@RequestMapping("/like")
@ResponseBody
public class LikeController {

    @Autowired
    private ILikeService likeService;
    @Autowired
    RunnerQueue runnerQueue;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 喜欢
     */
    @RequestMapping(value = "/doLike", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LikeResponse doLike(@RequestBody Like like) {
        MyHttpStatus status = MyHttpStatus.OK;

        List<Like> likes = new ArrayList<>();
        likes.add(like);
        likeService.inserts(likes);

        LikeResponse result = new LikeResponse(status);
        return result;
    }

    /**
     * 取消喜欢
     */
    @RequestMapping(value = "/cancelLike", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public LikeResponse cancelLike(@RequestBody Like like) throws IonaException {
        MyHttpStatus status = MyHttpStatus.OK;

        Like hitLike = likeService.selectByLikerIdAndMessageId(like.getLikerId(),like.getMessageId());
        likeService.delete(hitLike.getId());

        LikeResponse result = new LikeResponse(status);
        return result;
    }

}
