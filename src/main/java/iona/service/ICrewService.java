package iona.service;


import iona.exception.FirstLoginException;
import iona.exception.IonaException;
import iona.pojo.Crew;

public interface ICrewService extends BaseService<Crew> {
    boolean auth(Crew crew);

    String tokenResolve(Crew crew, boolean doForce) throws FirstLoginException;

    boolean isCrewAlreadyExist(String crewName, String phoneNum, String mail);

    int register(Crew crew);

    Crew getByPhone(String phone) throws IonaException;

    Crew getByMail(String mail) throws IonaException;

    Crew getByCrewName(String crewName) throws IonaException;

    Crew getById(int id) throws IonaException;

    void updateAvatar(String crewName,String fileName) throws IonaException;
}
