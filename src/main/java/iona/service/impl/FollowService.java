package iona.service.impl;

import iona.cache.IonaCache;
import iona.dao.IFollowDao;
import iona.dao.IMessageDao;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.pojo.Follow;
import iona.pojo.Message;
import iona.service.IFollowService;
import iona.service.IMessageService;
import iona.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class FollowService implements IFollowService {

    @Autowired
    private IFollowDao followDao;
    @Autowired
    private IonaCache ionaCache;


    @Override
    public void inserts(List<Follow> items) {
        followDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        followDao.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        followDao.deletes(ids);
    }

    @Override
    public void update(Follow item) throws IonaException {
        followDao.update(item);
    }

    @Override
    public void updates(List<Follow> items) {
        followDao.updates(items);
    }

    @Override
    public List<Follow> selects(Map<String, Object> condition) {
        return followDao.selects(condition);
    }

    @Override
    public Follow getByFollowIdAndFollowingId(int followId, int followingId) throws IonaException {
        Follow follow = new Follow();
        follow.setFollowerId(followId);
        follow.setFollowingId(followingId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",follow);
        List<Follow> follows =  followDao.selects(condition);
        if(follows == null || follows.size() <= 0){
            return null;
        }else if(follows.size() > 1){
            IonaLogger.info("检测到数据库数据关系异常,followId+followingId组合不唯一,followId:" +followId+ ",followingId" + followingId);
            throw new IonaException();
        }else{
            return follows.get(0);
        }
    }
}
