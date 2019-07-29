package iona.service.impl;

import iona.cache.EhcacheManager;
import iona.cache.IonaCache;
import iona.dao.ICrewDao;
import iona.exception.IonaException;
import iona.logger.IonaLogger;
import iona.pojo.Crew;
import iona.service.ICrewService;
import iona.util.DateUtil;
import iona.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

@Component
public class CrewService implements ICrewService {

    @Autowired
    private ICrewDao crewDao;
    @Autowired
    private IonaCache ionaCache;

    /**
     * 验证账号密码是否正确
     * @param crew
     * @return
     */
    public boolean auth(Crew crew){
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crew == null || crews.size() <= 0){
            return false;
        }
        return true;
    }

    /**
     * Token登录处理
     */
    public String tokenResolve(Crew crew, boolean doForce) {
        String key = crew.getCrewName();

        String token = TokenUtil.getToken(key);

        if(ionaCache.getCacheValue(key) != null){
            if(doForce){
                ionaCache.removeCache(key);
            }else{
                return ionaCache.getCacheValue(key);
            }
        }

        ionaCache.addCache(crew.getCrewName(),token);

        return token;
    }

    public boolean isCrewAlreadyExist(String crewName,String phoneNum,String mail){
        Crew crew = new Crew();
        crew.setCrewName(crewName);
        if(auth(crew)){
            return true;
        }
        crew = new Crew();
        crew.setPhoneNum(phoneNum);
        if(auth(crew)){
            return true;
        }
        crew = new Crew();
        crew.setMail(mail);
        if(auth(crew)){
            return true;
        }
        return false;
    }

    public int register(Crew crew){
        crew.setAvatarUrl(" ");
        crew.setCreateTime(DateUtil.getTimeString(new Date()));

        List<Crew> crews = new ArrayList<>();
        crews.add(crew);
        inserts(crews);
        return crews.get(0).getId();
    }

    @Override
    public Crew getByPhone(String phone) throws IonaException {
        Crew crew = new Crew();
        crew.setPhoneNum(phone);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crew == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            IonaLogger.info("检测到数据库数据关系异常,同一个手机号查询到了多个用户,此手机号为:" + phone);
            throw new IonaException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public Crew getByMail(String mail) throws IonaException {
        Crew crew = new Crew();
        crew.setMail(mail);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crew == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            IonaLogger.info("检测到数据库数据关系异常,同一个邮箱查询到了多个用户,此邮箱为:" + mail);
            throw new IonaException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public Crew getByCrewName(String crewName) throws IonaException {
        Crew crew = new Crew();
        crew.setCrewName(crewName);
        Map<String,Object> condition = new HashMap<>();
        condition.put("item",crew);
        List<Crew> crews =  crewDao.selects(condition);
        if(crew == null || crews.size() <= 0){
            return null;
        }else if(crews.size() > 1){
            IonaLogger.info("检测到数据库数据关系异常,同一个用户名查询到了多个用户,此用户名为:" + crewName);
            throw new IonaException();
        }else{
            return crews.get(0);
        }
    }

    @Override
    public void updateAvatar(String crewName,String fileName) throws IonaException {
        Crew crew = getByCrewName(crewName);
        crew.setAvatarUrl(fileName);
        update(crew);
    }

    @Override
    public void inserts(List<Crew> items) {
        crewDao.inserts(items);
    }

    @Override
    public void delete(int id) {
        crewDao.delete(id);

    }

    @Override
    public void deletes(List<Integer> ids) {
        crewDao.deletes(ids);
    }

    @Override
    public void update(Crew newCrew) throws IonaException {
        //验证要修改的数据
        if(newCrew.getId() <= 0){
            throw new IonaException("修改异常: id违规");
        }

        Crew oldCrew = getByCrewName(newCrew.getCrewName());
        if(oldCrew.getId()!=newCrew.getId()){
            throw new IonaException("昵称已注册");
        }

        oldCrew = getByPhone(newCrew.getPhoneNum());
        if(oldCrew.getId()!=newCrew.getId()){
            throw new IonaException("手机号已注册");
        }

        oldCrew = getByMail(newCrew.getMail());
        if(oldCrew.getId()!=newCrew.getId()){
            throw new IonaException("邮箱已注册");
        }

        crewDao.update(newCrew);
    }

    @Override
    public void updates(List<Crew> items) {
        crewDao.updates(items);
    }

    @Override
    public List<Crew> selects(Map<String, Object> condition) {
        return crewDao.selects(condition);
    }

}
