package iona.dao.impl;

import iona.dao.ILikeDao;
import iona.mapper.LikeMapper;
import iona.pojo.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class LikeDao extends AbstractDao implements ILikeDao {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LikeMapper likeMapper;

    @Override
    public void inserts(List<Like> items) {
        likeMapper.inserts(items);
    }

    @Override
    public void delete(int id) {
        likeMapper.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        likeMapper.deletes(ids);
    }

    @Override
    public void update(Like item) {
        likeMapper.update(item);
    }

    @Override
    public void updates(List<Like> items) {
        likeMapper.updates(items);
    }

    @Override
    public List<Like> selects(Map<String, Object> condition) {
        return likeMapper.selects(condition);
    }
}
