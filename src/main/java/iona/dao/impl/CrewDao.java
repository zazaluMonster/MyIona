package iona.dao.impl;

import iona.dao.ICrewDao;
import iona.mapper.CrewMapper;
import iona.pojo.Crew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CrewDao implements ICrewDao {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CrewMapper crewMapper;

    @Override
    public void inserts(List<Crew> items) {
        crewMapper.inserts(items);
    }

    @Override
    public void delete(int id) {
        crewMapper.delete(id);
    }

    @Override
    public void deletes(List<Integer> ids) {
        crewMapper.deletes(ids);
    }

    @Override
    public void update(Crew item) {
        crewMapper.update(item);
    }

    @Override
    public void updates(List<Crew> items) {
        crewMapper.updates(items);
    }

    @Override
    public List<Crew> selects(Map<String, Object> condition) {
        return crewMapper.selects(condition);
    }

}
