package iona.service.impl;

import iona.cache.IonaCache;
import iona.dao.ILikeDao;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.pojo.Like;
import iona.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LikeService implements ILikeService {

    @Autowired
    private ILikeDao likeDao;
    @Autowired
    private IonaCache ionaCache;


    @Override
    public void inserts(List<Like> items) {
        likeDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        likeDao.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        likeDao.deletes(ids);
    }

    @Override
    public void update(Like item) throws IonaException {
        likeDao.update(item);
    }

    @Override
    public void updates(List<Like> items) {
        likeDao.updates(items);
    }

    @Override
    public List<Like> selects(Map<String, Object> condition) {
        return likeDao.selects(condition);
    }

    @Override
    public Like selectByLikerIdAndMessageId(int likerId, int messageId) throws IonaException {
        Like like = new Like();
        like.setLikerId(likerId);
        like.setMessageId(messageId);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",like);
        List<Like> follows =  likeDao.selects(condition);
        if(follows == null || follows.size() <= 0){
            return null;
        }else if(follows.size() > 1){
            IonaLogger.info("检测到数据库数据关系异常,likerId+messageId组合不唯一,likerId:" +likerId+ ",messageId" + messageId);
            throw new IonaException();
        }else{
            return follows.get(0);
        }
    }
}
