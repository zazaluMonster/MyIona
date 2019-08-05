package iona.dao.impl;

import iona.config.ContantsContext;
import iona.exception.IonaException;
import iona.pojo.Pager;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class AbstractDao{
    private String msg = "添加分页器失败,condition为空";

    public void setPager(Map<String, Object> condition) throws IonaException {
        if(condition == null){
            throw new IonaException(msg);
        }
        condition.put("pager",new Pager(1, ContantsContext.PAGER_SIZE));
    }

    public void setPager(Map<String, Object> condition, Pager pager) throws IonaException {
        if(condition == null){
            throw new IonaException(msg);
        }
        condition.put("pager",pager);
    }
}
