package iona.service;

import iona.exception.IonaException;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    void inserts(List<T> items);
    void delete(int id);
    void deletes(List<Integer> ids);
    void update(T item) throws IonaException;
    void updates(List<T> items);
    List<T> selects(Map<String, Object> condition);
}
