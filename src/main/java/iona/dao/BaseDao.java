package iona.dao;

import iona.exception.IonaException;
import iona.pojo.Pager;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    void inserts(List<T> items);
    void delete(int id);
    void deletes(List<Integer> ids);
    void update(T item);
    void updates(List<T> items);
    List<T> selects(Map<String, Object> condition);
    default List<T> selects(Map<String, Object> condition, Pager pager) throws IonaException {
        throw new IonaException("当前操作不支持");
    }
}
